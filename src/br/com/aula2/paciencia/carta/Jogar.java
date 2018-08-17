package br.com.aula2.paciencia.carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Jogar {
	
	private static List<Carta> cartas;
	private static List<Carta> descarte;
	private static List<List<Carta>> fundacoes = new ArrayList<List<Carta>>();
	private static Carta as[];

	public static void main(String[] args) {
		cartas = new ArrayList<Carta>();
		descarte = new ArrayList<Carta>();
		as = new Carta[4];
		fundacoes = new ArrayList<List<Carta>>();
		
		int localOrigem;
		
		int localDestino;
		
		int qntdCarta;
		
		Scanner ler = new Scanner(System.in);
		
		boolean continuarJogo = true;
		
		for(int i=0 ; i<13; i++) {
			for(int j=0 ; j<4; j++) {
				Carta carta = new Carta(i+1, j%2==0 ? "Vermelho" : "Preto");
				cartas.add(carta);
			}
		}
		
		Collections.shuffle(cartas);
		
		//sorteando as cartas
		for(int fundacao=0; fundacao<7; fundacao++) {
			List<Carta> cartaFundacoes = new ArrayList<Carta>();
			for(int j=0; j<=fundacao; j++) {
				cartaFundacoes.add(cartas.get(cartas.size() -1));
				
				cartas.remove(cartas.size() -1);
			}
			cartaFundacoes.get(cartaFundacoes.size() - 1).setVirado(true);
			fundacoes.add(cartaFundacoes);
		}
		
		do {
			
			//Imprimir as torres
			for(int fundacao=0; fundacao<fundacoes.size(); fundacao++) {
				for(Carta carta : fundacoes.get(fundacao)) {
					if(carta.isVirado()) {
						System.out.println(carta.getNumero() + " - " + carta.getCor());
					} else {
						System.out.println("-");
					}
				}
			}
			
			System.out.println("Carta as");
			//Imprimir as
			for(int i=0; i<4; i++) {
				if(as[i]!=null) {
					System.out.print(as[i]+"\t");
				} else {
					System.out.print("Vazio"+"\t");
				}
				
			}
			
			//Imprimir Descarte
			if(descarte!=null && !descarte.isEmpty()) {
				Carta carta = descarte.get(descarte.size()-1);
				System.out.println(carta.getNumero() + " - " + carta.getCor());
				} else {
				System.out.println("Não Há Descartes.");
			}
			
			System.out.println("De onde você deseja mover?");
			localOrigem = ler.nextInt();
					
			//descarte para
			if(localOrigem == 0)
			{			
				if(!cartas.isEmpty()) {
					descarte.add(cartas.get(cartas.size()-1));
					cartas.remove(cartas.size()-1);
				}else {
					System.out.println("Não tem mais carta para descarte, vamos resetar as cartas de descarte");
					cartas = descarte;
					descarte.clear();
				}
				
				continue;
			}else if(localOrigem == 1) {
				
				System.out.println("Onde você deseja inserir?");
				localDestino = ler.nextInt();
				
				adicionaNoDestino(localDestino, localOrigem, 1);
				
			}else if(localOrigem > 1 && localOrigem < 9) {
				
				System.out.println("Quantas cartas você deseja mover?");
				qntdCarta = ler.nextInt();
				
				System.out.println("Onde você deseja inserir?");
				localDestino = ler.nextInt();
				
				adicionaNoDestinoFundacoes(localDestino, localOrigem, qntdCarta);
				
			} if(localOrigem > 8 && localOrigem < 13 ) {

				System.out.println("Onde você deseja inserir?");
				localDestino = ler.nextInt();
				
				adicionaNoDestinoFundacoes(localDestino, localOrigem, 1);
			
			} else {
				System.out.println("É proibido mover carta desse local!");
			}
			
			
		}while(continuarJogo);
		
		
	}

	private static void adicionaNoDestinoFundacoes(int localDestino, int localOrigem,  int quantidade) {
		List<Carta> listaOrigem = fundacoes.get(localOrigem-2);
		Carta ultimaCartaDestino = fundacoes.get(localDestino-2).get(fundacoes.get(localDestino-2).size() - 1);
		Carta ultimaCartaOrigem = fundacoes.get(localOrigem-2).get(fundacoes.get(localOrigem-2).size() - 1);
		
		if(localDestino > 1 && localDestino < 9 ) {
			if(permitidoMover(ultimaCartaDestino, ultimaCartaOrigem)) {
				int tamanhoListaOrigem = listaOrigem.size();
				for(int i = 0; i <  tamanhoListaOrigem ; i++) {
					if(tamanhoListaOrigem - i < quantidade && listaOrigem.get(i).isVirado()) {
						fundacoes.get(localDestino-2).add(listaOrigem.get(listaOrigem.size() - 1));
						listaOrigem.remove(listaOrigem.size() -1);
					}
				}
			} else {
				System.out.println("É proibido mover carta nesse local!");
			}
		} else {
			if(quantidade == 1) {
				int tamanhoListaOrigem = listaOrigem.size();
				for(int i = 0; i <  tamanhoListaOrigem ; i++) {
					if(tamanhoListaOrigem - i < quantidade && listaOrigem.get(i).isVirado() && listaOrigem.get(i).getNumero()==1) {
						int destinoAs = localDestino -9;
						if(as[destinoAs]!=null) {
							System.out.println("É proibido mover carta nesse local!");
						} else { 
							preencherAs(ultimaCartaOrigem, destinoAs);
							listaOrigem.remove(listaOrigem.size() -1);
						}
					}
				}
			} else {
				System.out.println("É proibido mover carta nesse local!");
			}
		}
	}
	
	private static void adicionaNoDestino(int localDestino, int localOrigem,  int quantidade) {
		Carta origem = descarte.get(descarte.size()-1);
		Carta ultimaCartaDestino = fundacoes.get(localDestino-2).get(fundacoes.get(localDestino-2).size() - 1);
		
		
		if (localDestino > 1 && localDestino < 9) {
			if (permitidoMover(ultimaCartaDestino, origem)) {
				if (origem.isVirado()) {
					fundacoes.get(localDestino - 2).add(origem);
					descarte.remove(descarte.size() - 1);
				}
			} else {
				System.out.println("É proibido mover carta nesse local!");
			}
		} else {
			if (origem.isVirado() && origem.getNumero() == 1) {
				int destinoAs = localDestino - 9;
				if (as[destinoAs] != null) {
					System.out.println("É proibido mover carta nesse local!");
				} else {
					preencherAs(origem, destinoAs);
					descarte.remove(descarte.size() - 1);
				}
			}
		}
	}
	
	private static void preencherAs(Carta origem, int destinoAs) {
		as[destinoAs].setCor(origem.getCor());
		as[destinoAs].setNumero(origem.getNumero());
		as[destinoAs].setVirado(true);
	}

	private static boolean permitidoMover(Carta ultimaCartaDestino, Carta ultimaCartaOrigem) {
		if(ultimaCartaDestino.getNumero() >  ultimaCartaOrigem.getNumero()) {
			if(!ultimaCartaDestino.getCor().equals(ultimaCartaOrigem.getCor()))
				return true;
		}
		return false;
	}

}

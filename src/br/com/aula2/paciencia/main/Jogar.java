package br.com.aula2.paciencia.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import br.com.aula2.paciencia.model.Carta;
import br.com.aula2.paciencia.rn.DistribuicaoRN;

public class Jogar {
	
	private static List<Carta> descarte;
	private static Carta as[];
	private static DistribuicaoRN distribuicaoRN = new DistribuicaoRN();
	
	public static void main(String[] args) {
		descarte = new ArrayList<Carta>();
		as = new Carta[4];
		
		int localOrigem;
		int localDestino;
		int sair;
		int qntdCarta;
				
		Scanner ler = new Scanner(System.in);
		
		//sorteando as cartas
		distribuicaoRN.distribuirCartas();
		
		do {
			System.out.println("______________****************************______________");
			System.out.println("______________*******Paciência Calor******______________");
			System.out.println("______________****************************______________");
			
			//Imprimir as torres
			distribuicaoRN.imprimirListaDistribuicoes();
			
			//Imprimir as
			for(int i=0; i<4; i++) {
				System.out.print("______________Torre de AS ");
				System.out.print(i+7);
				System.out.println("______________");
				if(as[i]!=null) {
					System.out.println(as[i].getNumero() + " - " + as[i].getCor());
				} else {
					System.out.println("Vazio");
				}
				
			}
			
			System.out.println("______________Torre 11 Descartes______________");
			if(descarte!=null && !descarte.isEmpty()) {
				Carta carta = descarte.get(descarte.size()-1);
				System.out.println(carta.getNumero() + " - " + carta.getCor());
				} else {
				System.out.println("Não Há Descartes.");
			}
			
			System.out.println("______________****************______________");
			System.out.println("_________Para Mais Cartas Digite 12_________");
			System.out.println("______________****************______________");
			
			System.out.println("Deseja continuar Jogando? 1 para 'sim' e 0 para 'parar'");
			sair = ler.nextInt();
			
			if(sair == 1) {
				System.out.println("De onde você deseja mover?");
				localOrigem = ler.nextInt();
						
				if(localOrigem == 12) {			
					if(!cartas.isEmpty()) {
						Carta cartaDescarte = cartas.get(cartas.size()-1);
						cartaDescarte.setVirada(true);
						descarte.add(cartaDescarte);
						cartas.remove(cartas.size()-1);
					}else {
						System.out.println("Não tem mais carta para descarte, vamos resetar as cartas de descarte");
						cartas = descarte;
						descarte.clear();
					}
					
					continue;
				}else if(localOrigem == 11) {
					
					System.out.println("Onde você deseja inserir?");
					localDestino = ler.nextInt();
					
					adicionaNoDestino(localDestino, localOrigem, 1);
					
				}else if(localOrigem >=0 && localOrigem <=6) {
					
					System.out.println("Quantas cartas você deseja mover?");
					qntdCarta = ler.nextInt();
					
					System.out.println("Onde você deseja inserir?");
					localDestino = ler.nextInt();
					
					adicionaNoDestinoFundacoes(localDestino, localOrigem, qntdCarta);
					
				} if(localOrigem >= 7 && localOrigem <= 10 ) {
	
					System.out.println("Onde você deseja inserir?");
					localDestino = ler.nextInt();
					
					adicionaNoDestinoFundacoes(localDestino, localOrigem, 1);
				
				}
			}
			
		}while(sair==1);
		
		
	}

	private static void adicionaNoDestinoFundacoes(int localDestino, int localOrigem,  int quantidade) {
		List<Carta> listaOrigem = fundacoes.get(localOrigem);
				
		if(localDestino >= 0 && localDestino <= 6 ) {
			Carta ultimaCartaDestino = fundacoes.get(localDestino).get(fundacoes.get(localDestino).size() - 1);
			Carta ultimaCartaOrigem = listaOrigem.get(fundacoes.get(localOrigem).size() - 1);
			if(permitidoMover(ultimaCartaDestino, ultimaCartaOrigem)) {
				int tamanhoListaOrigem = listaOrigem.size();
				for(int i = 0; i <  tamanhoListaOrigem ; i++) {
					if(listaOrigem.get(i).isVirada()) {
						fundacoes.get(localDestino).add(listaOrigem.get(listaOrigem.size() - 1));
						listaOrigem.remove(listaOrigem.size() -1);
						if(listaOrigem!=null && !listaOrigem.isEmpty())
							listaOrigem.get(listaOrigem.size() - 1).setVirada(true);
					}
				}
			} else {
				System.out.println("É proibido mover carta nesse local!");
			}
		} else {
			if(quantidade == 1) {
				int tamanhoListaOrigem = listaOrigem.size();
				for(int i = 0; i <  tamanhoListaOrigem ; i++) {
					if(listaOrigem.get(i).isVirada() && listaOrigem.get(i).getNumero()==1) {
						int destinoAs = localDestino -7;
						if(as[destinoAs]!=null) {
							System.out.println("É proibido mover carta nesse local!");
						} else {
							Carta ultimaCarta = listaOrigem.get(listaOrigem.size() - 1);
							preencherAs(ultimaCarta, destinoAs);
							listaOrigem.remove(listaOrigem.size() -1);
							if(listaOrigem!=null && !listaOrigem.isEmpty())
								listaOrigem.get(listaOrigem.size() -1).setVirada(true);;
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
		
		if(localDestino >= 0 && localDestino <= 6 ) {
			Carta ultimaCartaDestino = fundacoes.get(localDestino).get(fundacoes.get(localDestino).size() - 1);
			if (permitidoMover(ultimaCartaDestino, origem)) {
				if (origem.isVirada()) {
					fundacoes.get(localDestino).add(origem);
					descarte.remove(descarte.size() - 1);
					if(descarte!=null && !descarte.isEmpty())
						descarte.get(descarte.size() - 1).setVirada(true);
				}
			} else {
				System.out.println("É proibido mover carta nesse local!");
			}
		} else {
			if (origem.isVirada() && origem.getNumero() == 1) {
				int destinoAs = localDestino - 7;
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
		as[destinoAs] = new Carta();
		as[destinoAs].setCor(origem.getCor());
		as[destinoAs].setNumero(origem.getNumero());
		as[destinoAs].setVirada(true);
	}

	private static boolean permitidoMover(Carta ultimaCartaDestino, Carta ultimaCartaOrigem) {
		if(ultimaCartaDestino.getNumero() - ultimaCartaOrigem.getNumero() == 1) {
			if(!ultimaCartaDestino.getCor().equals(ultimaCartaOrigem.getCor()))
				return true;
		}
		return false;
	}

}

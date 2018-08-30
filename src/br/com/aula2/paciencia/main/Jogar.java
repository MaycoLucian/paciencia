package br.com.aula2.paciencia.main;

import java.util.Scanner;

import br.com.aula2.paciencia.mensagem.Mensagem;
import br.com.aula2.paciencia.rn.AsRN;
import br.com.aula2.paciencia.rn.BaralhoRN;
import br.com.aula2.paciencia.rn.DescarteRN;
import br.com.aula2.paciencia.rn.DistribuicaoRN;

public class Jogar {
	
	private static DistribuicaoRN distribuicaoRN = new DistribuicaoRN();
	private static AsRN asRN = new AsRN();
	private static DescarteRN descarteRN = new DescarteRN();
	private static BaralhoRN baralhoRN = new BaralhoRN();
	private static Scanner ler = new Scanner(System.in);
	private static int localOrigem;
	private static int localDestino;
	private static int qntdCarta;
	
	public static void main(String[] args) {
		int sair;
		baralhoRN.gerarBaralho();
		
		//sorteando as cartas
		distribuicaoRN.distribuirCartas(baralhoRN);
		
		asRN.criarTorresPrincipais();
		
		do {
			System.out.println("______________****************************______________");
			System.out.println("______________*******Paciência Calor******______________");
			System.out.println("______________****************************______________");
			
			//Imprimir as torres
			distribuicaoRN.imprimirListaDistribuicoes();
			
			//Imprimir as
			asRN.imprimirAs();
			
			System.out.println("______________Torre 11 Descartes______________");
			descarteRN.listarDescarte();
			
			System.out.println("______________****************______________");
			System.out.println("_________Para Mais Cartas Digite 12_________");
			System.out.println("______________****************______________");
			
			System.out.println("Deseja continuar Jogando? 1 para 'sim' e 0 para 'parar'");
			sair = ler.nextInt();
			
			if(sair == 1) {
				moverCartas();
			}
			
		}while(sair==1);
		System.out.println("Fim de Jogo!");
	}

	private static void moverCartas() {
		System.out.println("De onde você deseja mover?");
		localOrigem = ler.nextInt();

		if (localOrigem == 12) {
			baralhoRN.mover(descarteRN);
		} else if (localOrigem == 11) {

			localDestino = obterLocalDestino();
			if(localDestino >= 0 && localDestino <= 6 ) {
				descarteRN.mover(localOrigem, localDestino, distribuicaoRN.getDistribuicoes());
			} else {
				asRN.moverDescarteParaAs(localOrigem, localDestino, descarteRN.getDescartes());
			}

		} else if (localOrigem >= 0 && localOrigem <= 6) {

			localDestino = obterLocalDestino();
			
			if(localDestino >= 0 && localDestino <= 6 ) {
				System.out.println("Quantas cartas você deseja mover?");
				qntdCarta = ler.nextInt();
				
				distribuicaoRN.mover(localOrigem, localDestino, qntdCarta);
			} else {
				asRN.moverDistribuicaoParaAs(localOrigem, localDestino, distribuicaoRN.getDistribuicoes());
			}
		}
		if (localOrigem >= 7 && localOrigem <= 10) {

			localDestino = obterLocalDestino();
			if(localDestino >= 0 && localDestino <= 6) {
				descarteRN.moverDaTorreAs(localOrigem, localDestino, distribuicaoRN.getDistribuicoes(), asRN.getTorresPrincipais());
			} else {
				System.out.println(Mensagem.GENERICO);
			}
		}
	}
	
	private static int obterLocalDestino() {
		System.out.println("Onde você deseja inserir?");
		return ler.nextInt();
	}
}

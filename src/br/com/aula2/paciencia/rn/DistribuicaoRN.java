package br.com.aula2.paciencia.rn;

import java.util.ArrayList;
import java.util.List;

import br.com.aula2.paciencia.mensagem.Mensagem;
import br.com.aula2.paciencia.model.Carta;

/**
 * Corresponde as 7 Lista usadas para distribuição das cartas
 * Começa com os K (13) e vai diminuindo até cheagr no As (1)
 * Regra é que carga menor sempre deve ser de cor oposta a anterior
 * se a lista estiver a primeira carta a ser inserida na Lista deverá ser o K
 *
 */

public class DistribuicaoRN {

	private List<List<Carta>> distribuicoes;
	private BaralhoRN baralhoRN;
		
	public void distribuirCartas() {
		for(int fundacao=0; fundacao<7; fundacao++) {
			List<Carta> cartaFundacoes = new ArrayList<Carta>();
			for(int j=0; j<=fundacao; j++) {
				cartaFundacoes.add(getBaralhoRN().obterCartaTopo());
				getBaralhoRN().retiraCartaTopo();
			}
			cartaFundacoes.get(cartaFundacoes.size() - 1).setVirada(true);
			getDistribuicoes().add(cartaFundacoes);
		}
	}
	
	public void imprimirListaDistribuicoes() {
		for(int fundacao=0; fundacao<getDistribuicoes().size(); fundacao++) {
			System.out.println("______________Torre "+ fundacao+"______________");
			for(Carta carta : getDistribuicoes().get(fundacao)) {
				if(carta.isVirada()) {
					System.out.println(carta.getNumero() + " - " + carta.getNipe().getNaipe().getDescricao());
				} else {
					System.out.println(Mensagem.CARTA_NAO_VIRADA);
				}
			}
		}
	}
	
	public List<List<Carta>> getDistribuicoes() {
		if(distribuicoes == null) {
			distribuicoes = new ArrayList<List<Carta>>();
		}
		return distribuicoes;
	}

	public void setDistribuicoes(List<List<Carta>> distribuicoes) {
		this.distribuicoes = distribuicoes;
	}

	public BaralhoRN getBaralhoRN() {
		return baralhoRN;
	}

	public void setBaralhoRN(BaralhoRN baralhoRN) {
		this.baralhoRN = baralhoRN;
	}
}

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

public class DistribuicaoRN extends ComumRN {

	private List<List<Carta>> distribuicoes;
		
	public void distribuirCartas(BaralhoRN baralhoRN) {
		for(int fundacao=0; fundacao<7; fundacao++) {
			List<Carta> cartaFundacoes = new ArrayList<Carta>();
			for(int j=0; j<=fundacao; j++) {
				cartaFundacoes.add(baralhoRN.obterCartaTopo());
				baralhoRN.retiraCartaTopo();
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
	
	public void mover(int localOrigem, int localDestino, int quantidade) {
		List<Carta> listaOrigem = getDistribuicoes().get(localOrigem);

		Carta ultimaCartaDestino = getDistribuicoes().get(localDestino)
				.get(getDistribuicoes().get(localDestino).size() - 1);
		Carta ultimaCartaOrigem = listaOrigem.get(getDistribuicoes().get(localOrigem).size() - 1);
		if (permitidoMover(ultimaCartaDestino, ultimaCartaOrigem)) {
			int tamanhoListaOrigem = listaOrigem.size();
			for (int i = 0; i < tamanhoListaOrigem; i++) {
				if (listaOrigem.get(i).isVirada()) {
					if(getDistribuicoes().isEmpty() && listaOrigem.get(listaOrigem.size() - 1).getNumero()!=13) {
						System.out.println(Mensagem.LISTA_VAZIA_APENAS_REIS);
					} else {
						getDistribuicoes().get(localDestino).add(listaOrigem.get(listaOrigem.size() - 1));
						listaOrigem.remove(listaOrigem.size() - 1);
						if (listaOrigem != null && !listaOrigem.isEmpty())
							listaOrigem.get(listaOrigem.size() - 1).setVirada(true);
					}
				}
			}
		} else {
			System.out.println(Mensagem.GENERICO);
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
}

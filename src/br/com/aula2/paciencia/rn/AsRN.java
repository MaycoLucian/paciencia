package br.com.aula2.paciencia.rn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import br.com.aula2.paciencia.mensagem.Mensagem;
import br.com.aula2.paciencia.model.Carta;

public class AsRN extends ComumRN {

	private List<Stack<Carta>> torresPrincipais;

	public void criarTorresPrincipais() {
		for (int torre = 0; torre < 4; torre++) {
			Stack<Carta> pilhaAs = new Stack<Carta>();
			getTorresPrincipais().add(pilhaAs);
		}
	}
	
	public void imprimirAs() {
		for (int torre = 0; torre < 4; torre++) {
			System.out.print("______________Torre de AS ");
			System.out.print(torre + 7);
			System.out.println("______________");

			if (validarPosicaoPilha(torre)) {
				Stack<Carta> pilhaAs = getTorresPrincipais().get(torre);
				if (!pilhaAs.empty()) {
					Carta cartaTopo = obterCartaTopo(pilhaAs);
					System.out.println(cartaTopo.getNumero() + " - " + cartaTopo.getNipe().getNaipe());
				} else {
					System.out.println(Mensagem.VAZIO);
				}
			} else {
				System.out.println(Mensagem.VAZIO);
			}
		}
	}
	
	public Carta retiraCartaTopo(Stack<Carta> pilhaAs) {
		if (pilhaAs.isEmpty()) {
			return null;
		}

		return pilhaAs.pop();
	}

	private Carta obterCartaTopo(Stack<Carta> pilhaAs) {
		if (pilhaAs.isEmpty()) {
			return null;
		}

		return pilhaAs.peek();
	}
	
	public void moverDescarteParaAs(int localOrigem, int localDestino, Stack<Carta> descartes) {
		Carta origem = descartes.peek();
		
		if (origem.isVirada()) {
			int destinoAs = localDestino - 7;
			if (validarPosicaoPilha(destinoAs)) {
				Stack<Carta> pilhaAs = getTorresPrincipais().get(destinoAs);
				if (pilhaAs.isEmpty() && origem.getNumero() != 1) { 
					System.out.println(Mensagem.PILHA_SEQUENCIA_INVALIDA);
				} else if(pilhaAs.isEmpty() && origem.getNumero() == 1) {
					preencherAsComDescarte(origem, pilhaAs, descartes);
				} else if(!pilhaAs.isEmpty()) {
					if(verificarNipe(origem, pilhaAs.peek())) {	
						if(origem.getNumero() - pilhaAs.peek().getNumero() == 1) {
								preencherAsComDescarte(origem, pilhaAs, descartes);
							}
						} else {
							System.out.println(Mensagem.GENERICO);
						}
				} else {
					System.out.println(Mensagem.NIPE_INVALIDA);
				}
			} else {
				System.out.println(Mensagem.GENERICO);
			}
		}
	}
	
	public void moverDistribuicaoParaAs(int localOrigem, int localDestino, List<List<Carta>> distribuicoes) {
		List<Carta> listaOrigem = distribuicoes.get(localOrigem);
		Carta ultimaCartaOrigem = listaOrigem.get(listaOrigem.size() - 1);
		
		//int tamanhoListaOrigem = listaOrigem.size();
		//for (int i = 0; i < tamanhoListaOrigem; i++) {
			if (ultimaCartaOrigem.isVirada()) {
				int destinoAs = localDestino - 7;
				Stack<Carta> pilhaAs = getTorresPrincipais().get(destinoAs);
				if (pilhaAs.isEmpty() && ultimaCartaOrigem.getNumero() == 1) {
					preencherAs(ultimaCartaOrigem, pilhaAs, listaOrigem);
				} else if (!pilhaAs.isEmpty()) {
					if(verificarNipe(ultimaCartaOrigem, pilhaAs.peek())) {
						if (ultimaCartaOrigem.getNumero() - pilhaAs.peek().getNumero() == 1) {
							preencherAs(ultimaCartaOrigem, pilhaAs, listaOrigem);
						}
					} else 
						System.out.println(Mensagem.NIPE_INVALIDA);
				} else if (pilhaAs.isEmpty() && ultimaCartaOrigem.getNumero() != 1) { 
					System.out.println(Mensagem.PILHA_SEQUENCIA_INVALIDA);
				} else {
					System.out.println(Mensagem.GENERICO);
				}
			}
	}
	
	private boolean verificarNipe(Carta cartaOrigem, Carta cartaDestino) {
		return cartaOrigem.getNipe().getNaipe().equals(cartaDestino.getNipe().getNaipe());
	}
	
	private void preencherAsComDescarte(Carta origem, Stack<Carta> pilhaAs, Stack<Carta> descartes) {
		pilhaAs.push(origem);
		descartes.pop();
	}
	
	private void preencherAs(Carta origem, Stack<Carta> pilhaAs, List<Carta> listaOrigem) {
		pilhaAs.push(origem);
		listaOrigem.remove(listaOrigem.size() - 1);
		if (listaOrigem != null && !listaOrigem.isEmpty())
			listaOrigem.get(listaOrigem.size() - 1).setVirada(true);
	}

	private boolean validarPosicaoPilha(int posicao) {
		if(getTorresPrincipais()!=null && !getTorresPrincipais().isEmpty()) {
			if(getTorresPrincipais().size() > posicao) {
				return true;
			}
		}
		return false;
	}
	
	public List<Stack<Carta>> getTorresPrincipais() {
		if(torresPrincipais == null) {
			torresPrincipais = new ArrayList<Stack<Carta>>();
		}
		return torresPrincipais;
	}

	public void setTorresPrincipais(List<Stack<Carta>> torresPrincipais) {
		this.torresPrincipais = torresPrincipais;
	}
}

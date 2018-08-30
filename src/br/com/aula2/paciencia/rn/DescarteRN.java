package br.com.aula2.paciencia.rn;

import java.util.List;
import java.util.Stack;

import br.com.aula2.paciencia.mensagem.Mensagem;
import br.com.aula2.paciencia.model.Carta;

public class DescarteRN extends ComumRN {

	public Stack<Carta> descartes;

	public Carta retiraCartaTopo() {
		if (getDescartes().isEmpty()) {
			return null;
		}

		return getDescartes().pop();
	}

	private Carta obterCartaTopo() {
		if (getDescartes().isEmpty()) {
			return null;
		}

		return getDescartes().peek();
	}
	
	private boolean isDescartesPossuiCartas() {
		return getDescartes()!=null && !getDescartes().isEmpty();
	}

	public void listarDescarte() {
		if (isDescartesPossuiCartas()) {
			Carta carta = obterCartaTopo();
			System.out.println(carta.getNumero() + " - " + carta.getNipe().getNaipe().getDescricao());
		} else {
			System.out.println(Mensagem.VAZIO);
		}
	}
	
	public void mover(int localOrigem, int localDestino, List<List<Carta>> distribuicoes) {
		Carta origem = obterCartaTopo();
		
		Carta ultimaCartaDestino = distribuicoes.get(localDestino).get(distribuicoes.get(localDestino).size() - 1);
		if (permitidoMover(ultimaCartaDestino, origem)) {
			if (origem.isVirada()) {
				distribuicoes.get(localDestino).add(origem);
				retiraCartaTopo();
			}
		} else {
			System.out.println(Mensagem.GENERICO);
		}
	}
	
	public void moverDaTorreAs(int localOrigem, int localDestino, List<List<Carta>> distribuicoes, List<Stack<Carta>> torresPrincipais) {
		Stack<Carta> pilhaAs = torresPrincipais.get(localOrigem-7);
		Carta origem = pilhaAs.peek();
		
		Carta ultimaCartaDestino = distribuicoes.get(localDestino).get(distribuicoes.get(localDestino).size() - 1);
		if (permitidoMover(ultimaCartaDestino, origem)) {
			if (origem.isVirada()) {
				distribuicoes.get(localDestino).add(origem);
				pilhaAs.pop();
			}
		} else {
			System.out.println(Mensagem.GENERICO);
		}
	}
	
	public Stack<Carta> getDescartes() {
		if (descartes == null) {
			descartes = new Stack<Carta>();
		}
		return descartes;
	}

	public void setDescartes(Stack<Carta> descartes) {
		this.descartes = descartes;
	}
}

package br.com.aula2.paciencia.rn;

import java.util.Collections;
import java.util.Stack;

import br.com.aula2.paciencia.enun.CorEnum;
import br.com.aula2.paciencia.enun.NaipeEnum;
import br.com.aula2.paciencia.model.Carta;
import br.com.aula2.paciencia.model.Naipe;

public class BaralhoRN {

	public Stack<Carta> cartas;

	final public static Naipe[] naipes = { new Naipe(NaipeEnum.NIPE_COPAS, CorEnum.CARTA_VERMELHA),
			new Naipe(NaipeEnum.NIPE_OURO, CorEnum.CARTA_VERMELHA),
			new Naipe(NaipeEnum.NIPE_ESPADA, CorEnum.CARTA_PRETA),
			new Naipe(NaipeEnum.NIPE_PAUS, CorEnum.CARTA_PRETA) };

	/**
	 * Método responsável por criar o baralho
	 * 
	 * @return uma lista com 52 cartas
	 */
	public Stack<Carta> gerarBaralho() {

		for (int numCarta = 0; numCarta < 13; numCarta++) {
			for (Naipe naipe : BaralhoRN.naipes) {
				Carta nc = new Carta(numCarta + 1, naipe);
				getCartas().push(nc);
			}
		}

		embaralhar();
		
		return getCartas();
	}

	private void embaralhar() {
		Collections.shuffle(getCartas());
	}

	public Carta retiraCartaTopo() {
		if (getCartas().isEmpty()) {
			return null;
		}

		return getCartas().pop();
	}

	public Carta obterCartaTopo() {
		if (getCartas().isEmpty()) {
			return null;
		}

		return getCartas().peek();
	}
	
	private boolean isPossuiCartas() {
		return getCartas()!=null && !getCartas().isEmpty();
	}
	
	/**
	 * unico movimento que pode ser feito é para a pilha de descarte
	 */
	public void mover(DescarteRN descarteRN) {
		if(isPossuiCartas()) {
			Carta cartaDescarte = obterCartaTopo();
			cartaDescarte.setVirada(true);
			descarteRN.getDescartes().push(cartaDescarte);
			retiraCartaTopo();
		}else {
			System.out.println("Não tem mais carta para descarte, vamos resetar as cartas de descarte");
			for(int i = descarteRN.getDescartes().size(); i > 0; i-- ) {
				getCartas().push(descarteRN.getDescartes().get(i-1));
			}
			descarteRN.getDescartes().clear();
		}
	}
	
	public Stack<Carta> getCartas() {
		if (cartas == null) {
			cartas = new Stack<Carta>();
		}
		return cartas;
	}

	public void setCartas(Stack<Carta> cartas) {
		this.cartas = cartas;
	}
}

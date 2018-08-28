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
		if (cartas.isEmpty()) {
			return null;
		}

		return cartas.pop();
	}

	public Carta obterCartaTopo() {
		if (cartas.isEmpty()) {
			return null;
		}

		return cartas.peek();
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

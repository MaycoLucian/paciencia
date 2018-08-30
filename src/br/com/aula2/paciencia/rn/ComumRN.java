package br.com.aula2.paciencia.rn;

import br.com.aula2.paciencia.model.Carta;

public class ComumRN {

	public static boolean permitidoMover(Carta ultimaCartaDestino, Carta ultimaCartaOrigem) {
		if(ultimaCartaDestino.getNumero() - ultimaCartaOrigem.getNumero() == 1) {
			if(!ultimaCartaDestino.getNipe().getCor().equals(ultimaCartaOrigem.getNipe().getCor()))
				return true;
		}
		return false;
	}
}

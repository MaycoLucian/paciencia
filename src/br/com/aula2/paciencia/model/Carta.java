package br.com.aula2.paciencia.model;

public class Carta {

	private int numero;
	private boolean virada;
	private Naipe nipe;
	
	public Carta(int numeroCarta, Naipe nipe) {
		this.numero = numeroCarta;
		virada = false;
		this.nipe = nipe;
	}
	
	public Carta() {
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isVirada() {
		return virada;
	}

	public void setVirada(boolean virada) {
		this.virada = virada;
	}

	public Naipe getNipe() {
		return nipe;
	}

	public void setNipe(Naipe nipe) {
		this.nipe = nipe;
	}
}

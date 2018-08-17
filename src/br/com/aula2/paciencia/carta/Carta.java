package br.com.aula2.paciencia.carta;

public class Carta {

	private String cor;
	private int numero;
	private boolean virado;
	
	public Carta(int numeroCarta, String cor) {
		this.cor = cor;
		this.numero = numeroCarta;
		virado = false;
	}
	
	public Carta() {
	}

	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isVirado() {
		return virado;
	}

	public void setVirado(boolean virado) {
		this.virado = virado;
	}
}

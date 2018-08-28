package br.com.aula2.paciencia.enun;

public enum CorEnum {
	CARTA_VERMELHA("VERMELHO"),
	CARTA_PRETA("PRETO");
	
	private final String cor;

    
	CorEnum(final String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return cor;
    }
}

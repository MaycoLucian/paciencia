package br.com.aula2.paciencia.enun;

public enum NaipeEnum {
	 NIPE_COPAS(0, "COPAS"),
	 NIPE_OURO(1, "OURO"), 
	 NIPE_ESPADA(2, "ESPADA"), 
	 NIPE_PAUS(3, "PAUS");
	
	private int nipe;
	private String descricao;
    
	NaipeEnum(int nipe, String descricao) {
        this.nipe = nipe;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

	public int getNipe() {
		return nipe;
	}

	public void setNipe(int nipe) {
		this.nipe = nipe;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

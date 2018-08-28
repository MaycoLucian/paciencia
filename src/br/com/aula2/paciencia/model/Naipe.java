package br.com.aula2.paciencia.model;

import br.com.aula2.paciencia.enun.CorEnum;
import br.com.aula2.paciencia.enun.NaipeEnum;

public class Naipe {
    private NaipeEnum naipe;
    private CorEnum cor;
    
    public Naipe(NaipeEnum naipe, CorEnum cor) {
        this.naipe = naipe;
        this.cor = cor;
    }
    
    public NaipeEnum getNaipe() {
        return this.naipe;
    }
    
    public CorEnum getCor() {
        return this.cor;
    }
}
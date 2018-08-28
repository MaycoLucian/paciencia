package br.com.aula2.paciencia.mensagem;

public class Mensagem extends java.io.IOException {
	
	private static final long serialVersionUID = -4320294966815955422L;
	
	public static final String CARTA_NAO_VIRADA = "N�o Visivel";
    public final static int GENERICO = 0;
    public final static int LISTA_FECHADA = 1;
    public final static int LISTA_INVALIDO = 2;
    public final static int LISTA_VAZIA_APENAS_REIS = 3;
    public final static int LISTA_SEQUENCIA_INVALIDA = 4;
    public final static int PILHA_SEQUENCIA_INVALIDA = 5;
    
    private int code = 0;
    
    public Mensagem(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
    
    @Override
    public String getMessage() {
        String msg = "Movimento Inv�lido ";
        switch(this.code) {
            case LISTA_FECHADA:
                msg += "Voc� n�o pode arrastar uma carta fechada.";
                break;
            case LISTA_INVALIDO:
                msg += "A carta que voc� est� tentando<br>arrastar est� na ordem errada.";
                break;
            case LISTA_VAZIA_APENAS_REIS:
                msg += "Voc� s� pode iniciar uma lista com um REIS.";
                break;
            case LISTA_SEQUENCIA_INVALIDA:
                msg += "Voc� deve alternar entre as cores e<br>seguir a sequencia: K, Q, J, 10, 9, 8, 7, 6, 5, 4, 3, 2, A.";
                break;
            case PILHA_SEQUENCIA_INVALIDA: 
                msg += "Voc� s� pode iniciar uma pilha com<br>AS.";
                break;
            case GENERICO:
            default:
                msg += "Movimento inv�lido.";
                break;
        }
        
        return msg;
    }	
}

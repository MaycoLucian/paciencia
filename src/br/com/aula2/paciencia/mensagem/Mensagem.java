package br.com.aula2.paciencia.mensagem;

public class Mensagem extends java.io.IOException {
	
	private static final long serialVersionUID = -4320294966815955422L;
	
	public static final String CARTA_NAO_VIRADA = "N�o Visivel";
	public static final String VAZIO = "Vazio";
    public final static String GENERICO = "Movimento inv�lido.";
    public final static String LISTA_FECHADA = "Voc� n�o pode arrastar uma carta fechada.";
    public final static String LISTA_INVALIDO = "A carta que voc� est� tentando<br>arrastar est� na ordem errada.";
    public final static String LISTA_VAZIA_APENAS_REIS = "Voc� s� pode iniciar uma lista com um REIS.";
    public final static String LISTA_SEQUENCIA_INVALIDA = "Voc� deve alternar entre as cores e<br>seguir a sequencia: K, Q, J, 10, 9, 8, 7, 6, 5, 4, 3, 2, A.";;
    public final static String PILHA_SEQUENCIA_INVALIDA = "Voc� s� pode iniciar uma pilha com<br>AS.";
    public final static String NIPE_INVALIDA = "Nipe Inv�lido.";
    

}

public class JogoDados{    //classe para jogar os dados//
    private Dado[] dados;
    private final int qntDados = 2;

    public JogoDados() {
        dados = new Dado[qntDados];
        for (int i = 0; i < qntDados; i++) {
            dados[i] = new Dado(); // Assumindo que Dado tem um construtor padrão
   }
    
    
    public rolarDados(){
        for (Dado dado : dados) {
            dado.roll();
        }
    }
    
    public aplicarRegradoJogo(){
        //esperar a aplicação dos jogos
    }
    
    public String toString(){
     return "Dado 1: " + dados[0].sideUp + " Dado 2: " + dados[1].sideUp;
}

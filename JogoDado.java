public class JogoDados{    //classe para jogar os dados//
    private Dado[] dados;
    private final int qntDados = 2;

    public JogoDados(){
        dados = new Dado[qntDados];
        for(int i = 0; i < qntDados; i++)
            dados[i] = new Dado();
   }
    
    
    public rolarDados(){
        for(int i = 0; i < qntDados; i++)
            dados[i].roll();
    }

    public int[] getResultado(){
        int[] resultado = new int[qntDados];
        for(int i = 0; i < qntDados; i++)
            resultado[i] = dados[i].getSideUp();
        
        return resultado;
    }

    public String toString(){
        return "Dado 1: " + this.dados[0].getSideUp() + " Dado 2: " + this.dados[1].getSideUp();
    }
    
    public aplicarRegradoJogo(){
        
    }
}

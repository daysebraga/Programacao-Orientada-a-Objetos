public class Jogador{
    private int id;
    private Boolean tipo;
    private double aposta;
    private JogoDados jogoD;
    private double saldo = 100;
    
    public void jogarDados(){
        
    }
    
    public apostar(){
        
    }
    
    public void mostrarLancamentosJogo(){
        
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public String toString(){
        return "Id: " + this.id + ", saldo: " + this.saldo;
    }
}

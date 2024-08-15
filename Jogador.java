public class Jogador{
    private string id;
    private Boolean tipo;
    private double aposta;
    private JogoDado jogoD = new JogoDado;
    private double saldo = 100;
    
    public void jogarDados(){
        jogoD.rolarDados();
    }

    public double apostar(){
        if(tipo == ){ //maquina
            int auxiliar = (this.saldo/5);
            this.saldo -= auxiliar;
            return auxiliar;
        }   
    }
    
    public double apostar(int qntDaAposta){
        if(tipo == ){ //humano
            if((saldo > 0) && (qntDaAposta < saldo)){
                this.saldo -= qntDaAposta;
                return qntDaAposta;
            }
        }
        else if(tipo == ){ //maquina
            if(saldo > 0){
                qntDaAposta = (saldo/5);
                this.saldo -= qntDaAposta;
                return qntDaAposta;
            }
        }
    }
    
    public void mostrarLancamentosJogo(){
        jogoD.toString();
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public String toString(){
        return "Id: " + this.id + ", saldo: " + this.saldo;
    }
}

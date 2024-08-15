public class Jogador{
    private string id;
    private Boolean tipo;
    private double aposta;
    private JogoDado jogoD = new JogoDado;
    private double saldo = 100;
    private Boolean ganhador;
    
    public void jogarDados(){
        jogoD.rolarDados();
    }

    public double apostar(){
        if(tipo == 0){ //maquina
            int auxiliar = (this.saldo/5);
            this.saldo -= auxiliar;
            return auxiliar;
        }   
    }
    
    public double apostar(int qntDaAposta){
        if(tipo == 1){ //humano
            if((saldo > 0) && (qntDaAposta < saldo)){
                this.saldo -= qntDaAposta;
                return qntDaAposta;
            }
        }
        else if(tipo == 0){ //maquina
            if(saldo > 0){
                qntDaAposta = (this.saldo/5);
                this.saldo -= qntDaAposta;
                return qntDaAposta;
            }
        }
    }
    
    public void mostrarLancamentosJogo(){
        jogoD.toString();
    }
    
    public double getSaldo(){
        return this.saldo;
    }
    
    public String toString(){
        return "Id: " + this.id + ", saldo: " + this.saldo;
    }
}

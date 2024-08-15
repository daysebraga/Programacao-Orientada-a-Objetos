import java.util.Scanner;

public class Torneio{
    private Jogador[] jogadores;
    private int qntDeJogadores;
    private int qntDeRodadas;
    private int mesa = 0; //variavel para somar as apostas da mesa(facilitar a divisao em caso de empate)

    public void apostaDaRodada(){
        for(int i = 0; i < qntDeJogadores; i++){
            Scanner valorAposta = new Scanner(System.in);
            mesa += jogadores[i].apostar(valorAposta); 
        }
    }
    
    public void incluirJogador(){
        Jogador();
        qntDeJogadores++;
    }
    
    public void removerJogador( string id){
    
    }
    
    public void iniciarTorneio(){

        if(qntDeJogadores >= 2){
                do{
                    
                }while(!ganhador==1); //fazer o return de 1 para quando for encontrado ganhador(es), criterio de parada//
        }else
            System.out.println("quantidade de jogadores insuficiente");     
    }
    
    public void mostrarPlacarFinal(){
        
    }
    
    public void mostrarFinaldaRodada(){
        
    }   
}

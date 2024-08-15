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
        if(qntDeJogadores < 10){  
            Jogador();
            qntDeJogadores++;
        }
        else
            System.out.println("capacidade de jogadores excedida");
    }
    
    public void removerJogador( string iden){
            for(int i=0; i<qntDeJogadores; i++){
                    if(jogadores[i].id.equals(iden)){
                            jogadores[i].remover_tudo();
                            qntDeJogadores--;
                    }
            }
    }
    
    public void iniciarTorneio(){
        if(qntDeJogadores >= 2){
            do{
                System.out.println("(1) jogo de azar ou (2) jogo do porquinho: ");
                Scanner jogo_escolhido = new Scanner(System.in);
            }while(jogo_escolhido != 1 || jogo_escolhido !=2);
            
                do{
                    if(jogo_escolhido == 1){
                        //jogo de azar//
                    }
                    else if(jogo_escolhido == 2){
                        //jogo do porquinho//
                    }
                    qntDeRodadas++;
                }while(ganhador != true); //fazer o return true para quando for encontrado ganhador(es), criterio de parada//
        }else
            System.out.println("quantidade de jogadores insuficiente");     
    }
    
    public void mostrarPlacarFinal(){
        
    }
    
    public void mostrarFinaldaRodada(){
        
    }   
}

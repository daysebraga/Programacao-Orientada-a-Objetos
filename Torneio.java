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
    
    public void removerJogador(){
    
    }
    
    public void iniciarTorneio(){

        if(jogador > 2)

        else
            System.out.println("quantidade de jogadores insuficiente");
        
    }
    
    public void mostrarPlacarFinal(){
        
    }
    
    public void mostrarFinaldaRodada(){
        
    }   
}

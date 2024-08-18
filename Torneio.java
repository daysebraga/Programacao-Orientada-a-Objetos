import java.util.Scanner;

public class Torneio{
    private Jogador[] jogadores;
    private int qntDeJogadores;
    private int qntDeRodadas;
    private int mesa = 0; //variavel para somar as apostas da mesa(facilitar a divisao em caso de empate)
    private static Scanner scanner = new Scanner(System.in);

    public Torneio(){
        jogadores = new Jogador[10];
        qntDeJogadores = 0;
        qntDeRodadas = 0;
    }
    
    private static void exibirMenu(){
        System.out.println("(1) Incluir Jogador;\n"+
                       "(2) Remover Jogado;\n"+
                       "(3) Inicializar Torneio;\n"+
                       "(4) Placar do Torneio;\n"+
                       "(5) Gravar dados do Torneio em Arquivo;\n"+
                       "(6) Ler os dados do Tornei em Arquivo;\n"+
                       "(7) Sair da Aplicacao./n" +
                       "Escolha uma opcao: ");
    }
    
    private static void incluirJogador(){
        if(qntDeJogadores <= 10){
            System.out.print("Informe o id do jogador: ");
            String id = scanner.nextLine();
            System.out.println();
    
            do{
                System.out.print("Informe o tipo do jogador: ([1]Humano, [0]Maquina) ");
                int tipo = scanner.nextint();
                scanner.nextLine();
                if((tipo != 0) && (tipo != 1))
                    System.out.println("Tipo invalido.");
            }while((tipo != 0) && (tipo != 1))
    
            boolean auxiliar;
            if(tipo == 1)
                auxiliar = true;
            else
                auxiliar = false;

            Jogador jogador = new Jogador(id, auxiliar);
            jogadores[qntDeJogadores] = jogador;
            qntDeJogadores++;
        }else
            System.out.println("Quantidade de jogadores excedida.");
    }

    public void apostaDaRodada(){
        for(int i = 0; i < qntDeJogadores; i++){
            Scanner valorAposta = new Scanner(System.in);
            mesa += jogadores[i].apostar(valorAposta); 
        }
    }
    
    public void removerJogador(string iden){
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

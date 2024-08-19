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
    
    private void incluirJogador(){
        if(qntDeJogadores < 10){
            System.out.print("Informe o id do jogador: ");
            String id = scanner.nextLine();
            System.out.println();

            for(int i = 0; i < qntDeJogadores; i++)
                if(jogadores[i].getId().equals(id)){
                    System.out.println("Jogador já existe.");
                    return;
                }
            
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

    private void removerJogador(){
        if(qntDeJogadores > 0){
            System.out.print("Informe o id do jogador: ");
            String id = scanner.nextLine();
            boolean jogadorRemovido = false;
    
            for(int i = 0; i < qntDeJogadores; i++){ 
                if(jogadores[i].getId().equals(id)){ //procura o jogador
                    for(int j = i; j < qntDeJogadores - 1; j++) //joga o id do jogador selecionado para o ultimo indice
                        jogadores[j] = jogadores[j + 1];
                    
                    jogadores[qntDeJogadores - 1] = null; //limpar o ultimo jogador, no caso, o q foi informado
                    qntDeJogadores--;
                    jogadorRemovido = true;
                    break;
                }
            }
        }else
            System.out.println("Nenhum jogador para remover.");
    }

    public void apostaDaRodada(){
        Scanner valorAposta = new Scanner(System.in);
    
        for(int i = 0; i < qntDeJogadores; i++){
            if(jogadores[i].getSaldo() > 1){
                if(jogadores[i].isHumano()){
                    System.out.print("Jogador " + jogadores[i].getId() + ", insira o valor da aposta: ");
                    int aposta = valorAposta.nextInt();
                    mesa += jogadores[i].apostar(aposta);
                }else
                    mesa += jogadores[i].apostar();
            }
        }
    }
    
    public void setQntDeRodadas(int qntDeRodadas){
        this.qntDeRodadas = qntDeRodadas;
    }

    public void iniciarTorneio(){
            if(qntDeJogadores >= 2){
                Scanner scanner = new Scanner(System.in);
                int jogoEscolhido = 0;

                do{
                    System.out.println("Escolha o tipo de jogo: (1) Jogo de Azar ou (2) Jogo do Porquinho");
                    jogoEscolhido = scanner.nextInt();
                    
                    if((jogoEscolhido != 1) && (jogoEscolhido != 2))
                        System.out.println("Opcao invalida.");
                }while((jogoEscolhido != 1) && (jogoEscolhido != 2));

                System.out.print("Informe o número máximo de rodadas: ");
                int rodada = scanner.nextInt();
                setQntDeRodadas(rodada);
    
                boolean ganhadorEncontrado = false;
    
                do{
                    apostaDaRodada();
    
                    if(jogoEscolhido == 1){
                        int totalVencedores = 0;
    
                        for(int i = 0; i < qntDeJogadores; i++){
                            Jogador jogador = jogadores[i];
                            boolean ganhou = jogador.getJogoDados().jogarJogoAzar(jogador);
    
                            if(ganhou)
                                totalVencedores++;
                        }
    
                        if(totalVencedores > 0){
                            double valorPorGanhador = mesa / totalVencedores;
                            for(int i = 0; i < qntDeJogadores; i++){
                                Jogador jogador = jogadores[i];
                                if(jogador.isGanhador()){
                                    jogador.ganhou(valorPorGanhador);
                                    System.out.println("Jogador " + jogador.getId() + " ganhou " + valorPorGanhador);
                                }
                            }
                            ganhadorEncontrado = true;
                        }else
                            System.out.println("Nenhum jogador venceu nesta rodada.");
                    }else if(jogoEscolhido == 2){
                        for(int i = 0; i < qntDeJogadores; i++){
                            Jogador jogador = jogadores[i];
                            int pontuacao = jogador.getJogoDados().jogarJogoPorquinho();
                            
                            if(pontuacao >= 300){
                                ganhadorEncontrado = true;
                                jogador.setGanhador(true);
                                jogador.ganhou(mesa);
                                System.out.println("Jogador " + jogador.getId() + " ganhou " + mesa);
                                break;
                            }
                        }
                    }
                    
                    qntDeRodadas++;
                    mesa = 0;
                }while((!ganhadorEncontrado) && (qntDeRodadas < rodada));
                
                if(!ganhadorEncontrado)
                    System.out.println("Nenhum ganhador foi encontrado após " + rodada + " rodadas.");
    }
}

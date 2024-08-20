import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;


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
    
    private void incluirJogador(){
        if(qntDeJogadores < 10){
            System.out.print("Informe o id do jogador: ");
            String id = scanner.nextLine();

            for(int i = 0; i < qntDeJogadores; i++)
                if(jogadores[i].getId().equals(id)){
                    System.out.println("Jogador ja existe.");
                    return;
                }

            int tipo;
            do{
                System.out.print("Informe o tipo do jogador: ([1]Humano, [0]Maquina) ");
                tipo = scanner.nextInt();
                scanner.nextLine();
                if((tipo != 0) && (tipo != 1))
                    System.out.println("Tipo invalido.");
            }while((tipo != 0) && (tipo != 1));
    
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
            System.out.println("informe a indentificacao do jogador");
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
        for(int i = 0; i < qntDeJogadores; i++){
            if(jogadores[i].getSaldo() > 1){
                if(jogadores[i].isHumano()){
                    double aposta;
                    do{
                        System.out.print("Jogador " + jogadores[i].getId() + ", insira o valor da aposta: ");
                        aposta = scanner.nextInt();
                        if(aposta > jogadores[i].getSaldo())
                            System.out.println("Aposta invalida. Seu saldo disponivel e de: " + jogadores[i].getSaldo());
                    }while(aposta > jogadores[i].getSaldo());
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
            int jogoEscolhido = 0;

            do{
                System.out.println("Escolha o tipo de jogo: (1) Jogo de Azar ou (2) Jogo do Porquinho");
                jogoEscolhido = scanner.nextInt();
                scanner.nextLine();
                    
                if((jogoEscolhido != 1) && (jogoEscolhido != 2))
                    System.out.println("Opcao invalida.");
            }while((jogoEscolhido != 1) && (jogoEscolhido != 2));

            System.out.print("Informe o numero maximo de rodadas: ");
            int rodada = scanner.nextInt();
            setQntDeRodadas(rodada);
            scanner.nextLine();
    
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
                    int menorRodadas = Integer.MAX_VALUE;
                    Jogador vencedor = null;
                    
                    for(int i = 0; i < qntDeJogadores; i++){
                        Jogador jogador = jogadores[i];
                        int rodadas = jogador.getJogoDados().jogarJogoPorquinho(jogador);
                            
                        if(rodadas < menorRodadas){
                            menorRodadas = rodadas;
                            vencedor = jogador;
                        }                        
                    }

                    if(vencedor != null){
                        vencedor.setGanhador(true);
                        vencedor.ganhou(mesa);
                        System.out.println("Jogador " + vencedor.getId() + " ganhou " + mesa);
                        ganhadorEncontrado = true;
                    }else
                        System.out.println("Nenhum jogador venceu nesta rodada.");
                }
                    
                qntDeRodadas++;
                mesa = 0;
            }while((!ganhadorEncontrado) && (qntDeRodadas < rodada));
                
            if(!ganhadorEncontrado)                    
                System.out.println("Nenhum ganhador foi encontrado apos " + rodada + " rodadas.");
        }else
            System.out.println("Quantidade de jogadores inferior a 2.");
    }

    public void placarDoTorneio(){
        if(qntDeJogadores > 0){
            for(int i = 0; i < qntDeJogadores - 1; i++){ //ordenar os jogadores por saldo (do maior para o menor)
                for(int j = i + 1; j < qntDeJogadores; j++){
                    if(jogadores[i].getSaldo() < jogadores[j].getSaldo()){
                        Jogador temp = jogadores[i];
                        jogadores[i] = jogadores[j];
                        jogadores[j] = temp;
                    }
                }
            }
            
            System.out.println("Classificacao dos Jogadores:");
            for(int i = 0; i < qntDeJogadores; i++)
                System.out.println((i + 1) + " lugar: Jogador " + jogadores[i].getId() + " com saldo de " + jogadores[i].getSaldo());
        }
    }

    public void gravarTorneioArquivo(){
        try(PrintWriter escrever = new PrintWriter(new FileWriter("torneio.txt"))){ //cria o objeto PrintWriter e escreve no arquivo
            escrever.println(qntDeJogadores);
            escrever.println(qntDeRodadas);
            
            for(int i = 0; i < qntDeJogadores; i++){
                Jogador jogador = jogadores[i];
                escrever.println(jogador.getId());
                escrever.println(jogador.isHumano());
                escrever.println(jogador.getSaldo());
                escrever.println(jogador.getAposta());
                escrever.println(jogador.isGanhador());
            }
            
        }catch(IOException e){ 
            System.out.println("Erro ao gravar os dados do torneio: " + e.getMessage()); //pra imprimir o nome do erro em caso de excecao
        }
    }

    public void lerTorneioArquivo(){
        try(BufferedReader ler = new BufferedReader(new FileReader("torneio.txt"))){ //cria o objeto para ler o arquivo
            qntDeJogadores = Integer.parseInt(ler.readLine());
            qntDeRodadas = Integer.parseInt(ler.readLine());
    
            for (int i = 0; i < qntDeJogadores; i++){
                String id = ler.readLine();
                boolean tipo = Boolean.parseBoolean(ler.readLine());
                double saldo = Double.parseDouble(ler.readLine());
                double aposta = Double.parseDouble(ler.readLine());
                boolean ganhador = Boolean.parseBoolean(ler.readLine());
                
                Jogador jogador = new Jogador(id, tipo);
                jogador.ganhou(saldo);
                jogador.setAposta(aposta);
                jogador.setGanhador(ganhador);
                
                jogadores[i] = jogador;
            }
            
        }catch (IOException e){ //trata a excecao igual o de cima
            System.out.println("Erro ao ler os dados do torneio: " + e.getMessage());
        }
    }

    public void menuInterface(){
        boolean saida = false;
        do{
            System.out.print("(1)incluir_jogador\n"+
                             "(2)remover_jogador\n"+
                             "(3)inicializar_torneio\n"+
                             "(4)placar_do_torneio\n"+
                             "(5)gravar_dados_do_torneio_em_arquivo\n"+
                             "(6)ler_os_dados_do_torneio_em_arquivo\n"+
                             "(7)sair_da_aplicacao\n");
            
            int opcao;
            do{
                opcao = scanner.nextInt();
                scanner.nextLine();
                if((opcao < 1) || (opcao > 7))
                    System.out.println("Opcao invalida. Insira um numero entre 1 e 7.");
            }while((opcao < 1) || (opcao > 7));
            
            switch(opcao){
                case 1:
                    incluirJogador();
                    break;
                case 2:
                    removerJogador();
                    break;
                case 3:
                    iniciarTorneio();
                    break;
                case 4:
                    placarDoTorneio();
                    break;
                case 5:
                    gravarTorneioArquivo();
                    break;
                case 6:
                    lerTorneioArquivo();
                    break;
                case 7:
                    System.out.println("Saindo..."); //tirei o cls pq nao tava dando erro
                    saida = true;
                    break;
                default:
                    System.out.println("Opcao invalida.");
             }
        }while(saida == false);
    }
}

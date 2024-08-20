import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Torneio implements Serializable{
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
                    System.out.println("Jogador já existe.");
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
                }else{
                    mesa += jogadores[i].apostar();
                    System.out.println("O valor apostado do jogador " +  jogadores[i].getId() + " foi de " + jogadores[i].getAposta());
                }
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

            int rodada;
            do{
                System.out.print("Informe o número máximo de rodadas: ");
                rodada = scanner.nextInt();
                if(rodada > 10)
                    System.out.println("Quantidade de rodada invalida. Insira um valor de ate 10 rodadas.");
            }while(rodada > 10);
            setQntDeRodadas(rodada);
            scanner.nextLine();
    
            boolean ganhadorEncontrado = false;
            rodada = 0;
    
            do{
                System.out.println("Rodada " + (rodada + 1) + ": ");
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
                    
                rodada++;
                mesa = 0;
            }while((qntDeRodadas > rodada)); //(!ganhadorEncontrado) && 
                
            if(!ganhadorEncontrado)                    
                System.out.println("Nenhum ganhador foi encontrado após " + rodada + " rodadas.");
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
            
            System.out.println("Classificação dos Jogadores:");
            for(int i = 0; i < qntDeJogadores; i++)
                System.out.println((i + 1) + " lugar: Jogador " + jogadores[i].getId() + " com saldo de " + jogadores[i].getSaldo());
        }
    }


    public void gravarTorneioArquivo(){
        System.out.println("");
        File arquivo = new File("Torneio.dat");
        try {
            FileOutputStream fout = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fout); // até aqui parte que foi comentada acima
            for(int i = 0; i < qntDeJogadores; i++){
                Jogador jogador = jogadores[i];
                oos.writeObject(jogador); //gravando o dado dos players
                oos.flush();
                oos.close();
                fout.close();
                System.out.println("Gravado com sucesso!");
            }
            
        }catch(Exception e){ 
            System.out.println("Erro ao gravar os dados do torneio: " + e.getMessage()); //pra imprimir o nome do erro em caso de excecao
        }
    }

    public void lerTorneioArquivo(){
        File arquivo = new File("Torneio.dat");
        int i = 1;
        try{ 
            FileInputStream fin = new FileInputStream(arquivo);
            ObjectInputStream oin = new ObjectInputStream(fin);

            Jogador[] jogadores = (Jogador[]) oin.readObject();
            oin.close();
            fin.close();

            for(Jogador p : jogadores){
                if(p != null){
                    System.out.println("Id do jogador " + i + ": " + p.getId());
                    System.out.println("Tipo do jogador " + i +": " + (p.isHumano() ? "humano" : "maquina"));
                    System.out.println("O saldo do jogador " + i + ": " + p.getSaldo());
                    i++;
                }
            }
        }catch(Exception e){ //trata a excecao igual o de cima
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
                    System.out.println("Saindo..."); //tirei o cls pq tava dando erro
                    saida = true;
                    break;
                default:
                    System.out.println("Opcao invalida.");
             }
        }while(saida == false);
    }
}

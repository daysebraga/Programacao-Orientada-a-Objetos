import java.util.Scanner;
//interface apresentada ao usuario, simulador do torneio//

public class InterfaceTorneio{
 
}

 public class interface{

     System.out.print("(1)incluir_jogador\n"+
                       "(2)remover_jogador\n"+
                       "(3)inicializar_torneio\n"+
                       "(4)placar_do_torneio\n"+
                       "(5)gravar_dados_do_torneio_em_arquivo\n"+
                       "(6)ler_os_dados_do_torneio_em_arquivo\n"+
                       "(7)sair_da_aplicacao\n");

  Scanner opcao = new Scanner(System.in);

      switch(opcao){

         case 1:
             incluirJogador();
         case 2:
             System.out.println("informe a indentificacao do jogador");
             Scanner identificacao = new Scanner(System.in);
             removerJogador(identificacao);
         case 3:
             iniciarTorneio();
         case 4:
             mostrarPlacarFinal()
         case 5:
             gravarTorneioArquivo();
         case 6:
             lerTorneioArquivo();
         case 7:
              Runtime.getRuntime().exec("cls"); 
              break;
           
     }
       
 }

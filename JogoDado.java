public class JogoDados{    //classe para jogar os dados//
    private Dado[] dados;
    private final int qntDados = 2;

    public JogoDados(){
        dados = new Dado[qntDados];
        for(int i = 0; i < qntDados; i++)
            dados[i] = new Dado();
   }
    
    
    public void rolarDados(){
        for(int i = 0; i < qntDados; i++)
            dados[i].roll();
    }

    public int[] getResultado(){
        int[] resultado = new int[qntDados];
        rolarDados();
        for(int i = 0; i < qntDados; i++)
            resultado[i] = dados[i].getSideUp();
        
        return resultado;
    }

    public String toString(){
        return "Dado 1: " + this.dados[0].getSideUp() + " Dado 2: " + this.dados[1].getSideUp();
    }

    public boolean jogarJogoAzar(Jogador jogador){
        int[] resultado = getResultado();
        int soma = resultado[0] + resultado[1];

        if(soma == 7 || soma == 11){
            System.out.println("Lancamento 1: \n" + resultado[0] + " e " + resultado[1] + " = " + soma);
            System.out.println(jogador.getId() + " ganhou.");
            jogador.setGanhador(true);
            return true;
        }else if(soma == 2 || soma == 3 || soma == 12){
            System.out.println("Lancamento 1: \n" + resultado[0] + " e " + resultado[1] + " = " + soma);
            System.out.println(jogador.getId() + " perdeu.");
            jogador.setGanhador(false);
            return false;
        }else{
            int i = 1;
            System.out.println("Lancamento " + i + ": \n" + resultado[0] + " e " + resultado[1] + " = " + soma);
            System.out.println("Número a ser buscado: " + soma);
            int numeroBuscado = soma;
            
            while(true){
                resultado = getResultado();
                int novaSoma = resultado[0] + resultado[1];
                i++;
                
                if(novaSoma == numeroBuscado){
                    System.out.println("Lancamento " + i + ": \n" + resultado[0] + " e " + resultado[1] + " = " + novaSoma);
                    System.out.println(jogador.getId() + " ganhou.");
                    jogador.setGanhador(true);
                    return true;
                }else if(novaSoma == 2 || novaSoma == 3 || novaSoma == 12){
                    System.out.println("Lancamento " + i + ": \n" + resultado[0] + " e " + resultado[1] + " = " + novaSoma);
                    System.out.println(jogador.getId() + " perdeu.");
                    jogador.setGanhador(false);
                    return false;
                }
            }
        }
    }

    public int jogarJogoPorquinho(){
        int pontuacao = 0;
        int lancamentos = 0;
        
        while(pontuacao < 300){
            int[] resultado = getResultado();
            int produto = resultado[0] * resultado[1];
            
            if(resultado[0] == resultado[1]){
                if(resultado[0] == 1) //qnd os valores sao iguais a 1 o produto vale 30
                    produto = 30;
                else //dobrar o valor do produto para outros dobles
                    produto *= 2; 
            }
            
            pontuacao += produto;
            lancamentos++;
        }

        System.out.println("Pontuacao: " + pontuacao + "\nLancamentos: " + lancamentos);
        return pontuacao;
    }
    
    public void aplicarRegradoJogo(int qualJogo, Jogador jogador){
        if(qualJogo == 1)
            jogarJogoAzar(jogador);
        else if(qualJogo == 2)
            jogarJogoPorquinho();
    }
}

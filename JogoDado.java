public class JogoDados{    //classe para jogar os dados//
    private Dado[] dados;
    private final int qntDados = 2;

    public JogoDados(){
        dados = new Dado[qntDados];
        for(int i = 0; i < qntDados; i++)
            dados[i] = new Dado();
   }
    
    
    public rolarDados(){
        for(int i = 0; i < qntDados; i++)
            dados[i].roll();
    }

    public int[] getResultado(){
        int[] resultado = new int[qntDados];
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
            System.out.println("Lancamento 1: /n" + resultado[0] + " e " + resultado[1] + " = " + soma);
            System.out.println("Jogador ganhou.");
            jogador.setGanhador(true);
            return true;
        }else if(soma == 2 || soma == 3 || soma == 12){
            System.out.println("Lancamento 1: /n" + resultado[0] + " e " + resultado[1] + " = " + soma);
            System.out.println("Jogador perdeu.");
            jogador.setGanhador(false);
            return false;
        }else{
            int i = 1;
            System.out.println("Lancamento " + i + ": /n" + resultado[0] + " e " + resultado[1] + " = " + soma);
            System.out.println("Número a ser buscado: " + soma);
            int numeroBuscado = soma;
            
            while(true){
                rolarDados();
                resultado = getResultado();
                int novaSoma = resultado[0] + resultado[1];
                i++;
                
                if(novaSoma == numeroBuscado){
                    System.out.println("Lancamento " + i + ": /n" + resultado[0] + " e " + resultado[1] + " = " + soma);
                    System.out.println("Jogador ganhou.");
                    jogador.setGanhador(true);
                    return true;
                }else if(novaSoma == 7){
                    System.out.println("Lancamento " + i + ": /n" + resultado[0] + " e " + resultado[1] + " = " + soma);
                    System.out.println("Jogador perdeu.");
                    jogador.setGanhador(false);
                    return false;
                }
            }
        }
    }
    
    public aplicarRegradoJogo(int qualJogo, Jogador jogador){
        if(qualJogo == 1)
            jogarJogoAzar(jogador);
        else if(qualJogo == 2)
            //porquinho
    }
}

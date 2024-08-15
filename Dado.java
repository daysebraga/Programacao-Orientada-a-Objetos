import java.util.Random;

public class Dado{
    private int sideUp;

    public Dado(){ //construtor padrão, sempre que o objeto dado for criado o lado de cima do dado será 1
        sideUp = 1;
    }
    
    public void roll(){ //Gera um número aleatorio entre 1 e 6
        Random r = new Random();
        sideUp = r.nextInt(6) + 1;
    }
    
    public int getSideUp(){ //retorna o lado de cima do dado
        return sideUp;
    }
    
    public String toString(){ //imprime o lado de cima do dado
        return "Dado: " + getSideUp();
    }
}

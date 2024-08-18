import java.util.Random;

public class Dado{
    private int sideUp;

    public Dado(){ //construtor padrão, sempre que o objeto dado for criado o lado de cima do dado vai ser aleatorio 
        Random r = new Random();
        sideUp = r.nextInt(6) + 1;
    }
    
    public void roll(){ 
        Random r = new Random();
        sideUp = r.nextInt(6) + 1;
    }
    
    public int getSideUp(){ 
        return this.sideUp;
    }
    
    public String toString(){ 
        return "Dado: " + getSideUp();
    }
}

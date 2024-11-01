import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Leitor extends Thread {

    private ArrayList<String> lista;
    private ArrayList<String> base;

    public Leitor(ArrayList<String> base){

        lista = new ArrayList<>();
        this.base = base;
    }

    public void run(){

        for(int i = 0; i < 100; i++){

            int posicao = ThreadLocalRandom.current().nextInt(0, 36241);

            lista.add(base.get(posicao));
        }
    }
}




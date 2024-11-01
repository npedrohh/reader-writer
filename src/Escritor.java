import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Escritor extends Thread {

    private ArrayList<String> lista;
    private ArrayList<String> base;
    private static Semaphore mutex = new Semaphore(1);
    private static int rc = 0;

    public Escritor(ArrayList<String> base){

        lista = new ArrayList<>();
        this.base = base;
    }

    public void run(){

        for(int i = 0; i < 100; i++){



            int posicao = ThreadLocalRandom.current().nextInt(0, 36241);

            base.set(posicao, "MODIFICADO");
        }
    }

}
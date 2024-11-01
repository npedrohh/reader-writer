import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Escritor extends Thread {

    private ArrayList<String> lista;
    private ArrayList<String> base;

    public Escritor(ArrayList<String> base){

        lista = new ArrayList<>();
        this.base = base;
    }

    public void run(){

        for(int i = 0; i < 100; i++){

            int posicao = ThreadLocalRandom.current().nextInt(0, 36241);

            try {
                Semaforos.bd.acquire();
                base.set(posicao, "MODIFICADO");
                Semaforos.bd.release();
            } catch (InterruptedException e) {
                System.out.println("Erro ao modificar a base: " + e.getMessage());
            }
        }

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Erro ao simular verificação: " + e.getMessage());
        }

    }

}
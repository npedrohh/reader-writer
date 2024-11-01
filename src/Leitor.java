import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Leitor extends Thread {

    private ArrayList<String> lista;
    private final ArrayList<String> base;

    public Leitor(ArrayList<String> base){

        lista = new ArrayList<>();
        this.base = base;
    }

    public void run(){

        for(int i = 0; i < 100; i++){

            int posicao = ThreadLocalRandom.current().nextInt(0, 36241);

            try {
                Execucao.mutex.acquire();
                Execucao.cl++;
                if(Execucao.cl == 1) Execucao.bd.acquire();
                Execucao.mutex.release();

                // Leitura
                lista.add(base.get(posicao));
                if(i == 99) Thread.sleep(1);

                Execucao.mutex.acquire();
                Execucao.cl--;
                if(Execucao.cl == 0) Execucao.bd.release();
                Execucao.mutex.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
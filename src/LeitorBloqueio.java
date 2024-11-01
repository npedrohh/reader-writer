import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

// Objeto leitor que bloqueia a base para todos os objetos durante o acesso
public class LeitorBloqueio extends Thread {

    private ArrayList<String> lista;
    private final ArrayList<String> base;

    public LeitorBloqueio(ArrayList<String> base) {

        lista = new ArrayList<>();
        this.base = base;
    }

    public void run(){

        for (int i = 0; i < 100; i++) {

            int posicao = ThreadLocalRandom.current().nextInt(0, 36241);

            try {
                Execucao.bd.acquire();

                // Leitura
                lista.add(base.get(posicao));
                if (i == 99) Thread.sleep(1);

                Execucao.bd.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
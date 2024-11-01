import java.util.ArrayList;
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
                Execucao.bd.acquire();

                base.set(posicao, "MODIFICADO");
                if(i == 99) Thread.sleep(1);

                Execucao.bd.release();
            } catch (InterruptedException e) {
                System.out.println("Erro ao modificar a base: " + e.getMessage());
            }
        }
    }

}
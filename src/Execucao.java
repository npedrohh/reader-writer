import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;

public class Execucao {
    private ArrayList<Thread> leitores_escritores; // Lista de leitores e escritores

    static Semaphore mutex; // Mutex de controle do cl
    static Semaphore bd; // Semáforo de acesso ao banco
    static int cl; // Contador de leitores

    public Execucao(int leitores, int escritores, ArrayList<String> base) {
        leitores_escritores = new ArrayList<>();

        // Adiciona os leitores e escritores à lista seguindo a proporção
        for (int i = 0; i < leitores; i++) leitores_escritores.add(new Leitor(base));
        for (int i = 0; i < escritores; i++) leitores_escritores.add(new Escritor(base));

        // Coloca cada leitor/escritor em uma posição aleatória do array
        Collections.shuffle(leitores_escritores);

        // Inicializa os semáforos e o contador de leitores
        mutex = new Semaphore(1);
        bd = new Semaphore(1);
        cl = 0;
    }

    // Executa os objetos leitor/escritor em threads
    public void iniciaThreads() {
        for (Thread thread : leitores_escritores) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Erro ao dar join na thread: " + e.getMessage());
            }
        }

    }
}

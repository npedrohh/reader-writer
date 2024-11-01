import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;

public class Execucao {
    private ArrayList<Thread> leitores_escritores; // Lista de leitores e escritores

    static Semaphore mutex; // Mutex de controle do cl
    static Semaphore bd; // Semáforo de acesso ao banco
    static int cl; // Contador de leitores

    // Construtor da classe - o parâmetro algoritmo define se a execução utiliza ou não a implementação de Leitores e Escritores
    public Execucao(int leitores, int escritores, ArrayList<String> base, int algoritmo) {
        leitores_escritores = new ArrayList<>();

        // Inicializa o semáforo de acesso à base de dados (usado nos dois algoritmos)
        bd = new Semaphore(1);

        // Adiciona os escritores à lista seguindo a proporção
        for (int i = 0; i < escritores; i++) leitores_escritores.add(new Escritor(base));

        // Algoritmo que utiliza a implementação de Leitores e Escritores
        if (algoritmo == 0) {
            // Adiciona os leitores e escritores à lista seguindo a proporção
            for (int i = 0; i < leitores; i++) leitores_escritores.add(new Leitor(base));

            // Inicializa o mutex e o contador de leitores
            mutex = new Semaphore(1);
            cl = 0;
        }
        // Algoritmo que não utiliza a implementação de Leitores e Escritores (todos os objetos bloqueiam a base)
        else {
            // Adiciona os leitores e escritores à lista seguindo a proporção
            for (int i = 0; i < leitores; i++) leitores_escritores.add(new LeitorBloqueio(base));
        }

        // Coloca cada leitor/escritor em uma posição aleatória do array
        Collections.shuffle(leitores_escritores);
    }

    // Executa os objetos leitor/escritor em threads
    public void iniciaThreads() {
        for (Thread thread : leitores_escritores) {
            thread.start();
        }

        for (Thread thread : leitores_escritores) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Erro ao dar join na thread: " + e.getMessage());
            }
        }
    }

}

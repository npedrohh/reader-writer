import java.util.concurrent.Semaphore;

public class Semaforos {
    static Semaphore mutex; // Mutex de controle do cl
    static Semaphore bd; // Semáforo de acesso ao banco
    static int cl; // Contador de leitores

    // Inicializa os semáforos e o contador de leitores
    public Semaforos() {
        mutex = new Semaphore(1);
        bd = new Semaphore(1);
        cl = 0;
    }
}

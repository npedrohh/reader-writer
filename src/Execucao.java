import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Execucao {

    static Semaphore mutex = new Semaphore(1);
    static Semaphore db = new Semaphore(1);
    static int rc = 0;
    ArrayList<Thread> leitores_escritores;

}

import java.io.*;
import java.util.ArrayList;

public class Main {
    static ArrayList<String> base; // Base de dados

    public static void main(String[] args) {

        Execucao execucao;
        long media, mediaBloqueio, inicio, fim;

        System.out.println("algoritmo,numero-leitores,numero-escritores,media em ms");

        // Cria execuções seguindo diferentes proporções de leitores/escritores
        for (int i = 0; i <= 100; i++) {

            media = 0;
            mediaBloqueio = 0;

            // Para cada proporção, cria 50 execuções diferentes para os dois algoritmos
            for (int j = 0; j < 50; j++) {

                base = inicializarBase(); // Insere os dados na estrutura
                execucao = new Execucao(100-i, i, base, 0); // Cria uma execução fazendo uso de Leitores e Escritores

                // Calcula o tempo da execução
                inicio = System.currentTimeMillis();
                execucao.iniciaThreads();
                fim = System.currentTimeMillis();

                media += (fim - inicio);

                base = inicializarBase(); // Insere os dados na estrutura
                execucao = new Execucao(100-i, i, base, 1); // Cria uma execução sem fazer uso de Leitores e Escritores

                inicio = System.currentTimeMillis();
                execucao.iniciaThreads();
                fim = System.currentTimeMillis();

                mediaBloqueio += (fim - inicio);
            }
            media /= 50;
            mediaBloqueio /= 50;

            System.out.println("leitores/escritores," + Integer.toString(100-i) + "," + Integer.toString(i) + "," + Long.toString(media));
            System.out.println("sem leitores/escritores," + Integer.toString(100-i) + "," + Integer.toString(i) + "," + Long.toString(mediaBloqueio));
        }
    }

    // Realiza a leitura do arquivo e inserção na estrutura de dados
    private static ArrayList<String> inicializarBase() {

        base = new ArrayList<>();

        String filePath = "src/db.txt"; // Caminho relativo
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;

            while ((linha = br.readLine()) != null) {

                base.add(linha);
            }

        } catch (IOException e) {
            System.out.println("Erro ao inicializar a base: " + e.getMessage());
        }

        return base;
    }
}
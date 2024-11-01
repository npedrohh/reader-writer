import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static ArrayList<String> base; // Base de dados

    public static void main(String[] args) {

        Execucao execucao;

        // Cria execuções seguindo diferentes proporções de leitores/escritores
        for (int i = 0; i <= 100; i++) {

            long media = 0;
            System.out.print((100-i) + "L / " + i + "E - ");

            // Para cada proporção, cria 50 execuções diferentes
            for (int j = 0; j < 50; j++) {

                // Insere os dados na estrutura
                base = inicializarBase();

                // execucao = new Execucao(i, 100-i, base);
                execucao = new Execucao(100-i, i, base);

                long inicio = System.currentTimeMillis();
                execucao.iniciaThreads();
                long fim = System.currentTimeMillis();

                media += (fim - inicio);
            }
            media /= 50;
            System.out.println(media + "ms");
        }
    }

    // Função que lê o arquivo e insere na estrutura de dados
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
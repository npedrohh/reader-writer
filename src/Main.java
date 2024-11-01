import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static ArrayList<String> base = new ArrayList<>(); // Base de dados

    public static void main(String[] args) {
        // Insere os dados na estrutura
        inicializarBase();
        // System.out.println(base.toString());
        Execucao execucao;

        // Cria execuções seguindo diferentes proporções de leitores/escritores
        for (int i = 0; i <= 100; i++) {

            // Para cada proporção, cria 50 execuções diferentes
            for (int j = 0; j < 50; j++) {

                execucao = new Execucao (i, 100-i, base);
                execucao.iniciaThreads();
            }
            System.out.println(i);
        }
    }

    // Função que lê o arquivo e insere na estrutura de dados
    private static void inicializarBase() {
        String filePath = "src/bd.txt"; // Caminho
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;

            while ((linha = br.readLine()) != null) {

                base.add(linha);
            }

        } catch (IOException e) {
            System.out.println("Erro ao inicializar a base: " + e.getMessage());
        }
    }
}
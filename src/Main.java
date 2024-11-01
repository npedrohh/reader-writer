import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static ArrayList<String> base = new ArrayList<>();

    public static void main(String[] args) {

        inicializarBase();

        ArrayList<Thread> leitores_escritores;
        int leitores = 100;
        int escritores = 0;

        for(int i = 0; leitores >= 0 && escritores <= 100; i++){

            for(int j = 0; j < 50; j++) {

                leitores_escritores = inicializarLeitoresEscritores(leitores, escritores);


            }

            leitores--;
            escritores++;
        }
    }

    private static void inicializarBase(){

        String filePath = "C:\\Users\\koutary\\IdeaProjects\\readerwriter\\src\\bd.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;

            while ((linha = br.readLine()) != null){

                base.add(linha);
            }

        } catch (IOException e) {
            System.out.println("Erro ao inicializar a base: " + e.getMessage());
        }
    }

    private static ArrayList<Thread> inicializarLeitoresEscritores(int leitores, int escritores) {

        ArrayList<Thread> leitores_escritores = new ArrayList<>();

        while(leitores > 0){

            Leitor leitor = new Leitor(base);

            leitores_escritores.add(leitor);

            leitores--;
        }

        while(escritores > 0){

            Escritor escritor = new Escritor(base);

            leitores_escritores.add(escritor);

            escritores--;
        }

        Collections.shuffle(leitores_escritores);

        return leitores_escritores;
    }


}
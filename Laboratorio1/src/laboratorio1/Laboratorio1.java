package laboratorio1;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 *
 * @author maria eduarda pedroso
 */
public class Laboratorio1 {

    // Define o caminho do arquivo de texto com as "fortunes"
    public final static Path path = Paths.get("src/laboratorio1/fortune-br.txt");

    // Variável para armazenar a quantidade de "fortunes" encontradas no arquivo
    private int NUM_FORTUNES = 0;

    /**
     * Classe interna que manipula as operações de leitura e escrita no arquivo.
     */
    public class FileReader {

        /**
         * Conta o número de "fortunes" no arquivo, considerando que cada uma
         * é separada pelo caractere '%'.
         * @return número total de "fortunes".
         * @throws FileNotFoundException se o arquivo não for encontrado.
         */
        public int countFortunes() throws FileNotFoundException {
            int lineCount = 0;

            // Abre o arquivo para leitura
            InputStream is = new BufferedInputStream(new FileInputStream(path.toString()));
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                String line = "";
                // Lê cada linha do arquivo
                while (!(line == null)) {
                    // Conta uma "fortune" ao encontrar o caractere '%'
                    if (line.equals("%")) {
                        lineCount++;
                    }
                    line = br.readLine();
                } // fim do while

                System.out.println(lineCount);  // Exibe o total de "fortunes"
            } catch (IOException e) {
                System.out.println("SHOW: Exceção na leitura do arquivo.");
            }
            return lineCount;  // Retorna o número de "fortunes" contadas
        }

        /**
         * Lê cada "fortune" do arquivo e armazena no HashMap passado como argumento.
         * @param hm HashMap que armazena cada "fortune" associada a uma chave numérica.
         * @throws FileNotFoundException se o arquivo não for encontrado.
         */
        public void parser(HashMap<Integer, String> hm) throws FileNotFoundException {
            InputStream is = new BufferedInputStream(new FileInputStream(path.toString()));
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                int lineCount = 0;
                String line = "";

                // Lê cada linha do arquivo
                while (!(line == null)) {
                    // Aumenta o contador ao encontrar o caractere '%' (nova "fortune")
                    if (line.equals("%")) {
                        lineCount++;
                    }

                    line = br.readLine();
                    StringBuffer fortune = new StringBuffer();

                    // Armazena a "fortune" até encontrar um novo '%'
                    while (!(line == null) && !line.equals("%")) {
                        fortune.append(line + "\n");
                        line = br.readLine();
                    }

                    // Armazena a "fortune" no HashMap
                    hm.put(lineCount, fortune.toString());
                    System.out.println(fortune.toString());  // Exibe a "fortune"
                    System.out.println(lineCount);  // Exibe o número da "fortune"
                } // fim do while

            } catch (IOException e) {
                System.out.println("SHOW: Exceção na leitura do arquivo.");
            }
        }

        /**
         * Lê o arquivo linha por linha e armazena cada linha no HashMap.
         * @param hm HashMap onde cada linha é armazenada com seu número de linha.
         * @throws FileNotFoundException se o arquivo não for encontrado.
         */
        public void read(HashMap<Integer, String> hm) throws FileNotFoundException {
            try (FileInputStream fis = new FileInputStream(path.toFile());
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader br = new BufferedReader(isr)) {

                String line;
                int lineNumber = 1;

                // Lê o arquivo linha por linha
                while ((line = br.readLine()) != null) {
                    System.out.println(line);  // Exibe cada linha no console
                    hm.put(lineNumber++, line);  // Armazena a linha no HashMap
                }
            } catch (IOException e) {
                System.out.println("SHOW: Exceção na leitura do arquivo.");
            }
        }

        /**
         * Escreve o conteúdo do HashMap no arquivo, linha por linha.
         * @param hm HashMap com o conteúdo a ser escrito no arquivo.
         * @throws FileNotFoundException se o arquivo não for encontrado.
         */
        public void write(HashMap<Integer, String> hm) throws FileNotFoundException {
            try (FileOutputStream fos = new FileOutputStream(path.toFile());
                 OutputStreamWriter osw = new OutputStreamWriter(fos);
                 BufferedWriter bw = new BufferedWriter(osw)) {

                // Escreve cada valor do HashMap em uma nova linha
                for (String value : hm.values()) {
                    bw.write(value);
                    bw.newLine();  // Para separar as linhas
                }
            } catch (IOException e) {
                System.out.println("SHOW: Exceção na escrita do arquivo.");
            }
        }
    }

    /**
     * Método principal que coordena o fluxo do programa.
     * Conta as "fortunes", processa o arquivo e reescreve o conteúdo.
     */
    public void iniciar() {
        // Instancia a classe FileReader
        FileReader fr = new FileReader();

        try {
            // Conta o número de "fortunes" e armazena em NUM_FORTUNES
            NUM_FORTUNES = fr.countFortunes();

            // Cria o HashMap para armazenar as "fortunes"
            HashMap<Integer, String> hm = new HashMap<>();

            // Processa e armazena as "fortunes" no HashMap
            fr.parser(hm);

            // Lê o arquivo e exibe cada linha
            fr.read(hm);

            // Escreve o conteúdo do HashMap de volta no arquivo
            fr.write(hm);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método main.
     * Executa o método iniciar.
     */
    public static void main(String[] args) {
        new Laboratorio1().iniciar();
    }

}

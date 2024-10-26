import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Servidor {

    private static Socket clientSocket;
    private static ServerSocket serverSocket;

    private static DataInputStream input;
    private static DataOutputStream output;

    private int port = 1030;
    
    public final static Path fortuneFile = Paths.get("src/fortune-br.txt");

    public void start() {
        System.out.println("Server running on port: " + port);
        try {
            // Create server socket
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();  // Wait for client connections

            // Initialize input and output streams
            input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());

            // Receive JSON data
            String jsonRequest = input.readUTF();
            
            // Process the JSON data
            jsonRequest = jsonRequest.substring(1, jsonRequest.length() - 1);
            String[] fields = jsonRequest.split(",");
            String method = null;
            String arguments = null;
            
            for (String field : fields) {
                String[] parts = field.split(":");
                String key = parts[0].trim();
                String value = parts[1].trim();
                
                if (key.equals("\"method\"")) {
                    method = value.substring(1, value.length() - 1);
                } else if (key.equals("\"args\"")) {
                    arguments = value.substring(2, value.length() - 2);
                }
            }
            
            // Execute the command
            if ("read".equals(method)) {
                String fortune = getFortune();
                output.writeUTF("{\n"
                        + "\"result\": \"" + fortune.trim() + "\""
                        + "\n}");
            } else if ("write".equals(method)) {
                // Handle the fortune writing
                if (arguments.endsWith("\\n")) {
                    arguments = arguments.substring(0, arguments.length() - 2) + "\n";
                    saveFortune(arguments);
                    output.writeUTF("{\n"
                            + "\"result\": \"" + arguments + "\""
                            + "\n}");
                } else {
                    output.writeUTF("{"
                            + "\"result\": \"false\""
                            + "}");
                }
            }
            
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getFortune() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fortuneFile.toString()))) {
            List<String> fortunes = new ArrayList<>();
            String line;
            StringBuilder currentFortune = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("%")) {
                    if (currentFortune.length() > 0) {
                        fortunes.add(currentFortune.toString());
                        currentFortune.setLength(0); // Clear the buffer
                    }
                } else {
                    currentFortune.append(line).append("\n");
                }
            }

            if (!fortunes.isEmpty()) {
                return getRandomFortune(fortunes);
            } else {
                System.out.println("The fortune file is empty.");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "";
    }
    
    private static String getRandomFortune(List<String> fortunes) {
        Random random = new Random();
        int randomIndex = random.nextInt(fortunes.size());
        return fortunes.get(randomIndex);
    }
    
    private void saveFortune(String newFortune) {
        try (FileWriter writer = new FileWriter(fortuneFile.toString(), true)) {
            writer.write(newFortune + "%\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Servidor().start();
    }
}

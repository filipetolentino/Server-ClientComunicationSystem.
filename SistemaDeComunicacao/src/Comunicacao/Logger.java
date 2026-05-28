package Comunicacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static void info(String message) {
        
        System.out.println(timestamp() + "| INFO |" + message);
    }
    
    public static void error(String message) {
        System.out.println(timestamp() + "| ERROR |" + message);
    }
    
    public static void recv(int clientId, String command) {
        System.out.println(timestamp() + "| RECV | cliente=" + clientId + "|" + command);
    }
    
    private static String timestamp() {
        return LocalDateTime.now().format(formatter);
        
    }
}
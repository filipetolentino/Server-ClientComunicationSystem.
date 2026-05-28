package Comunicacao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    private static final int Default_Port = 5000;
    
    public static final Map<String, ClientSession> activeClients = new ConcurrentHashMap<>();
    
    private static final AtomicInteger clientCounter = new AtomicInteger(1);
    
    private static final ExecutorService pool = Executors.newFixedThreadPool(20);
    
    public static void main(String[] args) {
        
        int port = Default_Port;
        
        if(args.length > 0) {
            
            try {
                port = Integer.parseInt(args[0]);
            }catch(NumerFormatException e) {
                System.out.println("Porta inválida. A usar porta 5000.");
            }
        }
        
        try(ServerSocket serverSocket = new ServerSocket(port)){
            
            Logger.info("Servidor iniciado na porta" + port);
            Logger.info("A aguardar clientes...");
            
            while(true) {
                
                Socket clientSocket = serverSocket.accept();
                
                clientSocket.setSoTimeout(30000);
                
                int clientId = clientCounter.getAndIncrement();
                
                Logger.info("Nova ligação - cliente:" + clientId + "- endereço:" + clientSocket.getRemoteSocketAddress());
                
                ClientHandler handler = new ClientHandler(clientId, clientSocket);
                
                pool.execute(handler);
            }
            
        } catch(IOException e) {
            Logger.error("Erro no servidor:" + e.getMessage());
        }finally {
            pool.shutdown();
            
        }
    }    
}

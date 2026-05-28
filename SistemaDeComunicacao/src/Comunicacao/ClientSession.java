package Comunicacao;

import java.io.BufferedWriter;
import java.net.Socket;

public class ClientSession {

    private final int clientId;
    private final Socket socket;
    private final BufferedWriter writer;
    private String nickname;
    
    public ClientSession(int clientId, Socket socket, BufferedWriter writer) {
        this.clientId=clientId;
        this.socket=socket;
        this.writer=writer;    
    }
    
    public int getClientId() {
        return clientId;
    }
    
    public Socket getSocket() {
        return socket;
    }
    
    public BufferedWriter getWriter() {
        return writer;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname=nickname;
    }
}

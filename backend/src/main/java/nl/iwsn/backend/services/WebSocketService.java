package nl.iwsn.backend.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate socket;

    public WebSocketService(SimpMessagingTemplate socket) {
        this.socket = socket;
    }

    public void send(String payload) {
        this.socket.convertAndSend("/topic/data", payload);
    }

}

package nl.iwsn.backend.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class WebSocketService {

    private final SimpMessagingTemplate socket;

    public WebSocketService(SimpMessagingTemplate socket) {
        this.socket = socket;
    }

    @Scheduled(fixedDelay = 3000)
    public void sendSmartMeterData() {
        this.socket.convertAndSend("/topic/user", "test!");
    }

}

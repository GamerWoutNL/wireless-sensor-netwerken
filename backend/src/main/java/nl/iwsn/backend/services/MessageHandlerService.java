package nl.iwsn.backend.services;

import org.springframework.stereotype.Service;

@Service
public class MessageHandlerService {

    public void handle(String message) {
        System.out.println(message);
    }

}

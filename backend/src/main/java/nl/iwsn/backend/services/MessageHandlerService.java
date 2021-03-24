package nl.iwsn.backend.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.iwsn.backend.model.Message;
import nl.iwsn.backend.model.dht.DhtData;
import nl.iwsn.backend.model.smartmeter.Measurement;
import nl.iwsn.backend.model.smartmeter.SmartMeterData;
import org.springframework.stereotype.Service;

@Service
public class MessageHandlerService {

    private final Gson gson;
    private final DatabaseService databaseService;
    private final WebSocketService webSocketService;

    public MessageHandlerService(DatabaseService databaseService, WebSocketService webSocketService) {
        this.databaseService = databaseService;
        this.webSocketService = webSocketService;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Message.class, new Message())
                .registerTypeAdapter(Measurement.class, new Measurement())
                .registerTypeAdapter(DhtData.class, new DhtData())
                .create();
    }

    public void handle(String rawMessage) {
        Message message = this.gson.fromJson(rawMessage, Message.class);

        switch (message.getSensor()) {
            case "smartmeter" -> this.handleSmartMeterData((SmartMeterData) message.getData());
            case "DHT11" -> this.handleDhtData((DhtData) message.getData());
        }
    }

    private void handleSmartMeterData(SmartMeterData data) {
        //this.databaseService.saveSmartMeterData(data);
        this.webSocketService.send("hello");
    }

    private void handleDhtData(DhtData data) {
        this.databaseService.saveDhtData(data);
    }

}

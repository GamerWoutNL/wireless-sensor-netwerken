package nl.iwsn.backend.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.iwsn.backend.model.DhtData;
import nl.iwsn.backend.model.Message;
import nl.iwsn.backend.model.SmartMeterData;
import nl.iwsn.backend.model.serializers.MessageSerializer;
import org.springframework.stereotype.Service;

@Service
public class MessageHandlerService {

    private final Gson gson;

    public MessageHandlerService() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Message.class, new MessageSerializer())
                .create();
    }

    public void handle(String rawMessage) {
        Message message = this.gson.fromJson(rawMessage, Message.class);

        switch (message.getSensor()) {
            case "smartmeter":
                this.handleSmartMeter((SmartMeterData) message.getData());
                break;
            case "DHT11":
                this.handleDhtData((DhtData) message.getData());
                break;
        }
    }

    private void handleSmartMeter(SmartMeterData data) {
        System.out.println("GOT SMART METER DATA");
    }

    private void handleDhtData(DhtData data) {
        System.out.println("GOT DHT DATA");
    }

}

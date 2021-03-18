package nl.iwsn.backend.services;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmbeddedMessagingService {

    private IMqttClient client;

    public EmbeddedMessagingService () {
        try {
            this.client = new MqttClient("tcp://test.mosquitto.org:1883", UUID.randomUUID().toString());
        } catch (MqttException e) {
            e.printStackTrace();
        }

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        try {
            this.client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String payload) {
        if (!this.client.isConnected()) {
            return;
        }

        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(0);
        message.setRetained(false);

        try {
            this.client.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}

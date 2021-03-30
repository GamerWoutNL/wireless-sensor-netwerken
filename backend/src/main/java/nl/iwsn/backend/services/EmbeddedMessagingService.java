package nl.iwsn.backend.services;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EmbeddedMessagingService implements MqttCallbackExtended {

    @Value("${mqtt.host}")
    private String host;

    @Value("${mqtt.topic}")
    private String topic;

    private IMqttClient client;
    private final MessageHandlerService messageHandler;

    public EmbeddedMessagingService (MessageHandlerService messageHandler) {
        this.messageHandler = messageHandler;
    }

    @PostConstruct
    public void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);

        try {
            this.client = new MqttClient(this.host, "client-1");
            this.client.setCallback(this);
            this.client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String payload) {
        if (!this.client.isConnected()) {
            return;
        }

        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(0);
        message.setRetained(false);

        try {
            this.client.publish(this.topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectComplete(boolean b, String s) {
        try {
            this.client.subscribe(this.topic, 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        this.messageHandler.handle(new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}

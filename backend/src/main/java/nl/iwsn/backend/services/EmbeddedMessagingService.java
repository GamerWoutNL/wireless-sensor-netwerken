package nl.iwsn.backend.services;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Service;

@Service
public class EmbeddedMessagingService implements MqttCallbackExtended {

    private static final String host = "tcp://test.mosquitto.org:1883";
    private static final String topic = "smartmeter-wout";

    private IMqttClient client;
    private final MessageHandlerService messageHandler;

    public EmbeddedMessagingService (MessageHandlerService messageHandler) {
        this.messageHandler = messageHandler;

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);

        try {
            this.client = new MqttClient(host, "client-1");
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
            this.client.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectComplete(boolean b, String s) {
        try {
            this.client.subscribe(topic, 0);
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

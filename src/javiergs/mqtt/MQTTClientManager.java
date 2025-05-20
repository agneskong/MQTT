package javiergs.mqtt;

import org.eclipse.paho.client.mqttv3.*;

public class MQTTClientManager {
    private final MqttClient client;
    private final String topic;
    private MessageHandler handler;

    public interface MessageHandler {
        void handleMessage(String message);
    }

    public MQTTClientManager(String broker, String clientId, String topic, MessageHandler handler) throws MqttException {
        this.topic = topic;
        this.handler = handler;
        client = new MqttClient(broker, clientId);
        client.connect();
        client.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) {
                System.out.println("Connection lost");
            }
            public void messageArrived(String topic, MqttMessage message) {
                handler.handleMessage(new String(message.getPayload()));
            }
            public void deliveryComplete(IMqttDeliveryToken token) {}
        });
        client.subscribe(topic);
    }

    public void publish(String message) throws MqttException {
        client.publish(topic, new MqttMessage(message.getBytes()));
    }

    public void close() throws MqttException {
        client.disconnect();
        client.close();
    }
}

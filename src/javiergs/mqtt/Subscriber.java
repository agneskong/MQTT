package javiergs.mqtt;

import org.eclipse.paho.client.mqttv3.*;

public class Subscriber implements Runnable {
	private final Repository repo;

	public Subscriber(Repository repo) {
		this.repo = Repository.getInstance();
	}

	@Override
	public void run() {
		try {
			MqttClient client = new MqttClient("tcp://test.mosquitto.org:1883", MqttClient.generateClientId());
			client.setCallback(new MqttCallback() {
				public void connectionLost(Throwable cause) {
					System.out.println("Subscriber disconnected");
				}

				public void messageArrived(String topic, MqttMessage message) {
					String[] parts = new String(message.getPayload()).split(",");
					if (parts.length == 2) {
						try {
							int x = Integer.parseInt(parts[0]);
							int y = Integer.parseInt(parts[1]);
							System.out.println("Received: " + x + "," + y);
							repo.addCoordinate(x, y);
						} catch (NumberFormatException e) {
							System.err.println("Invalid coordinates received: " + message);
						}
					}
				}

				public void deliveryComplete(IMqttDeliveryToken token) {}
			});
			client.connect();
			client.subscribe("cal-poly/csc/309");
			System.out.println("Subscriber connected and listening.");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}


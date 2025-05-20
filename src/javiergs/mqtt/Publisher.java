package javiergs.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import java.util.Observable;
import java.util.Observer;

public class Publisher implements Observer, Runnable {
	private final Repository repo;
	private MqttClient client;

	public Publisher(Repository repo) {
		this.repo = repo;
		repo.addObserver(this);
	}

	@Override
	public void run() {
		try {
			client = new MqttClient("tcp://test.mosquitto.org:1883", MqttClient.generateClientId());
			client.connect();
			System.out.println("Publisher connected to broker.");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (client == null || !client.isConnected()) return;
		if (arg instanceof int[]) {
			int[] coords = (int[]) arg;
			String message = coords[0] + "," + coords[1];
			try {
				client.publish("cal-poly/csc/309", new MqttMessage(message.getBytes()));
				System.out.println("Published: " + message);
			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
	}
}

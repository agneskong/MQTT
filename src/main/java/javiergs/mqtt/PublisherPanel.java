package javiergs.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/* GUI for App 1*/

public class PublisherPanel extends JPanel implements MouseListener {
    private MQTTClientManager mqtt;
    public PublisherPanel() {
        setBackground(Color.WHITE);
        addMouseListener(this);
        try {
            mqtt = new MQTTClientManager("tcp://test.mosquitto.org:1883",
                    "publisher-client",
                    "cal-poly/csc/309",
                    message -> {});
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        String coord = x + "," + y;
        System.out.println("Publishing click: " + coord);
        try {
            mqtt.publish(coord);
        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    public static void main(String[] args) {
        JFrame frame = new JFrame("Publisher App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new PublisherPanel());
        frame.setVisible(true);
    }
}

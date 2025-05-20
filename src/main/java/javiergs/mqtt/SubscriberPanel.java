package javiergs.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
/* GUI for App 2*/

public class SubscriberPanel extends JPanel implements MouseListener {
    private final List<Point> points = new ArrayList<>();
    private MQTTClientManager mqtt;

    public SubscriberPanel() {
        setBackground(Color.WHITE);
        addMouseListener(this);

        try {
            mqtt = new MQTTClientManager("tcp://test.mosquitto.org:1883",
                    "subscriber-client",
                    "cal-poly/csc/309",
                    message -> {
                        String[] coords = message.split(",");
                        if (coords.length == 2) {
                            try {
                                int x = Integer.parseInt(coords[0]);
                                int y = Integer.parseInt(coords[1]);
                                points.add(new Point(x, y));
                                repaint();
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid message: " + message);
                            }
                        }
                    });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        String coord = x + "," + y;
        System.out.println("App2 sent: " + coord);  // ← ADD THIS LINE
        try {
            mqtt.publish(coord);
        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        for (Point p : points) {
            g.fillOval(p.x - 10, p.y - 10, 20, 20);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Subscriber App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new SubscriberPanel());
        frame.setVisible(true);
    }
}

package javiergs.mqtt;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Repository repo = Repository.getInstance();

        JFrame frame = new JFrame("MQTT Shared Canvas");
        DrawArea drawArea = new DrawArea(repo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.add(drawArea);
        frame.setVisible(true);

        new Thread(new Publisher(repo)).start();
        new Thread(new Subscriber(repo)).start();
    }
}
package javiergs.mqtt;

import javax.swing.*;
import java.awt.*;

public class DrawArea extends JPanel {
    public DrawArea(Repository repo) {
        setLayout(new GridLayout(1, 2));  // 1 row, 2 columns

        // Left panel: Publisher (clicks get published)
        JPanel publisherPanel = new PublisherPanel(repo);
        publisherPanel.setBorder(BorderFactory.createTitledBorder("Publisher"));
        add(publisherPanel);

        // Right panel: Subscriber (displays all received coordinates)
        JPanel subscriberPanel = new SubscriberPanel(repo);
        subscriberPanel.setBorder(BorderFactory.createTitledBorder("Subscriber"));
        add(subscriberPanel);
    }
}
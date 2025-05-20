package javiergs.mqtt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SubscriberPanel extends JPanel implements Observer, MouseListener {
    private final Repository repo;

    public SubscriberPanel(Repository repo) {
        this.repo = Repository.getInstance();
        setBackground(Color.LIGHT_GRAY);
        this.repo.addObserver(this);
        addMouseListener(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        List<int[]> points = repo.getCoordinates();
        for (int[] p : points) {
            g.fillOval(p[0] - 10, p[1] - 10, 20, 20);
        }
    }

    @Override public void mousePressed(MouseEvent e) {
        repo.addCoordinate(e.getX(), e.getY());
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}


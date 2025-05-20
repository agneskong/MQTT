
package javiergs.mqtt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PublisherPanel extends JPanel implements MouseListener {
    private final Repository repo;

    public PublisherPanel(Repository repo) {
        this.repo = Repository.getInstance();
        setBackground(Color.WHITE);
        addMouseListener(this);
    }

    @Override public void mousePressed(MouseEvent e) {
        repo.addCoordinate(e.getX(), e.getY());
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}


package javiergs.mqtt;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Repository extends Observable {
    private static Repository instance;
    private final List<int[]> coordinates = new ArrayList<>();

    private Repository() {}

    public static synchronized Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public void addCoordinate(int x, int y) {
        for (int[] p : coordinates) {
            if (p[0] == x && p[1] == y) return;
        }
        coordinates.add(new int[]{x, y});
        setChanged();
        notifyObservers(new int[]{x, y});
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }
}

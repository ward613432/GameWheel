import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class GameWheel {
    private ArrayList<Slice> slices = new ArrayList<Slice>();
    private int currentPos;
    private ArrayList<Slice> red = new ArrayList<Slice>();
    private ArrayList<Slice> blue = new ArrayList<Slice>();
    private ArrayList<Slice> black = new ArrayList<Slice>();

    public GameWheel() {
        for (int i = 0; i <= 19; i++) {
            // init black slices
            if (i % 5 == 0) {
                slices.add(new Slice("black", i * 1000));
            // init blue slices
            } else if (i % 2 == 0) {
                slices.add(new Slice("blue", i * 100));
            // init red slices
            } else {
                slices.add(new Slice("red", i * 200));
            }
        }
    }

    public String toString() {
        String stringSlices = "";
        for (int i = 0; i <= 19; i++) {
            stringSlices += String.format("%d - %s\n", i, slices.get(i).toString());
        }
        return stringSlices;
    }

    public void split() {
        for (Slice s: slices) {
            if (s.getColor().equals("black")) {
                black.add(s);
            } else if (s.getColor().equals("blue")) {
                blue.add(s);
            } else if (s.getColor().equals("red")) {
                red.add(s);
            }
        }
    }

    // re-splits the list to assure there aren't any extra slices
    public void resplit() {
        black.clear();
        red.clear();
        blue.clear();
        split();
    }

    public void scramble() {
        // clear then split the color lists
        resplit();

        // shuffle lists
        Collections.shuffle(black);
        Collections.shuffle(red);
        Collections.shuffle(blue);

        // clear main slice list and then re-construct slice list
        reconstructMainList();
    }

    public void sort() {
        // clear then split the color lists
        resplit();

        // sort lists
        black.sort(Comparator.comparingInt((Slice::getPrizeAmount)));
        red.sort(Comparator.comparingInt((Slice::getPrizeAmount)));
        blue.sort(Comparator.comparingInt((Slice::getPrizeAmount)));

        // clear main slice list then re-construct slice list
        reconstructMainList();
    }

    public Slice spinWheel() {
        Random random = new Random();
        return slices.get(random.nextInt(slices.size()+1));
    }

    public Slice getSlice(int i) {
        if (i > slices.size()) { return slices.get(0); }
        return slices.get(i);
    }

    // clears then rebuilds the slice lists in proper order from black/blue/red lists
    private void reconstructMainList() {
        slices.clear();

        // black, blue, red
        int[] slicesCount = {0, 0, 0};
        for (int i = 0; i <= 19; i++) {
            // add black slices
            if (i % 5 == 0) {
                slices.add(black.get(slicesCount[0]));
                slicesCount[0]++;
                // add blue slices
            } else if (i % 2 == 0) {
                slices.add(blue.get(slicesCount[1]));
                slicesCount[1]++;
                // add red slices
            } else {
                slices.add(red.get(slicesCount[2]));
                slicesCount[2]++;
            }
        }
    }
}
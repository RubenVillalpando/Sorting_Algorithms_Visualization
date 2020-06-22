package SorterVisualization;

import java.awt.Color;
import java.util.Random;

public class Bar {

    int r, g, b, height;
    Color color;
    Random rand = new Random();

    public Bar(){
        r = rand.nextInt(256);
        g = rand.nextInt(256);
        b = rand.nextInt(256);
        height = 200 + rand.nextInt(600);
        color = new Color(r, g, b);
    }

}

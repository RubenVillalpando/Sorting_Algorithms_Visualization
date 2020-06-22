package SorterVisualization;

import javax.swing.JFrame;

public class Window extends JFrame {

    public Window(int height, int width, Main main){
        setTitle("Sorter Visualization");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        add(main);
        setVisible(true);
        main.start();
    }

}

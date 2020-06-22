package SorterVisualization;

import java.awt.*;

public class Main extends Canvas implements Runnable{

    Bar[] bars;
    boolean gameIsRunning;
    Thread thread = new Thread(this);
    Sorter sort;

    public Main(){
        init();
        Window window = new Window(836, 800, this);
    }

    private void init(){
        bars = new Bar[200];
        for (int i = 0; i < 200; i++){
            bars[i] = new Bar();
        }

    }

    public synchronized void start(){
        thread.start();
        gameIsRunning = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            gameIsRunning = false;
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void run(){
        long lastTime = System.nanoTime();
        double ticks = 100.0;
        double ns = 1000000000/ticks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int seconds = 0;
        while(gameIsRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(gameIsRunning){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
                seconds++;
                if (seconds == 1){
                    seconds = 0;
                }
            }
        }
    }

    private void tick() {
        sort = new Sorter(this);
//        sort.bubbleSort(bars);
        sort.insertionSort(bars);
//        sort.selectionSort(bars);
//        sort.quickSort(bars);
    }

    private void render() {
//        sort = new Sorter(this);
//        sort.bubbleSort(bars);
//        sort.insertionSort(bars);
//        sort.selectionSort(bars);
//        sort.quickSort(bars);
    }


    public static void main(String[] args){
        new Main();
    }
}

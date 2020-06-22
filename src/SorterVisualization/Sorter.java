package SorterVisualization;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Sorter{

    Graphics g;
    BufferStrategy bs;
    Canvas canvas;

    public Sorter(Main canvas){
        this.canvas = canvas;
    }

    private void swap(Bar[] arr, int a, int b){
        Bar temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void bubbleSort(Bar[] arr){
        if(!isSorted(arr)) {
            boolean isSorted;
            for (int i = 0; i < arr.length - 1; i++) {
                isSorted = true;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i].height > arr[j].height) {
                        swap(arr, i, j);
                        isSorted = false;
                        drawBars(arr);
                    }
                }
                if (isSorted) return;
            }
        }
    }

    public void selectionSort(Bar[] arr){
        if(!isSorted(arr)) {
            for (int i = 0; i < arr.length - 1; i++) {
                int min = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[min].height > arr[j].height) min = j;
                }
                if (min != i){
                    swap(arr, min, i);
                    drawBars(arr);
                }
            }
        }

    }

    public void insertionSort(Bar[] arr){
        if(!isSorted(arr)) {
            for (int i = 1; i < arr.length; i++) {
                Bar element = arr[i];
                int j = i;
                while (j > 0 && arr[j - 1].height < element.height) {
                    arr[j] = arr[j - 1];
                    j -= 1;

                    drawBars(arr);
                }
                arr[j] = element;
            }
        }
    }

    public void quickSort(Bar[] arr){
        if(!isSorted(arr))quickSort(arr, 0, arr.length-1);
    }

    private void quickSort(Bar[] arr, int start, int end){

        if (start >= end) return;

        Bar divider = arr[start];
        int hi = end;
        int lo = start;

        while(true){
            while(arr[hi].height >= divider.height){
                hi--;
                if (hi<= lo) {
                    break;
                }
                drawBars(arr);
            }

            if(hi <= lo){
                arr[lo] = divider;
                break;
            }
            arr[lo] = arr[hi];
            lo++;
            while(arr[lo].height < divider.height){
                lo++;
                if (lo>= hi){
                    break;
                }
                drawBars(arr);
            }
            if(lo >= hi){
                lo = hi;
                arr[hi] = divider;
                break;
            }
            arr[hi] = arr[lo];
        }
        quickSort(arr, start, lo-1);
        quickSort(arr, lo+1, end);

    }

//    void mergeSort(Bar[] arr, int l, int r){
//        if(!isSorted(arr)) {
//            if (l < r) {
//                int m = (l + r) / 2;
//
//                mergeSort(arr, l, m);
//                mergeSort(arr, m + 1, r);
//
//                mergeSort(arr, l, m, r);
//            }
//        }
//    }
//
//    private void mergeSort(Bar[] arr, int l, int m, int r){
//        int n1 = m - l + 1;
//        int n2 = r - m;
//
//        int L[] = new int [n1];
//        int R[] = new int [n2];
//
//        for (int i=0; i<n1; ++i)
//            L[i] = arr[l + i];
//        for (int j=0; j<n2; ++j)
//            R[j] = arr[m + 1+ j];
//
//
//        int i = 0, j = 0;
//
//        int k = l;
//        while (i < n1 && j < n2)
//        {
//            if (L[i] <= R[j])
//            {
//                arr[k] = L[i];
//                i++;
//            }
//            else
//            {
//                arr[k] = R[j];
//                j++;
//            }
//            k++;
//        }
//
//        while (i < n1)
//        {
//            arr[k] = L[i];
//            i++;
//            k++;
//        }
//        while (j < n2)
//        {
//            arr[k] = R[j];
//            j++;
//            k++;
//        }
//    }



    public void printArr(int[] arr){
        StringBuilder arrString = new StringBuilder("[" + arr[0]);
        for(int i = 1; i < arr.length; i++){
            arrString.append(", ").append(arr[i]);
        }
        arrString.append("]");
        System.out.println(arrString);
    }

    public int[] randArray(int size, int bound){
        Random r = new Random();
        int[] ints = new int[size];

        for(int i = 0; i < size; i++){
            ints[i] = r.nextInt(bound);
        }
        return ints;
    }

    private boolean isSorted(Bar[] arr){
        boolean isSorted = true;

        for(int i = 0; i < arr.length-1; i++){
            if(arr[i].height > arr[i+1].height){
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    private void drawBars(Bar[] bars){

        bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 800);

        for(int i = 0; i < bars.length; i++){
            Bar bar = bars[i];
            g.setColor(bar.color);
            g.fill3DRect(i*4, bars[i].height, 4, 800-bars[i].height, true);

        }

        g.dispose();
        bs.show();
    }

}

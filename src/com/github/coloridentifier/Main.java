package com.github.coloridentifier;

import com.github.coloridentifier.graphing.Graph;

import java.awt.*;

public class Main {
    public static Graph scatterPlot;

    public static void main(String args[]) {
        scatterPlot = new Graph( 600, 600, 0, 0, 14, 14, "Fruits", "Spike Length", "Spot Size");
        scatterPlot.setRegularity(2);
        // plot "good" fruits
        scatterPlot.addPoint(4.2, 6.1, Color.BLUE);
        scatterPlot.addPoint(1.5, 5.3, Color.BLUE);
        scatterPlot.addPoint(6.2, 3.9, Color.BLUE);
        scatterPlot.addPoint(5.1, 2.4, Color.BLUE);
        scatterPlot.addPoint(2.7, 1.1, Color.BLUE);
        scatterPlot.addPoint(3.0, 4.2, Color.BLUE);
        scatterPlot.addPoint(7.2, 1.1, Color.BLUE);

        // plot "bad" fruits
        scatterPlot.addPoint(8.2, 6.6, 'X', Color.RED);
        scatterPlot.addPoint(7.5, 5.3, 'X', Color.RED);
        scatterPlot.addPoint(4.1, 6.9, 'X', Color.RED);
        scatterPlot.addPoint(5.6, 8.4, 'X', Color.RED);
        scatterPlot.addPoint(9.7, 1.1, 'X', Color.RED);
        scatterPlot.addPoint(2.0, 9.2, 'X', Color.RED);
        scatterPlot.addPoint(10.2, 6.6, 'X', Color.RED);
        scatterPlot.addPoint(7.8, 4.3, 'X', Color.RED);
        scatterPlot.addPoint(11.2, 6.9, 'X', Color.RED);
        scatterPlot.addPoint(13.7, 3.4, 'X', Color.RED);
        scatterPlot.addPoint(9.3, 3.1, 'X', Color.RED);
        scatterPlot.addPoint(2.4, 8.9, 'X', Color.RED);
        scatterPlot.addPoint(2.9, 11.9, 'X', Color.RED);
        scatterPlot.addPoint(5.7, 13.4, 'X', Color.RED);
        scatterPlot.addPoint(1.3, 14, 'X', Color.RED);
        scatterPlot.addPoint(6.4, 10.9, 'X', Color.RED);
        scatterPlot.addPoint(11.7, 12.2, 'X', Color.RED);
        scatterPlot.addPoint(11.3, 12.8, 'X', Color.RED);
        scatterPlot.addPoint(14, 10.9, 'X', Color.RED);

        new ColorIdentifier(2, 2, 2);

        scatterPlot.repaint();
    }
}

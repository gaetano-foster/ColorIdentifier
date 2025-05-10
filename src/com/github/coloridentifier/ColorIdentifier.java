package com.github.coloridentifier;

import com.github.coloridentifier.graphing.Graph;

import javax.swing.*;
import java.awt.*;

public class ColorIdentifier {
    // Weights connecting inputs to first output
    private double weight11 = 0, weight21 = 0;
    // Weights connecting inputs to second output
    private double weight12 = 0, weight22 = 0;
    // Bias values
    private double bias1 = 0, bias2 = 0;

    private JFrame frame;
    private JSlider ws11, ws21, ws12, ws22, bs1, bs2;

    private Graph graph;

    public ColorIdentifier(Graph graph) {
        this.graph = graph;
        createDisplayWindow();
    }

    private void createDisplayWindow() {
        frame = new JFrame("Weights & Biases");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6, 2));

        // Weight_1_1 slider
        ws11 = new JSlider(-100, 100, 0);
        ws11.setMajorTickSpacing(100);
        ws11.setPaintLabels(true);
        ws11.addChangeListener(e -> {
            weight11 = (double)(ws11.getValue()) / 100;
            resetShades();
        });

        // Weight_2_1 slider
        ws21 = new JSlider(-100, 100, 0);
        ws21.setMajorTickSpacing(100);
        ws21.setPaintLabels(true);
        ws21.addChangeListener(e -> {
            weight21 = (double)(ws21.getValue()) / 100;
            resetShades();
        });

        // Weight_1_2 slider
        ws12 = new JSlider(-100, 100, 0);
        ws12.setMajorTickSpacing(100);
        ws12.setPaintLabels(true);
        ws12.addChangeListener(e -> {
            weight12 = (double)(ws12.getValue()) / 100;
            resetShades();
        });

        // Weight_2_2 slider
        ws22 = new JSlider(-100, 100, 0);
        ws22.setMajorTickSpacing(100);
        ws22.setPaintLabels(true);
        ws22.addChangeListener(e -> {
            weight22 = (double)(ws22.getValue()) / 100;
            resetShades();
        });

        // Bias 1 slider
        bs1 = new JSlider(-100, 100, 0);
        bs1.setMajorTickSpacing(100);
        bs1.setPaintLabels(true);
        bs1.addChangeListener(e -> {
            bias1 = (double)(bs1.getValue()) / 100;
            resetShades();
        });

        // Bias 2 slider
        bs2 = new JSlider(-100, 100, 0);
        bs2.setMajorTickSpacing(100);
        bs2.setPaintLabels(true);
        bs2.addChangeListener(e -> {
            bias2 = (double)(bs2.getValue()) / 100;
            resetShades();
        });

        frame.add(ws11);
        frame.add(ws21);
        frame.add(ws12);
        frame.add(ws22);
        frame.add(bs1);
        frame.add(bs2);
        frame.setVisible(true);
    }

    public int classify(double input1, double input2) {
        double output1 = input1 * weight11 + input2 * weight21 + bias1;
        double output2 = input1 * weight12 + input2 * weight22 + bias2;

        return (output1 > output2) ? 0 : 1;
    }

    public void resetShades() {
        for (double y = 0; y < graph.getEndY(); y += 0.1) {
            for (double x = 0; x < graph.getEndX(); x += 0.01) {
                visualize(x, y);
            }
        }
    }

    public void visualize(double graphX, double graphY) {
        int predictedClass = classify(graphX, graphY);

        if (predictedClass == 0) {
            graph.shadeGraph(graphX, graphY, Color.CYAN);
        }
        else {
            graph.shadeGraph(graphX, graphY, Color.PINK);
        }
        graph.repaint();
    }
}

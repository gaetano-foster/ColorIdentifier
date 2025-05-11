package com.github.coloridentifier.neuralnetwork;

import javax.swing.*;
import java.awt.*;

public class Layer {
    private static int layerIndex = 1;
    private int numNodesIn, numNodesOut;
    private double weights[][];
    private JFrame wFrame;
    private JSlider wSliders[][];
    private double biases[];
    private JFrame bFrame;
    private JSlider bSliders[];

    public Layer(int numNodesIn, int numNodesOut) {
        this.numNodesIn = numNodesIn;
        this.numNodesOut = numNodesOut;

        weights = new double[numNodesIn][numNodesOut];

        wFrame = new JFrame("Weights " + layerIndex);
        wFrame.setSize(400, 400);
        wFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wFrame.setResizable(false);
        wFrame.setLocationRelativeTo(null);
        wFrame.setLayout(new GridLayout(numNodesIn * numNodesOut, 2));

        wSliders = new JSlider[numNodesIn][numNodesOut];
        for (int i = 0; i < numNodesOut; i++) {
            for (int j = 0; j < numNodesIn; j++) {
                wSliders[j][i] = new JSlider(-1000, 1000, 0);
                wSliders[j][i].setMajorTickSpacing(1000);
                wSliders[j][i].setPaintLabels(true);
                int finalJ = j;
                int finalI = i;
                wSliders[j][i].addChangeListener(e -> {
                    weights[finalJ][finalI] = (double)(wSliders[finalJ][finalI].getValue()) / 1000;
                    NeuralNetwork.resetShades();
                });
                wFrame.add(wSliders[j][i]);
            }
        }
        wFrame.setVisible(true);

        biases = new double[numNodesOut];

        bFrame = new JFrame("Biases " + layerIndex);
        bFrame.setSize(400, 400);
        bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bFrame.setResizable(false);
        bFrame.setLocationRelativeTo(null);
        bFrame.setLayout(new GridLayout(biases.length, 2));

        bSliders = new JSlider[numNodesOut];
        for (int i = 0; i < numNodesOut; i++) {
            bSliders[i] = new JSlider(-1000, 1000, 0);
            bSliders[i].setMajorTickSpacing(1000);
            bSliders[i].setPaintLabels(true);
            int finalI = i;
            bSliders[i].addChangeListener(e -> {
                biases[finalI] = (double)(bSliders[finalI].getValue()) / 1000;
                NeuralNetwork.resetShades();
            });
            bFrame.add(bSliders[i]);
        }
        bFrame.setVisible(true);

        layerIndex++;
    }

    // calculate output of the layer
    public double[] calculateOutputs(double inputs[]) {
        double activations[] = new double[numNodesOut];
        for (int nodeOut = 0; nodeOut < numNodesOut; nodeOut++) {
            double weightedInput = biases[nodeOut];
            for (int nodeIn = 0; nodeIn < numNodesIn; nodeIn++) {
                weightedInput += inputs[nodeIn] * weights[nodeIn][nodeOut];
            }

            activations[nodeOut] = activationFunction(weightedInput);
        }

        return activations;
    }

    private double activationFunction(double weightedInput) {
        return 1 / (1 + Math.exp(-weightedInput));
    }
}

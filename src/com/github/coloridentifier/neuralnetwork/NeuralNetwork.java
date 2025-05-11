package com.github.coloridentifier.neuralnetwork;

import com.github.coloridentifier.Main;

import java.awt.*;
import java.util.Comparator;
import java.util.stream.IntStream;

public class NeuralNetwork {
    private static Layer layers[];

    public NeuralNetwork(int... layerSizes) {
        layers = new Layer[layerSizes.length - 1];
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Layer(layerSizes[i], layerSizes[i + 1]);
        }
    }

    // run input values through the network to calculate output values
    public static double[] calculateOutputs(double inputs[]) {
        for (Layer layer : layers) {
            inputs = layer.calculateOutputs(inputs);
        }

        return inputs;
    }

    // run the inputs through the network and calculate which output node has the highest value
    public static int classify(double inputs[]) {
        double outputs[] = calculateOutputs(inputs);
        return IntStream.range(0, outputs.length)
                .boxed()
                .max(Comparator.comparingDouble(i -> outputs[i]))
                .orElse(-1);
    }

    public static void resetShades() {
        for (double y = 0; y < Main.scatterPlot.getEndY(); y += 0.1) {
            for (double x = 0; x < Main.scatterPlot.getEndX(); x += 0.01) {
                visualize(x, y);
            }
        }
    }

    public static void visualize(double graphX, double graphY) {
        int predictedClass = classify(new double[]{ graphX, graphY });

        if (predictedClass == 0) {
            Main.scatterPlot.shadeGraph(graphX, graphY, Color.CYAN);
        }
        else {
            Main.scatterPlot.shadeGraph(graphX, graphY, Color.PINK);
        }
        Main.scatterPlot.repaint();
    }
}

package com.github.coloridentifier;

import com.github.coloridentifier.graphing.Graph;
import com.github.coloridentifier.neuralnetwork.NeuralNetwork;

import javax.swing.*;
import java.awt.*;

public class ColorIdentifier {
    private NeuralNetwork neuralNetwork;
    public ColorIdentifier(int... layerSizes) {
        neuralNetwork = new NeuralNetwork(layerSizes);
    }
}

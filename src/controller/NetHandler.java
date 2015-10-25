package controller;

import model.Neuron;
import model.NeuronLayer;
import model.NeuronNet;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Asus on 25.10.2015.
 */
public class NetHandler {

    private int numNeuFirstLayer;
    private int numNeuSecondLayer;

    NeuronNet compressNetwork;

    public void createNeuronNetwork() {
        NeuronLayer firstLayer = new NeuronLayer(numNeuFirstLayer);

        NeuronLayer secondLayer = new NeuronLayer(numNeuSecondLayer);


        for (int neuronNumber=0; neuronNumber < numNeuSecondLayer; neuronNumber++) {
            List<Double> coofs = new LinkedList<Double>();
            for (int neuronNumber2 = 0; neuronNumber2 < numNeuFirstLayer; neuronNumber2++) {
                coofs.add(Math.random());
            }
            secondLayer.setConnections(neuronNumber, coofs, firstLayer);
        }
    }
}

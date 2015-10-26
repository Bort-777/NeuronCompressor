package controller;

import model.NeuronPackage.Neuron;
import model.NeuronPackage.NeuronLayer;
import model.NeuronPackage.NeuronNet;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Asus on 25.10.2015.
 */
public class NetHandler {

    private int numNeuFirstLayer;
    private int numNeuSecondLayer;

    NeuronNet compressNetwork;
    NeuronNet reverseNetwork;

    public void setNumNeuFirstLayer(int num) {
        this.numNeuFirstLayer = num;
    }

    public void setNumNeuSecondLayer(int num) {
        this.numNeuSecondLayer = num;
    }

    public NeuronNet createNeuronNetwork() {
        NeuronLayer firstLayer = new NeuronLayer(numNeuFirstLayer);

        NeuronLayer secondLayer = new NeuronLayer(numNeuSecondLayer);


        for (int neuronNumber = 0; neuronNumber < numNeuSecondLayer; neuronNumber++) {
            List<Double> coofs = new LinkedList<Double>();
            for (int neuronNumber2 = 0; neuronNumber2 < numNeuFirstLayer; neuronNumber2++) {
                int one = 0;
                if (Math.random() > 0.5) one = 1;
                if (Math.random() < 0.5) one = -1;
                coofs.add(one * Math.random());
            }
            secondLayer.setConnections(neuronNumber, coofs, firstLayer);
        }

        compressNetwork = new NeuronNet();
        compressNetwork.setInputLayer(firstLayer);
        compressNetwork.setOutputLayer(secondLayer);

        return compressNetwork;
    }

    public NeuronNet createReverseNetwork() {
        NeuronLayer firstLayer = new NeuronLayer(numNeuFirstLayer);

        NeuronLayer secondLayer = new NeuronLayer(numNeuSecondLayer);


        for (int neuronNumber = 0; neuronNumber < numNeuFirstLayer; neuronNumber++) {
            List<Double> coofs = new LinkedList<Double>();
            for (int neuronNumber2 = 0; neuronNumber2 < numNeuSecondLayer; neuronNumber2++) {
                coofs.add(Math.random());
            }
            firstLayer.setConnections(neuronNumber, coofs, secondLayer);
        }

        reverseNetwork = new NeuronNet();
        reverseNetwork.setInputLayer(secondLayer);
        reverseNetwork.setOutputLayer(firstLayer);

        return reverseNetwork;
    }
}

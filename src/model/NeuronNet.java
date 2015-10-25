package model;

import java.util.List;

/**
 * Created by Asus on 25.10.2015.
 */
public class NeuronNet {

    private NeuronLayer inputLayer;
    private NeuronLayer outputLayer;

    public void setInputLayer(NeuronLayer neuronLayer) {
        this.inputLayer = neuronLayer;
    }

    public void setOutputLayer(NeuronLayer neuronLayer) {
        this.outputLayer = neuronLayer;
    }

    public List<Double> goThrew(List<Double> inputValues) {
        inputLayer.setValues(inputValues);

        return outputLayer.getValues();
    }

    public void input(List<Double> values) {
        inputLayer.setValues(values);
    }

    public List<Double> output(){
        return outputLayer.getValues();
    }

}

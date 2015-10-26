package model;

import model.NeuronPackage.Akson;
import model.NeuronPackage.Neuron;
import model.NeuronPackage.NeuronLayer;
import model.NeuronPackage.NeuronNet;

import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Asus on 26.10.2015.
 */
public class Mentor {

    private LinkedList<Double> mistake;
    private Double alfa;

    public void setMistake(LinkedList<Double> souce, LinkedList<Double> result) {
        if (souce.size() == result.size()) {

            mistake = new LinkedList<Double>();

            Iterator<Double> sourceIter = souce.iterator();
            Iterator<Double> resultIter = result.iterator();

            while (sourceIter.hasNext()) {
                mistake.add(new Double(resultIter.next() - sourceIter.next()));
            }
        }
    }

    public void teach(NeuronNet neuronNet, boolean output) { //coof - alfa * ytran * deltax

        NeuronLayer outputLayer = neuronNet.getOutputLayer();
        LinkedList<Neuron> outputNeurons = outputLayer.getNeurons();

        alfa = 0.001;



        Iterator<Neuron> neuronIterator = outputNeurons.iterator();
        while (neuronIterator.hasNext()) {
            Neuron neuron = neuronIterator.next();
            LinkedList<Akson> aksons = neuron.getInputAksonsList();

            LinkedList<Double> weights = new LinkedList<Double>();
            Iterator<Akson> aksonIterator = aksons.iterator();
            while (aksonIterator.hasNext()) {
                Akson akson = aksonIterator.next();

                Double coof = akson.getWeightCoof();
                Double fatherValue = akson.getFatherValue();
                Double delta = new Double(0);


                if(output){
                    Iterator<Double> mistakeIterator = mistake.iterator();
                    while (mistakeIterator.hasNext()) {
                        delta += mistakeIterator.next() * fatherValue;
                    }
                    weights.add(new Double(coof - alfa * delta));
                }
                else {
                    Iterator<Double> mistakeIterator = mistake.iterator();
                    while (mistakeIterator.hasNext()) {
                        delta += mistakeIterator.next() * fatherValue;
                    }

                }


            }
            neuron.setWeightCoofs(weights);
        }

    }

}

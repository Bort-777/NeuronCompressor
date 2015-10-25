package model.Neuro;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Asus on 25.10.2015.
 */
public class NeuronLayer {

    private LinkedList<Neuron> neurons;

    public NeuronLayer(int numberOfNeurons) {
        neurons = new LinkedList<Neuron>();

        for (int number = 0; number < numberOfNeurons; number++) {
            Neuron neuron = new Neuron();
            neurons.add(neuron);
        }
    }

    public LinkedList<Neuron> getNeurons() {
        return neurons;
    }

    public void setValues(List<Double> values) {
        if (values.size() == neurons.size()) {
            Iterator<Neuron> iterator = neurons.iterator();
            int numberOfNeuron = -1;
            while (iterator.hasNext()) {
                numberOfNeuron++;

                Neuron neuron = iterator.next();
                neuron.setValue(values.get(numberOfNeuron));
            }
        }
    }

    public void setConnections(int numberOfNeuron, List<Double> coofs, NeuronLayer previousLayer) {     // addin Aksons to neuron
        Iterator<Neuron> iterator = neurons.iterator();                 // finding neuron
        Neuron neuron = new Neuron();
        int number = -1;
        while (iterator.hasNext()) {
            number++;
            neuron = iterator.next();
            if (number == numberOfNeuron) {
                break;
            }
        }

        LinkedList<Neuron> previousLayerNeuron = previousLayer.getNeurons();        // adding connections between all previous layer neurons
        iterator = previousLayerNeuron.iterator();
        number = -1;
        while (iterator.hasNext()) {
            number++;
            Neuron father = iterator.next();

            Akson akson = new Akson(father);
            akson.setWeightCoof(coofs.get(number));

            neuron.addAkson(akson);
        }
    }

    public List<Double> getValues() {

        List<Double> valuesList = new LinkedList<Double>();

        Iterator<Neuron> iterator = neurons.iterator();
        int number = -1;
        while (iterator.hasNext()) {
            number++;
            Neuron neuron = iterator.next();

            valuesList.add(new Double(neuron.getValue()));
        }

        return valuesList;
    }
}

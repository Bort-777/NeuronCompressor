package model.NeuronPackage;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * Created by Maxim Danilov on 25.10.2015.
 */
public class Neuron {

    private double value;                   // value on this neuron
    private LinkedList<Akson> aksons;       // all aksons, collides to neuron

    public Neuron() {
        aksons = new LinkedList<Akson>();
    }

    public void setValue(double value) {    // set value on this neuron
        this.value = value;
    }

    public double getValue() {                // get value on this neuron
        if (aksons.isEmpty()) {             // means that neuron is sensor and have default value
            return this.value;
        } else {                            // calculate value by previous neurons
            return calculateValue();
        }
    }

    public double calculateValue() {        // calculates value by weight coofs
        double value = 0;

        Iterator<Akson> iter = aksons.iterator();
        while (iter.hasNext()) {
            Akson akson = iter.next();
            value += akson.getWeightCoof() * akson.getFatherValue();
        }

        this.value = value;

        return value;
    }

    public void addAkson(Akson akson) {     // add Akson
        aksons.add(akson);
    }

    public LinkedList<Akson> getInputAksonsList() {
        return aksons;
    }

    public void setWeightCoofs(LinkedList<Double> coof){
        Iterator<Akson> aksonIterator = aksons.iterator();
        int number = -1;
        while(aksonIterator.hasNext()){
            number++;
            aksonIterator.next().setWeightCoof(coof.get(number));
        }
    }
}

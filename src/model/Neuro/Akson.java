package model.Neuro;

/**
 * Created by Maxim Danilov on 25.10.2015.
 */
public class Akson {

    private double weightCoof;          // weight coofecient of connection
    private Neuron fatherNeuron;        // source Neuron

    public Akson(Neuron fatherNeuron){
        setFatherNeuron(fatherNeuron);
    }

    public void setWeightCoof(double coof) {
        this.weightCoof = coof;
    }

    public double getWeightCoof() {
        return this.weightCoof;
    }

    public void setFatherNeuron(Neuron father) {
        this.fatherNeuron = father;
    }

    public double getFatherValue() {
        return fatherNeuron.getValue();
    }
}

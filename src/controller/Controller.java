package controller;

import model.ImageCreator;
import model.ImagePartDivider;
import model.Mentor;
import model.NeuronPackage.NeuronNet;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Asus on 26.10.2015.
 */
public class Controller {

    private BufferedImage source;
    private BufferedImage output;

    private NetHandler netHandler;
    private NeuronNet compressNet;
    private NeuronNet reverseNet;

    private int partWidth;
    private int partHeight;


    public Controller() {
        netHandler = new NetHandler();
        netHandler.setNumNeuFirstLayer(12);
        netHandler.setNumNeuSecondLayer(3);

        compressNet = netHandler.createNeuronNetwork();
        reverseNet = netHandler.createReverseNetwork();

        partHeight = 2;
        partWidth = 2;
    }

    public void setSource(BufferedImage image) {
        this.source = image;
        this.output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
    }

    public BufferedImage getOutput() {
        return output;
    }

    public void comress() {
        ImagePartDivider imagePartDivider = new ImagePartDivider(source);
        imagePartDivider.setWidth(partWidth);
        imagePartDivider.setHeight(partHeight);

        ImageCreator imageCreator = new ImageCreator(output);

        Mentor mentor = new Mentor();

        for (int x = 0; x < source.getWidth(); x += partWidth) {
            for (int y = 0; y < source.getHeight(); y += partHeight) {
                LinkedList<Double> values = imagePartDivider.getPartVector(x, y);
                System.out.println(values);
                LinkedList<Double> compressedValues = (LinkedList) compressNet.goThrew(values);
                System.out.println(compressedValues);
                LinkedList<Double> recompressedValues = (LinkedList) reverseNet.goThrew(compressedValues);
                System.out.println(recompressedValues);
                imageCreator.drawPart(recompressedValues, partWidth, partHeight, x, y);

                mentor.setMistake(values, recompressedValues);
                mentor.teach(reverseNet);

                mentor.teach(compressNet);

            }
        }
    }
}

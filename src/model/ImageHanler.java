package model;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Asus on 25.10.2015.
 */
class ImageHandler {

    private Image image;
    private int height;
    private int width;

    public ImageHandler(Image image) {
        this.image = image;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public LinkedList<Double> getPartVector(int coordX, int coordY) {
        LinkedList<Double> vector = new LinkedList<>();

        BufferedImage bufferedImage = (BufferedImage) image;

        for (int x = coordX; x < coordX + width; x++) {
            for (int y = coordY; y < coordY + height; y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));

                double red = color.getRed();

                vector.add(new Double(2 * red / 255 - 1));
            }
        }

        for (int x = coordX; x < coordX + width; x++) {
            for (int y = coordY; y < coordY + height; y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));

                double green = color.getGreen();

                vector.add(new Double(2 * green / 255 - 1));
            }
        }

        for (int x = coordX; x < coordX + width; x++) {
            for (int y = coordY; y < coordY + height; y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));

                double blue = color.getBlue();

                vector.add(new Double(2 * blue / 255 - 1));
            }
        }

        return vector;
    }
}

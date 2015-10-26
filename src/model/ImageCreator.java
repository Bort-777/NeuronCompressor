package model;

import controller.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Asus on 26.10.2015.
 */
public class ImageCreator {

    private BufferedImage image;

    public ImageCreator(BufferedImage image) {
        this.image = image;
    }

    public void drawPart(LinkedList<Double> values, int numberOfPixelsX, int numberOfPixelsY, int xStart, int yStart) {
        if (xStart + numberOfPixelsX > image.getWidth()) {
            xStart -= xStart + numberOfPixelsX - image.getWidth();
        }
        if (yStart + numberOfPixelsY > image.getHeight()) {
            yStart -= yStart + numberOfPixelsY - image.getHeight();
        }

        int red = 0;
        int green = 0;
        int blue = 0;

        int numberOfPixel = 0;

        for (int x = xStart; x < xStart + numberOfPixelsX; x++) {
            for (int y = yStart; y < yStart + numberOfPixelsY; y++) {
                if (values.get(numberOfPixel) < -1) {
                    red = 0;
                } else if (values.get(numberOfPixel) > 1) {
                    red = 255;
                } else if (-1 < values.get(numberOfPixel) && values.get(numberOfPixel) < 1) {
                    red = (int) ((values.get(numberOfPixel) + 1) * 255 / 2);
                }

                if (values.get(numberOfPixel + numberOfPixelsX * numberOfPixelsY) < -1) {
                    green = 0;
                } else if (values.get(numberOfPixel+ numberOfPixelsX * numberOfPixelsY) > 1) {
                    green = 255;
                } else if (-1 < values.get(numberOfPixel+ numberOfPixelsX * numberOfPixelsY) && values.get(numberOfPixel+ numberOfPixelsX * numberOfPixelsY) < 1) {
                    green = (int) ((values.get(numberOfPixel + numberOfPixelsX * numberOfPixelsY) + 1) * 255 / 2);
                }

                if (values.get(numberOfPixel + 2 * numberOfPixelsX * numberOfPixelsY) < -1) {
                    blue = 0;
                } else if (values.get(numberOfPixel + 2 * numberOfPixelsX * numberOfPixelsY) > 1) {
                    blue = 255;
                } else if (-1 < values.get(numberOfPixel + 2 * numberOfPixelsX * numberOfPixelsY) && values.get(numberOfPixel + 2 * numberOfPixelsX * numberOfPixelsY) < 1) {
                    blue = (int) ((values.get(numberOfPixel + 2 * numberOfPixelsX * numberOfPixelsY) + 1) * 255 / 2);
                }

                Color color = new Color(red, green, blue);
                drawPixel(color, x, y);
                numberOfPixel++;
            }
        }


    }

    public void drawPixel(Color color, int x, int y) {
        image.setRGB(x, y, color.getRGB());
    }
}

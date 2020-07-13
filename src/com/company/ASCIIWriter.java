package com.company;

import com.company.PicHandler.Picture;
import com.company.PicHandler.Pixel;
import com.company.UI.Canvas;
import com.company.UI.CanvasText;
import com.company.UI.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class ASCIIWriter {

    int factor;
    Picture original;

    public ASCIIWriter(Picture p, int factor){
        this.original = p;
        this.factor = factor;
    }

    public void print(){
        Picture ref = asciiCompress();

        Pixel[][] pixels = ref.getPixels2D();
        for (int i = 0; i < pixels.length; i++) {
            String line = "";
            for (int j = 0; j < pixels.length; j++) {
                Pixel pixel = pixels[i][j];
                line += getChar(pixel.getAverage()) + "";
                line += getChar(pixel.getAverage()) + "";

            }
            if (!lineIsEmpty(line)) {
                System.out.println(line);
            }
        }
    }
    public void saveToFile(String fileName){
        Picture ref = asciiCompress();

        Pixel[][] pixels = ref.getPixels2D();
        for (int i = 0; i < pixels.length; i++) {
            String line = "";
            for (int j = 0; j < pixels.length; j++) {
                Pixel pixel = pixels[i][j];
                line += getChar(pixel.getAverage()) + "";
                line += getChar(pixel.getAverage()) + "";

            }
            if (!lineIsEmpty(line)) {
                try {
                    FileWriter fw = new FileWriter(fileName, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(line);
                    bw.newLine();
                    bw.close();
                } catch (IOException e) {
                    System.out.println("FileNotFoundException");
                }
            }
        }
    }

    public ArrayList<String> toList(){
        ArrayList<String> output = new ArrayList<>();
        Picture ref = asciiCompress();

        Pixel[][] pixels = ref.getPixels2D();
        for (int i = 0; i < pixels.length; i++) {
            String line = "";
            for (int j = 0; j < pixels.length; j++) {
                Pixel pixel = pixels[i][j];
                line += getChar(pixel.getAverage()) + "";
                line += getChar(pixel.getAverage()) + "";

            }
            if (!lineIsEmpty(line)) {
                output.add(line);
            }
        }
        return output;
    }

    public void saveToImage(String filename, int size){
        ArrayList<String> asciiList = this.toList();
        int width = original.getWidth() * size * 4;
        int height = original.getHeight() * size * 4;

        Window win = new Window(width, height);
        Canvas canvas = new Canvas(width, height) {

            @Override
            public void render(Graphics2D g2d) {
                int fontSize = size;
                for (int i = 0; i < asciiList.size(); i++) {
                    CanvasText.drawString(1, fontSize * i, g2d, asciiList.get(i), size);
                }
            }
        };
        win.add(canvas);

        win.show();
        canvas.show();
        canvas.repaint();
        canvas.saveToImg(filename);
        win.frame.dispatchEvent(new WindowEvent(win.frame, WindowEvent.WINDOW_CLOSING));
    }

    public Picture saveToPicture() throws IOException {
        int size = 39;
        String key = "Sample";
        int width = (int) (original.getWidth());
        int height = (int) (original.getHeight());
//        int height = (int) (original.getHeight() * 3.08);

        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width + 10, height + 10);
        graphics.setColor(Color.BLACK);

        ArrayList<String> asciiList = this.toList();
//        size *= 1.25;
        for (int i = 0; i < asciiList.size(); i++) {
            CanvasText.drawString(size, (int) ((size * 1.25) * (i + 1)), (Graphics2D) graphics, asciiList.get(i), size);
        }

        System.out.println("Image Created");
        return new Picture(bufferedImage);
    }


    private static char getChar(double g){
        final char str;

        if (g >= 230.0) {
            str = ' ';
        } else if (g >= 200.0) {
            str = '.';
        } else if (g >= 180.0) {
            str = '*';
        } else if (g >= 160.0) {
            str = ':';
        } else if (g >= 130.0) {
            str = 'o';
        } else if (g >= 100.0) {
            str = '&';
        } else if (g >= 70.0) {
            str = '8';
        } else if (g >= 50.0) {
            str = '#';
        } else {
            str = '@';
        }
        return str; // return the character

    }
    public Picture asciiCompress(){

        int charH = factor;
        int charW = factor;

        int maxDim = Math.max(original.getHeight(), original.getWidth());
//        Picture p = new Picture(original.getHeight(), original.getWidth());
        Picture p = new Picture(maxDim, maxDim);

        p.copyPicture(original);
        Picture out = new Picture((p.getHeight() / charH) + 1, (p.getWidth()/ charW) + 1);

        Pixel[][] dest = out.getPixels2D();
        Pixel[][] src = p.getPixels2D();

        for (       int y = 0   ; y < src.length - (src.length % charH)      ; y++) {
            for (   int x = 0   ; x < src[y].length - (src[y].length % charW)   ; x++) {
//                System.out.println("y = " + y);
//                System.out.println("x = " + x);

                int destAvg = (int) dest[y/charH][x/charW].getAverage();
                int srcAvg = (int)  src[y][x].getAverage();

                if (destAvg == 255){
                    dest[y/charH][(x/charW)].setRGB(new int[]{srcAvg, srcAvg, srcAvg});
                } else {
                    int avg = (destAvg + srcAvg)/2;
                    dest[y/charH][x/charW].setRGB(new int[]{avg,avg,avg});
                }

//                System.out.println(dest[y/charH][x/charW].getAverage());
            }
        }
//        out.explore();
        return out;
    }

    public static boolean lineIsEmpty(final String line){
        if (line.replace(" ", "").length() < 1){
            return true;
        }
        else {
            return false;
        }
    }
}

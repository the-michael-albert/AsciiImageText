package com.company.UI;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public abstract class Canvas extends JPanel {

    HashMap<String, Shape> shapes = new HashMap<>();
    public HashMap<String, BufferedImage> images = new HashMap<>();

    int w,h;
    public Canvas(int width, int height){
        super();
        w = width;
        h = height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, w, h);


        for (Map.Entry<String, BufferedImage> imageEntry : images.entrySet()) {
            g.drawImage(imageEntry.getValue(), 0, 0, this);
        }
        render(g2d);

//        System.out.println("rendering");
    }

    public abstract void render(Graphics2D g2d);

    public void addShape(String name, Shape object){
        shapes.put(name, object);
    }


    public void addImage(String name, BufferedImage image){
        images.put(name, image);
    }

    public Shape getShape(String name){
        return shapes.get(name);
    }

    public BufferedImage getBufferedImage(String name){
        return images.get(name);
    }


    public void clear(){
        shapes = new HashMap<>();
        this.repaint();
    }


    private static void drawBackground(Graphics2D g2d, Color color){


    }

    public void saveToImg(String filename){
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        printAll(g);
        g.dispose();

        try {
            ImageIO.write(image, filename.split("\\.")[1], new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

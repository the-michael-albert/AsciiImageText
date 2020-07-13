package com.company;


import com.company.PicHandler.Picture;
import com.company.UI.Canvas;
import com.company.UI.CanvasText;
import com.company.UI.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;

public class Main {


    public static void main(String[] args) throws IOException {
        System.out.println("reading file " + args[0]);

        String FILEPATH = args[0];

        String out = args[1];

        Picture p = new Picture(FILEPATH);
        ASCIIWriter ascii = new ASCIIWriter(p, 48);
//        ascii.saveToFile(out);
//        ascii.saveToImage("ascii10.png", 14);
        saveBFImg(ascii.saveToPicture().getBufferedImage(), out);
        System.out.println("Saving file " + args[1]);

//        ascii.saveToPicture().explore();




    }


    public static void saveBFImg(BufferedImage bufferedImage, String filename) throws IOException {
        File outputfile = new File(filename);
        ImageIO.write(bufferedImage, "png", outputfile);
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }


}

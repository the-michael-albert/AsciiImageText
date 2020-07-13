package com.company.PicHandler;

import com.sun.tools.jconsole.JConsoleContext;

import java.awt.image.BufferedImage;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments
     */
    public Picture() {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName) {
        // let the parent class handle this fileName
        super(fileName);
    }
    public Picture shrink(int factor){
        Picture p = new Picture(this.getHeight(), this.getWidth());
        p.copyPicture(this);
        Picture out = new Picture(p.getHeight() / factor, p.getWidth()/ factor);



        for (int y = 0; y < out.getHeight(); y++) {
            for (int x = 0; x < out.getWidth(); x++) {
                int oY = y * factor;
                int oX = x * factor;
                try {
                    out.getPixel(x, y).setRGB(p.getPixel(oX, oY).getRGB());
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("x" + x + "; Y " + y );
                }
            }
        }
        return out;
    }

    public Picture shrink(int xFactor, int yFactor){
        Picture p = new Picture(this.getHeight(), this.getWidth());
        p.copyPicture(this);
        Picture out = new Picture(p.getHeight() / yFactor, p.getWidth()/ xFactor);



        for (int y = 0; y < out.getHeight(); y++) {
            for (int x = 0; x < out.getWidth(); x++) {
                int oY = y * yFactor;
                int oX = x * xFactor;
                try {
                    out.getPixel(x, y).setRGB(p.getPixel(oX, oY).getRGB());
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("x" + x + "; Y " + y );
                }
            }
        }
        return out;
    }



    /**
     * Constructor that takes the width and height
     *
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width) {
        // let the parent class handle this width and height
        super(width, height);
    }

    /**
     * Constructor that takes a picture and creates a copy of that picture
     *
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture) {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     *
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image) {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////
    /**
     * Method to return a string with information about this picture.
     *
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString() {
        String output = "Picture, filename " + getFileName()
                + " height " + getHeight()
                + " width " + getWidth();
        return output;

    }





} // this } is the end of class Picture, put all new methods before this

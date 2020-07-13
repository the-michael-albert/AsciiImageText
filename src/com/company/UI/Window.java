package com.company.UI;

import java.awt.*;
import javax.swing.*;
// Create a simple GUI window
public class Window {
    public JFrame frame;
    public int w, h;

    public Window(int width, int height){
        //Create and set up the window.
        frame = new JFrame(); //creates a Java Frame called frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w = width  + 15;
        h = height + 8 + 30;
        frame.setSize(w, h); //pixel size of frame in width then height
        show();
    }

    public void show(){
        frame.setVisible(true); //if false then frame will be invisible
    }
    public void hide(){
        frame.setVisible(false); //if false then frame will be invisible
    }

    public void setText(String s){
        frame.setTitle(s);
    }
    public void add(Component component){
        frame.add(component);
    }
}

package com.company.UI;

import java.awt.*;

public class CanvasText {
    public static void drawString(int x, int y, Graphics2D g2d, String fontText, int size){
        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, size));
        g2d.drawString(fontText, x, y);
    }

}

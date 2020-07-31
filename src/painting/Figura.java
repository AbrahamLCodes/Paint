package painting;

/*
Autor: Abraham Luna Cázares
Fecha: Julio 28 2020
*/


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Figura {

    private int x1, y1, x2, y2;
    private Stroke stroke;
    
    public Figura(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public Figura(int x, int y){
        x1 = x;
        y1 = y;
    }

    public void pintar(Graphics2D g, int figura, Color color, int strokeWidth) {
        g.setColor(color);
        stroke = new BasicStroke(strokeWidth);
        g.setStroke(stroke);
        
        switch (figura) {
            case 1:
                // Line
                g.drawLine(x1, y1, x2, y2);
                break;
            case 2:
                //Square
                g.drawRect(x1, y1, x2, y2);
                break;
            case 3:
                //Rectangle
                g.drawRect(x1, y1, x2, y2);
                break;
            case 4:
                //Circle
                g.drawOval(x1, y1, x2, y2);
                break;
            case 5:
                //Oval
                g.drawOval(x1, y1, x2, y2);
                break;
            case 6:
                g.fillOval(x1, y1, strokeWidth, strokeWidth);
                break;
        }
    }
}

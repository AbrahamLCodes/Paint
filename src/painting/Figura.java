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

    public Figura(int x, int y) {
        x1 = x;
        y1 = y;
    }

    /*
        Con este método pintaremos las figuras en el lienzo. En la clase Lienzo
        el método paint manda a pintar todas las figuras que hemos guardado.
        Las propiedades de las figuras (Su id, color y brocha) se pasan como 
        parámetros y con un switch  se van pintando según sus propiedades.
     */
    public void pintar(Graphics2D g, int figura, Color color, int strokeWidth) {
        g.setColor(color);
        stroke = new BasicStroke(strokeWidth);
        g.setStroke(stroke);

        switch (figura) {
            case 1:
                // Línea
                g.drawLine(x1, y1, x2, y2);
                break;
            case 2:
                //Cuadrado
                g.drawRect(x1, y1, x2, y2);
                break;
            case 3:
                //Rectángulo
                g.drawRect(x1, y1, x2, y2);
                break;
            case 4:
                //Círculo
                g.drawOval(x1, y1, x2, y2);
                break;
            case 5:
                //Óvalo
                g.drawOval(x1, y1, x2, y2);
                break;
            case 6:
                //Mano alzada
                g.fillOval(x1, y1, strokeWidth, strokeWidth);
                break;
        }
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

}

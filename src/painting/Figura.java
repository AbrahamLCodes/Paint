package painting;

import java.awt.Color;
import java.awt.Graphics;
import sun.net.www.content.audio.x_aiff;

public class Figura {

    private int x1, y1, x2, y2;
    private int[] rectanguloValoresX, rectanguloValoresY;

    public Figura(int x1, int y1, int x2, int y2) {
        //Para dibujar una Linea
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Figura(int[] rvx, int[] rvy) {
        rectanguloValoresX = rvx;
        rectanguloValoresY = rvy;
    }

    public void pintar(Graphics g, int figura) {
        switch (figura) {
            case 1:
                //Nada seleccionado
                break;
            case 2:
                // Line
                g.drawLine(x1, y1, x2, y2);
                break;
            case 3:

                //Square
                g.setColor(Color.red);
                //Linea 1 (Horizontal en arriba) 
                g.drawLine(rectanguloValoresX[0], rectanguloValoresY[0], rectanguloValoresX[1], rectanguloValoresY[1]);
                //Linea 2 (Vertical en derecha) 
                g.drawLine(rectanguloValoresX[1], rectanguloValoresY[1], rectanguloValoresX[3], rectanguloValoresY[3]);
                //Linea 3 (Horizontal en abajo)  
                g.drawLine(rectanguloValoresX[2], rectanguloValoresY[2], rectanguloValoresX[3], rectanguloValoresY[3]);
                //Linea 4 (Vertical en izquierda) 
                g.drawLine(rectanguloValoresX[0], rectanguloValoresY[0], rectanguloValoresX[2], rectanguloValoresY[2]);
                break;
            case 4:
                //Rectangle
                g.setColor(Color.cyan);
                g.drawLine(rectanguloValoresX[0], rectanguloValoresY[0], rectanguloValoresX[1], rectanguloValoresY[1]);
                g.drawLine(rectanguloValoresX[1], rectanguloValoresY[1], rectanguloValoresX[3], rectanguloValoresY[3]);
                g.drawLine(rectanguloValoresX[2], rectanguloValoresY[2], rectanguloValoresX[3], rectanguloValoresY[3]);
                g.drawLine(rectanguloValoresX[0], rectanguloValoresY[0], rectanguloValoresX[2], rectanguloValoresY[2]);
                break;
            case 5:
                g.setColor(Color.ORANGE);
                g.drawOval(x1, y1, x2, y2);
                g.setColor(Color.MAGENTA);
                g.drawLine(x1 + (x2 / 2), y1, x1 + (x2 / 2), y1 + (y2 / 2));
                g.drawString("Radio = " + (double) (x2 / 10) / 2 + "Mts.", x1 + (x2 / 2), y1 + (y2 / 2));
                //Circle
                break;
            case 6:
                //Oval
                g.setColor(Color.GREEN);
                g.drawOval(x1, y1, x2, y2);
                g.setColor(Color.YELLOW);
                g.drawString("R Ancho = " + (float) (x2 / 10) / 2, x1 + (x2 / 2), y1 + (y2 / 2));
                g.drawLine(x1 + (y2 / 2), y1 + (y2 / 2), x1, y1 + (y2 / 2));
                g.setColor(Color.red);
                g.drawString("R Alto = " + (float) (y2 / 10) / 2, x1 + (x2 / 2), (y1 + (y2 / 2) - 10));
                g.drawLine(x1 + (x2 / 2), y1, x1 + (x2 / 2), y1 + (y2 / 2));
                break;
        }
    }
}

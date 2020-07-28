package painting;

import java.awt.Color;
import java.awt.Graphics;

public class Figura {

    private int x1, y1, x2, y2;

    public Figura(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void pintar(Graphics g, int figura) {
        switch (figura) {

            case 1:
                // Line
                g.setColor(Color.yellow);
                g.drawLine(x1, y1, x2, y2);
                break;
            case 2:
                //Square
                g.setColor(Color.red);
                g.drawRect(x1, y1, x2, y2);
                break;
            case 3:
                //Rectangle
                g.setColor(Color.cyan);
                g.drawRect(x1, y1, x2, y2);
                break;
            case 4:
                //Circle
                g.setColor(Color.ORANGE);
                g.drawOval(x1, y1, x2, y2);
                break;
            case 5:
                //Oval
                g.setColor(Color.GREEN);
                g.drawOval(x1, y1, x2, y2);
                break;
        }
    }
}

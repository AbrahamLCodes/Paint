package painting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;

public class Lienzo extends JPanel {

    int x1, x2, y1, y2;
    int ancho, alto;

    int[] rectanguloValoresX = new int[4];
    int[] rectanguloValoresY = new int[4];

    public Lienzo() {
        setBackground(Color.BLACK);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseMotionHandler);

    }

    public void paint(Graphics g) {
        super.paint(g);

        drawInit(g);

        if (Formulario.getComboBox1().getItemCount() == 6) {

            if (Formulario.getComboBox1().getSelectedIndex() == 0) {
                //"Select an item"
            } else if (Formulario.getComboBox1().getSelectedIndex() == 1) {
                //Draw Line
                Line(g);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 2) {
                // Draw Square
                Square(g);

            } else if (Formulario.getComboBox1().getSelectedIndex() == 3) {
                //Draw Rectangle
                Rectangle(g);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 4) {
                //Draw Circle
                Circle(g);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 5) {
                //Draw Oval
                Oval(g);
            }
        }
    }

    MouseListener mouseHandler = new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {

            if (Formulario.getComboBox1().getSelectedIndex() == 1) {
                LineReleased(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 2) {
                SquareReleased(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 3) {
                RectangleReleased(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 4) {
                CircleReleased(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 5) {
                OvalReleased(e);
            }
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {

            if (Formulario.getComboBox1().getSelectedIndex() == 1) {
                LinePressed(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 2) {
                SquarePressed(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 3) {
                RectanglePressed(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 4) {
                CirclePressed(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 5) {
                OvalPressed(e);
            }
            repaint();
        }
    };

    MouseMotionListener mouseMotionHandler = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {

            if (Formulario.getComboBox1().getSelectedIndex() == 1) {
                LineDragged(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 2) {
                SquareDragged(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 3) {
                RectangleDragged(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 4) {
                CircleDragged(e);
            } else if (Formulario.getComboBox1().getSelectedIndex() == 5) {
                OvalDragged(e);
            }
            repaint();
        }
    };

    private void LinePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        x2 = x1;
        y2 = y1;
    }

    private void LineDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
    }

    private void LineReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
    }

    private void Line(Graphics g) {
        g.setColor(Color.white);
        g.drawLine(x1, y1, x2, y2);
        float numero = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (x1 > x2 && y1 > y2) {
            g.drawString("" + numero / 10 + "mts", x1 - Math.abs((x1 - x2) / 2), y1 - Math.abs((y1 - y2) / 2));
        } else if (x1 < x2 && y1 < y2) {
            g.drawString("" + numero / 10 + "mts", x2 - Math.abs((x1 - x2) / 2), y2 - Math.abs((y1 - y2) / 2));
        } else if (x1 > x2 && y1 < y2) {
            g.drawString("" + numero / 10 + "mts", x1 - Math.abs((x1 - x2) / 2), y2 - Math.abs((y1 - y2) / 2));
        } else if (x1 < x2 && y1 > y2) {
            g.drawString("" + numero / 10 + "mts", x2 - Math.abs((x1 - x2) / 2), y1 - Math.abs((y1 - y2) / 2));
            //Linea hacia arriba
        } else if (x1 == x2 && y1 > y2) {
            g.drawString("" + numero / 10 + "mts", x1 - Math.abs((x1 - x2) / 2), y1 - Math.abs((y1 - y2) / 2));
            //Linea hacia abajo
        } else if (x1 == x2 && y1 < y2) {
            g.drawString("" + numero / 10 + "mts", x1 - Math.abs((x1 - x2) / 2), y2 - Math.abs((y1 - y2) / 2));
            //Linea hacia izquierda
        } else if (x1 > x2 && y1 == y2) {
            g.drawString("" + numero / 10 + "mts", x1 - Math.abs((x1 - x2) / 2), y1 - Math.abs((y1 - y2) / 2));
            //Linea hacia derecha
        } else if (x1 < x2 && y1 == y2) {
            g.drawString("" + numero / 10 + "mts", x2 - Math.abs((x1 - x2) / 2), y1 - Math.abs((y1 - y2) / 2));
        }
    }

    private void SquarePressed(MouseEvent e) {
        //Primer punto
        rectanguloValoresX[0] = e.getX();
        rectanguloValoresY[0] = e.getY();
        //Igualar todos los puntos a cero
        for (int i = 1; i < 4; i++) {
            rectanguloValoresX[i] = rectanguloValoresX[0];
            rectanguloValoresY[i] = rectanguloValoresY[0];
        }

    }

    private void SquareDragged(MouseEvent e) {
        //Punto 2
        rectanguloValoresX[1] = e.getX();
        rectanguloValoresY[1] = rectanguloValoresY[0];

        //Punto 3
        rectanguloValoresX[2] = rectanguloValoresX[0];
        rectanguloValoresY[2] = Math.abs(rectanguloValoresY[0] + (e.getX() - rectanguloValoresX[0]));

        //punto 4
        rectanguloValoresX[3] = e.getX();
        rectanguloValoresY[3] = Math.abs(rectanguloValoresY[0] + (e.getX() - rectanguloValoresX[0]));
    }

    private void SquareReleased(MouseEvent e) {
        //Punto 2 Esta bien
        rectanguloValoresX[1] = e.getX();
        rectanguloValoresY[1] = rectanguloValoresY[0];

        //Punto 3
        rectanguloValoresX[2] = rectanguloValoresX[0];
        rectanguloValoresY[2] = Math.abs(rectanguloValoresY[0] + (e.getX() - rectanguloValoresX[0]));

        //punto 4
        rectanguloValoresX[3] = e.getX();
        rectanguloValoresY[3] = Math.abs(rectanguloValoresY[0] + (e.getX() - rectanguloValoresX[0]));
    }

    private void Square(Graphics g) {
        g.setColor(Color.red);
        //Linea 1 (Horizontal en arriba) 
        g.drawLine(rectanguloValoresX[0], rectanguloValoresY[0], rectanguloValoresX[1], rectanguloValoresY[1]);
        //Linea 2 (Vertical en derecha) 
        g.drawLine(rectanguloValoresX[1], rectanguloValoresY[1], rectanguloValoresX[3], rectanguloValoresY[3]);
        //Linea 3 (Horizontal en abajo)  
        g.drawLine(rectanguloValoresX[2], rectanguloValoresY[2], rectanguloValoresX[3], rectanguloValoresY[3]);
        //Linea 4 (Vertical en izquierda) 
        g.drawLine(rectanguloValoresX[0], rectanguloValoresY[0], rectanguloValoresX[2], rectanguloValoresY[2]);

        float numero = (float) rectanguloValoresX[1] - (float) rectanguloValoresX[0];
        g.drawString("" + numero / 10 + "mts/L", rectanguloValoresX[1] - Math.abs((rectanguloValoresX[0] - rectanguloValoresX[1]) / 2),
                rectanguloValoresY[0] - Math.abs((rectanguloValoresY[0] - rectanguloValoresY[1]) / 2));
    }

    private void RectanglePressed(MouseEvent e) {
        //Primer punto
        rectanguloValoresX[0] = e.getX();
        rectanguloValoresY[0] = e.getY();

        //Igualar todos los puntos a cero
        for (int i = 1; i < 4; i++) {
            rectanguloValoresX[i] = rectanguloValoresX[0];
            rectanguloValoresY[i] = rectanguloValoresY[0];
        }
    }

    private void RectangleDragged(MouseEvent e) {
        //Punto 2
        rectanguloValoresX[1] = e.getX();
        rectanguloValoresY[1] = rectanguloValoresY[0];

        //Punto 3
        rectanguloValoresX[2] = rectanguloValoresX[0];
        rectanguloValoresY[2] = e.getY();

        //punto 4
        rectanguloValoresX[3] = e.getX();
        rectanguloValoresY[3] = e.getY();
    }

    private void RectangleReleased(MouseEvent e) {
        //Punto 2
        rectanguloValoresX[1] = e.getX();
        rectanguloValoresY[1] = rectanguloValoresY[0];

        //Punto 3
        rectanguloValoresX[2] = rectanguloValoresX[0];
        rectanguloValoresY[2] = e.getY();

        //punto 4
        rectanguloValoresX[3] = e.getX();
        rectanguloValoresY[3] = e.getY();
    }

    private void Rectangle(Graphics g) {
        float numero;

        g.setColor(Color.cyan);
        g.drawLine(rectanguloValoresX[0], rectanguloValoresY[0], rectanguloValoresX[1], rectanguloValoresY[1]);
        g.drawLine(rectanguloValoresX[1], rectanguloValoresY[1], rectanguloValoresX[3], rectanguloValoresY[3]);
        g.drawLine(rectanguloValoresX[2], rectanguloValoresY[2], rectanguloValoresX[3], rectanguloValoresY[3]);
        g.drawLine(rectanguloValoresX[0], rectanguloValoresY[0], rectanguloValoresX[2], rectanguloValoresY[2]);

        //Punto 1 al punto 2 (Derecha) x2 y1 
        numero = (float) Math.sqrt(Math.pow(rectanguloValoresX[1] - rectanguloValoresX[0], 2) + Math.pow(rectanguloValoresY[1] - rectanguloValoresY[0], 2));
        g.drawString("" + numero / 10 + "mts/L", rectanguloValoresX[1] - Math.abs((rectanguloValoresX[0] - rectanguloValoresX[1]) / 2), rectanguloValoresY[0] - Math.abs((rectanguloValoresY[0] - rectanguloValoresY[1]) / 2));

        //Punto 1 al punto 3 (Abajo) x1 y3
        numero = (float) Math.sqrt(Math.pow(rectanguloValoresX[2] - rectanguloValoresX[0], 2) + Math.pow(rectanguloValoresY[2] - rectanguloValoresY[0], 2));
        g.drawString("" + numero / 10 + "mts/L", rectanguloValoresX[0] - Math.abs((rectanguloValoresX[0] - rectanguloValoresX[2]) / 2), rectanguloValoresY[2] - Math.abs((rectanguloValoresY[0] - rectanguloValoresY[2]) / 2));

        //Punto 2 al punto 4 (Abajo) x2 y4 
        numero = (float) Math.sqrt(Math.pow(rectanguloValoresX[3] - rectanguloValoresX[1], 2) + Math.pow(rectanguloValoresY[3] - rectanguloValoresY[1], 2));
        g.drawString("" + numero / 10 + "mts/L", rectanguloValoresX[1] - Math.abs((rectanguloValoresX[1] - rectanguloValoresX[3]) / 2), rectanguloValoresY[3] - Math.abs((rectanguloValoresY[1] - rectanguloValoresY[3]) / 2));

        //Punto 3 al punto 4 (derecha) x3 y4
        numero = (float) Math.sqrt(Math.pow(rectanguloValoresX[3] - rectanguloValoresX[2], 2) + Math.pow(rectanguloValoresY[3] - rectanguloValoresY[2], 2));
        g.drawString("" + numero / 10 + "mts/L", rectanguloValoresX[3] - Math.abs((rectanguloValoresX[2] - rectanguloValoresX[3]) / 2), rectanguloValoresY[2] - Math.abs((rectanguloValoresY[2] - rectanguloValoresY[3]) / 2));
    }

    private void CirclePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;
    }

    private void CircleDragged(MouseEvent e) {
        ancho = e.getX() - x1;

        alto = ancho;
    }

    private void CircleReleased(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = ancho;
    }

    private void Circle(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawOval(x1, y1, ancho, alto);
        g.setColor(Color.MAGENTA);
        g.drawLine(x1 + (ancho / 2), y1, x1 + (ancho / 2), y1 + (alto / 2));
        g.drawString("Radio = " + (double) (ancho / 10) / 2 + "Mts.", x1 + (ancho / 2), y1 + (alto / 2));
    }

    private void OvalPressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;
    }

    private void OvalDragged(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = e.getY() - y1;

    }

    private void OvalReleased(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = e.getY() - y1;
    }

    private void Oval(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawOval(x1, y1, ancho, alto);
        g.setColor(Color.YELLOW);
        g.drawString("R Ancho = " + (float) (ancho / 10) / 2, x1 + (ancho / 2), y1 + (alto / 2));
        g.drawLine(x1 + (ancho / 2), y1 + (alto / 2), x1, y1 + (alto / 2));
        g.setColor(Color.red);
        g.drawString("R Alto = " + (float) (alto / 10) / 2, x1 + (ancho / 2), (y1 + (alto / 2) - 10));
        g.drawLine(x1 + (ancho / 2), y1, x1 + (ancho / 2), y1 + (alto / 2));
    }

    private void drawInit(Graphics g) {
        //Pintar en horizontal
        for (int i = 0; i < this.getHeight(); i += 10) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(0, i, this.getWidth(), i);
        }
        //Pintar en vertical
        for (int i = 0; i < this.getWidth(); i += 10) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(i, 0, i, this.getHeight());
        }
    }
}

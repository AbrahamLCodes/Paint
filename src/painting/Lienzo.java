package painting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

public class Lienzo extends JPanel {

    private int x1, x2, y1, y2;
    private int ancho, alto;

    private Vector<Figura> vectorFiguras;
    private Vector<Integer> vectorId;

    public Lienzo() {
        setBackground(Color.BLACK);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseMotionHandler);
        vectorFiguras = new Vector<>();
        vectorId = new Vector<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        drawInit(g);

        if (Formulario.getComboBox1().getItemCount() == 6) {
            int i = 0;
            for (Figura figuras : vectorFiguras) {
                figuras.pintar(g, vectorId.get(i));
                i++;
            }
        }
    }

    MouseListener mouseHandler = new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {

            switch (Formulario.getComboBox1().getSelectedIndex()) {
                case 1:
                    LineReleased(e);
                    break;
                case 2:
                    SquareReleased(e);
                    break;
                case 3:
                    RectangleReleased(e);
                    break;
                case 4:
                    CircleReleased(e);
                    break;
                case 5:
                    OvalReleased(e);
                    break;
                default:
                    break;
            }
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {

            if (Formulario.getComboBox1().getSelectedIndex() != 0) {
                vectorId.add(Formulario.getComboBox1().getSelectedIndex());
            }

            switch (Formulario.getComboBox1().getSelectedIndex()) {
                case 1:
                    LinePressed(e);
                    break;
                case 2:
                    SquarePressed(e);
                    break;
                case 3:
                    RectanglePressed(e);
                    break;
                case 4:
                    CirclePressed(e);
                    break;
                case 5:
                    OvalPressed(e);
                    break;
                default:
                    break;
            }
            repaint();
        }
    };

    MouseMotionListener mouseMotionHandler = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {

            switch (Formulario.getComboBox1().getSelectedIndex()) {
                case 1:
                    LineDragged(e);
                    break;
                case 2:
                    SquareDragged(e);
                    break;
                case 3:
                    RectangleDragged(e);
                    break;
                case 4:
                    CircleDragged(e);
                    break;
                case 5:
                    OvalDragged(e);
                    break;
                default:
                    break;
            }
            repaint();
        }

    };

    private void LinePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        x2 = x1;
        y2 = y1;

        vectorFiguras.add(new Figura(x1, y1, x2, y2));
    }

    private void LineDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, x2, y2));
    }

    private void LineReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, x2, y2));
    }

    private void SquarePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        vectorFiguras.add(new Figura(x1, y1, ancho, alto));
    }

    private void SquareDragged(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = ancho;

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, ancho, alto));
    }

    private void SquareReleased(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = ancho;

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, ancho, alto));
    }

    private void RectanglePressed(MouseEvent e) {

        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        vectorFiguras.add(new Figura(x1, y1, ancho, alto));
    }

    private void RectangleDragged(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = e.getY() - y1;

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, ancho, alto));
    }

    private void RectangleReleased(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = e.getY() - y1;

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, ancho, alto));
    }

    private void CirclePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        vectorFiguras.add(new Figura(x1, y1, ancho, alto));
    }

    private void CircleDragged(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = ancho;

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, ancho, alto));
    }

    private void CircleReleased(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = ancho;

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, ancho, alto));
    }

    private void OvalPressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        vectorFiguras.add(new Figura(x1, y1, ancho, alto));
    }

    private void OvalDragged(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = e.getY() - y1;

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, ancho, alto));
    }

    private void OvalReleased(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = e.getY() - y1;

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, ancho, alto));
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

    private int ultimoIndex() {
        return vectorFiguras.size() - 1;
    }
}

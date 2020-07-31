package painting;

/*
Autor: Abraham Luna Cázares
Fecha: Julio 28 2020
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Lienzo extends JPanel {

    private int x1, x2, y1, y2;
    private int ancho, alto;

    private ArrayList<Figura> vectorFiguras;
    private ArrayList<Integer> vectorId;
    private ArrayList<Color> vectorColor;
    private ArrayList<Integer> vectorStroke;

    //Vectores para soporte de eliminacion de multiples Manos Alzadas
    private ArrayList<Integer> alzadaIniciales;
    private ArrayList<Integer> alzadaFinales;

    private Stack<Figura> pila;
    // Stack<String> stackOfCards = new Stack<>();


    public Lienzo() {
        setBackground(Color.BLACK);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseMotionHandler);
        vectorFiguras = new ArrayList<>();
        vectorId = new ArrayList<>();
        vectorColor = new ArrayList<>();
        vectorStroke = new ArrayList<>();

        alzadaIniciales = new ArrayList<>();
        alzadaFinales = new ArrayList<>();

        pila = new Stack<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        drawInit(g);

        if (Formulario.getComboBox1().getItemCount() == 7) {
            int i = 0;
            for (Figura figuras : vectorFiguras) {
                figuras.pintar((Graphics2D) g, vectorId.get(i), vectorColor.get(i),
                        vectorStroke.get(i));
                i++;
            }
        }
    }

    MouseListener mouseHandler = new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {

            switch (Formulario.getComboBox1().getSelectedIndex()) {
                case 1:
                    lineMoved(e);
                    break;
                case 2:
                    squareMoved(e);
                    break;
                case 3:
                    rectangleMoved(e);
                    break;
                case 4:
                    circleMoved(e);
                    break;
                case 5:
                    ovalMoved(e);
                    break;
                case 6:
                    free(e);
                    alzadaFinales.add(ultimoIndex());
                default:
                    break;
            }
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {

            switch (Formulario.getComboBox1().getSelectedIndex()) {
                case 1:
                    linePressed(e);
                    break;
                case 2:
                    squarePressed(e);
                    break;
                case 3:
                    rectanglePressed(e);
                    break;
                case 4:
                    circlePressed(e);
                    break;
                case 5:
                    ovalPressed(e);
                    break;
                case 6:
                    //Subindice de la ultima figura 
                    free(e);
                    alzadaIniciales.add(ultimoIndex());
                    break;
                default:
                    break;
            }
            if (Formulario.getComboBox1().getSelectedIndex() != 0
                    && Formulario.getComboBox1().getSelectedIndex() != 6) {

                vectorId.add(Formulario.getComboBox1().getSelectedIndex());
                vectorColor.add(Formulario.getColor());
                vectorStroke.add(Integer.parseInt(Formulario.getBrochaLabel().getText()));
            }
            repaint();
        }
    };

    MouseMotionListener mouseMotionHandler = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {

            switch (Formulario.getComboBox1().getSelectedIndex()) {
                case 1:
                    lineMoved(e);
                    break;
                case 2:
                    squareMoved(e);
                    break;
                case 3:
                    rectangleMoved(e);
                    break;
                case 4:
                    circleMoved(e);
                    break;
                case 5:
                    ovalMoved(e);
                    break;
                case 6:
                    free(e);
                    break;
                default:
                    break;
            }
            repaint();
        }

    };

    public void undo() {
        if (ultimoIndex() != -1) {
            if (vectorId.get(ultimoIndex()) == 6) {
                undoFree();
            } else {
                vectorId.remove(ultimoIndex());
                vectorColor.remove(ultimoIndex());
                vectorStroke.remove(ultimoIndex());
                vectorFiguras.remove(ultimoIndex());
                repaint();
            }

        } else {
            JOptionPane.showMessageDialog(null, "No hay figuras para borrar");
        }
    }

    private void undoFree() {
        int i;
        int j = alzadaIniciales.get(alzadaFinales.size() - 1);

        for (i = alzadaFinales.get(alzadaFinales.size() - 1); i >= j; i--) {
            vectorId.remove(i);
            vectorColor.remove(i);
            vectorStroke.remove(i);
            vectorFiguras.remove(i);
        }

        if (alzadaFinales.size() > 0 && alzadaIniciales.size() > 0) {
            alzadaFinales.remove(alzadaFinales.size() - 1);
            alzadaIniciales.remove(alzadaIniciales.size() - 1);
        }

        repaint();
    }

    private void free(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        vectorId.add(Formulario.getComboBox1().getSelectedIndex());
        vectorColor.add(Formulario.getColor());
        vectorStroke.add(Integer.parseInt(Formulario.getBrochaLabel().getText()));
        vectorFiguras.add(new Figura(x1, y1));
    }

    private int ultimoIndex() {
        return vectorFiguras.size() - 1;
    }

    private void linePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        x2 = x1;
        y2 = y1;

        vectorFiguras.add(new Figura(x1, y1, x2, y2));
    }

    private void lineMoved(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, x2, y2));
    }

    private void squarePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        vectorFiguras.add(new Figura(x1, y1, ancho, alto));
    }

    private void squareMoved(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = ancho;

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, ancho, alto));
    }

    private void rectanglePressed(MouseEvent e) {

        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        vectorFiguras.add(new Figura(x1, y1, ancho, alto));
    }

    private void rectangleMoved(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = e.getY() - y1;

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, ancho, alto));
    }

    private void circlePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        vectorFiguras.add(new Figura(x1, y1, ancho, alto));
    }

    private void circleMoved(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = ancho;

        vectorFiguras.set(ultimoIndex(), new Figura(x1, y1, ancho, alto));
    }

    private void ovalPressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        vectorFiguras.add(new Figura(x1, y1, ancho, alto));
    }

    private void ovalMoved(MouseEvent e) {
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
}

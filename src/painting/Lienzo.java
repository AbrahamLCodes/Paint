package painting;

/*
Autor: Abraham Luna Cázares
Fecha: Julio 28 2020
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Lienzo extends Canvas {

    private int x1, x2, y1, y2;
    private int ancho, alto;

    //Vectores para soporte de eliminacion de multiples Manos Alzadas
    private ArrayList<Integer> alzadaIniciales;
    private ArrayList<Integer> alzadaFinales;

    private Stack<Figura> pilaFiguras;
    private Stack<Integer> pilaId;
    private Stack<Color> pilaColor;
    private Stack<Integer> pilaStroke;

    public Lienzo() {
        setBackground(Color.BLACK);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseMotionHandler);

        pilaFiguras = new Stack<>();
        pilaId = new Stack<>();
        pilaColor = new Stack<>();
        pilaStroke = new Stack<>();

        alzadaIniciales = new ArrayList<>();
        alzadaFinales = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        drawInit(g);

        if (Formulario.getComboBox1().getItemCount() == 7) {
            int i = 0;
            for (Figura figuras : pilaFiguras) {
                figuras.pintar((Graphics2D) g, pilaId.get(i), pilaColor.get(i),
                        pilaStroke.get(i));
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
                    alzadaFinales.add(pilaFiguras.size());
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
                    alzadaIniciales.add(pilaFiguras.size());
                    break;
                default:
                    break;
            }
            if (Formulario.getComboBox1().getSelectedIndex() != 0
                    && Formulario.getComboBox1().getSelectedIndex() != 6) {

                pilaId.push(Formulario.getComboBox1().getSelectedIndex());
                pilaColor.push(Formulario.getColor());
                pilaStroke.push(Integer.parseInt(Formulario.getBrochaLabel().getText()));
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
        if (!pilaFiguras.isEmpty()) {

            if (pilaId.peek() == 6) {
                undoFree();
            } else {
                pilaId.pop();
                pilaColor.pop();
                pilaStroke.pop();
                pilaFiguras.pop();
                repaint();
            }

        } else {
            JOptionPane.showMessageDialog(null, "No hay figuras para borrar");
        }
    }

    private void undoFree() {
        int j = alzadaIniciales.get(alzadaIniciales.size() - 1);

        for (int i = alzadaFinales.get(alzadaFinales.size() - 1); i >= j; i--) {

            pilaId.pop();
            pilaColor.pop();
            pilaStroke.pop();
            pilaFiguras.pop();
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

        pilaId.push(Formulario.getComboBox1().getSelectedIndex());
        pilaColor.push(Formulario.getColor());
        pilaStroke.push(Integer.parseInt(Formulario.getBrochaLabel().getText()));
        pilaFiguras.push(new Figura(x1, y1));
    }

    private Figura ultimoIndex() {
        return pilaFiguras.peek();
    }

    private void linePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        x2 = x1;
        y2 = y1;

        pilaFiguras.push(new Figura(x1, y1, x2, y2));
    }

    private void lineMoved(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();

        actualizar(x2, y2);
    }

    private void squarePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        pilaFiguras.push(new Figura(x1, y1, ancho, alto));

    }

    private void squareMoved(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = ancho;
        actualizar(ancho, alto);
    }

    private void rectanglePressed(MouseEvent e) {

        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        pilaFiguras.push(new Figura(x1, y1, ancho, alto));
    }

    private void rectangleMoved(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = e.getY() - y1;
        actualizar(ancho, alto);
    }

    private void circlePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        pilaFiguras.push(new Figura(x1, y1, ancho, alto));
    }

    private void circleMoved(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = ancho;
        actualizar(ancho, alto);
    }

    private void ovalPressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();

        ancho = 0;
        alto = ancho;

        pilaFiguras.push(new Figura(x1, y1, ancho, alto));

    }

    private void ovalMoved(MouseEvent e) {
        ancho = e.getX() - x1;
        alto = e.getY() - y1;
        actualizar(ancho, alto);
    }

    private void actualizar(int ancho, int alto) {
        ultimoIndex().setX2(ancho);
        ultimoIndex().setY2(alto);
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

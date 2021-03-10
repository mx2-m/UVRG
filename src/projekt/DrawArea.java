package projekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;


public class DrawArea extends JComponent {

    // Image in which we're going to draw
    private Image image;
    // Graphics2D object ==> used to draw on
    private Graphics2D g2;
    // Mouse coordinates
    private double x, y, oldX, oldY, x1, y1, x2, y2, x3, y3, x4, y4;
    private int n = 1;
    public int clicked = 0;
    public int risi1;

    public DrawArea() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                switch (risi1) {
                    case 1: {
                        x = e.getX();
                        y = e.getY();
                        System.out.println("X:" + x + " Y:" + y);
                        if (g2 != null) {
                            // draw line if g2 context not null
                            g2.fillRect((int) x, (int) y, 5, 5);
                            g2.drawString("T" + n, (int) x, (int) y);
                            // refresh draw area to repaint
                            repaint();
                            // store current coords x,y as olds x,y
                            clicked++;
                            n++;
                            if (clicked == 1) {
                                x1 = x;
                                y1 = y;
                            }

                            if (clicked == 2) {
                                x2 = e.getX();
                                y2 = e.getY();
                            }
                            if (clicked > 2) {
                                clear();
                                clicked = 0;
                                n = 1;
                                oldX = 0;
                                oldY = 0;
                            }
                        }
                    }
                    break;

                    case 2: {
                        x = e.getX();
                        y = e.getY();
                        System.out.println("X:" + x + " Y:" + y);
                        if (g2 != null) {
                            g2.fillRect((int) x, (int) y, 5, 5);
                            g2.drawString("T" + n, (int) x, (int) y);

                            if (x != 0 && y != 0 && oldX != 0 && oldY != 0 && clicked == 2) {
                                g2.drawLine((int) x, (int) y, (int) oldX, (int) oldY);
                            }

                            repaint();
                            oldX = x;
                            oldY = y;
                            clicked++;
                            n++;

                            if (clicked == 1) {
                                x1 = x;
                                y1 = y;
                            }

                            if (clicked == 2) {
                                x2 = e.getX();
                                y2 = e.getY();
                            }
                            if (clicked == 3) {
                                x3 = x;
                                y3 = y;
                            }
                            if (clicked > 3) {
                                clear();
                                clicked = 0;
                                n = 1;
                                oldX = 0;
                                oldY = 0;
                            }
                        }
                    }

                    break;
                    case 3: {
                        x = e.getX();
                        y = e.getY();
                        System.out.println("X:" + x + " Y:" + y);
                        if (g2 != null) {
                            // draw line if g2 context not null
                            g2.fillRect((int) x, (int) y, 5, 5);
                            g2.drawString("T" + n, (int) x, (int) y);
                            if (x != 0 && y != 0 && oldX != 0 && oldY != 0 && clicked != 2) {
                                g2.drawLine((int) x, (int) y, (int) oldX, (int) oldY);
                            }
                            // refresh draw area to repaint
                            repaint();
                            // store current coords x,y as olds x,y
                            oldX = x;
                            oldY = y;
                            clicked++;
                            n++;
                            if (clicked == 1) {
                                x1 = x;
                                y1 = y;
                            }

                            if (clicked == 2) {
                                x2 = e.getX();
                                y2 = e.getY();
                            }
                            if (clicked == 3) {
                                x3 = x;
                                y3 = y;
                            }
                            if (clicked == 4) {
                                x4 = x;
                                y4 = y;
                            }
                            if (clicked > 4) {
                                clear();
                                clicked = 0;
                                n = 1;
                                oldX = 0;
                                oldY = 0;
                            }
                        }
                    }
                    break;
                }
            }
        });


    }


    protected void paintComponent(Graphics g) {
        if (image == null) {
            // image to draw null ==> we create
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            // enable antialiasing
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // clear draw area
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        g2.setPaint(Color.white);
        // draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }

    public void red() {
        // apply red color on g2 context
        g2.setPaint(Color.red);
    }


    public void drawingDveTocki(double x1, double y1, double x2, double y2) {

        g2.fillRect((int) x1, (int) y1, 5, 5);
        g2.drawString("T1", (int) x1, (int) y1);
        g2.fillRect((int) x2, (int) y2, 5, 5);
        g2.drawString("T2", (int) x2, (int) y2);
        g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        repaint();
    }

    public void drawingDveTockiIDaljica(double x1, double y1, double x2, double y2, double x3, double y3) {

        g2.fillRect((int) x1, (int) y1, 5, 5);
        g2.drawString("T1", (int) x1, (int) y1);
        g2.fillRect((int) x2, (int) y2, 5, 5);
        g2.drawString("T2", (int) x2, (int) y2);
        g2.fillRect((int) x3, (int) y3, 5, 5);
        g2.drawString("T3", (int) x3, (int) y3);
        g2.drawLine((int) x3, (int) y3, (int) x2, (int) y2);
        repaint();
    }

    public void drawingTocka(double x1, double y1) {

        g2.fillRect((int) x1, (int) y1, 5, 5);
        g2.drawString("Tp", (int) x1, (int) y1);
        repaint();
    }

    public void drawingDaljica(double x1, double y1, double x2, double y2) {

        g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        repaint();
    }

    public void drawingDveDaljici(double x1, double y1, double x2, double y2,double x3, double y3,double x4,double y4) {

        g2.fillRect((int) x1, (int) y1, 5, 5);
        g2.drawString("T1", (int) x1, (int) y1);
        g2.fillRect((int) x2, (int) y2, 5, 5);
        g2.drawString("T2", (int) x2, (int) y2);
        g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

        g2.fillRect((int) x3, (int) y3, 5, 5);
        g2.drawString("T3", (int) x3, (int) y3);
        g2.fillRect((int) x4, (int) y4, 5, 5);
        g2.drawString("T4", (int) x4, (int) y4);
        g2.drawLine((int) x3, (int) y3, (int) x4, (int) y4);
        repaint();
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    public double getX4() {
        return x4;
    }

    public double getY4() {
        return y4;
    }
}
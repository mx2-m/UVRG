package projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class App {

    private JPanel panelMain;
    private JRadioButton enaTockaEnaDaljicaRadioButton;
    private JButton izracunajButton;
    private JRadioButton dveTockiRadioButton;
    private JRadioButton dveDaljiciRadioButton;
    private JLabel nacin;
    private JTextField t1x;
    private JTextField t1y;
    private JTextField t2x;
    private JTextField t2y;
    private JTextField t3x;
    private JTextField t3y;
    private JTextField t4x;
    private JTextField t4y;
    private JLabel t1xLabel;
    private JLabel t1yLabel;
    private JLabel t2xLabel;
    private JLabel t2yLabel;
    private JLabel t3xLabel;
    private JLabel t3yLabel;
    private JLabel t4xLabel;
    private JLabel t4yLabel;
    private ButtonGroup buttonGroup;
    DrawArea drawArea = new DrawArea();
    private JButton clearBtn;
    int solution;
    double x1, y1, x2, y2, razdaljaE, razdaljaM, razdaljaC, x3, y3, razdalja, razdalja2, razdalja3;
    Vector2D v1;
    Vector2D v2;
    double sp;
    double tpx, tpy, crossProduct, x4, y4;
    String side, daljica;
    double a, b, d, ua, ub;


    public App() {
    }

    ActionListener clear = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {

                drawArea.clear();
            }
        }
    };

    boolean equal(double v1, double v2) {
        double eps = 0.000001;
        if (Math.abs(v1 - v2) < eps)
            return true;
        else
            return false;
    }

    ActionListener dveTocki = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("dve tocki");
            drawArea.risi1 = 1;
            System.out.println(" " + drawArea.risi1);
            t3x.setVisible(false);
            t3y.setVisible(false);
            t3xLabel.setVisible(false);
            t3yLabel.setVisible(false);

            t4x.setVisible(false);
            t4y.setVisible(false);
            t4xLabel.setVisible(false);
            t4yLabel.setVisible(false);


            if (drawArea.clicked > 1) {
                t1x.setText(String.valueOf(drawArea.getX1()));
                t1y.setText(String.valueOf(drawArea.getY1()));
                t2x.setText(String.valueOf(drawArea.getX2()));
                t2y.setText(String.valueOf(drawArea.getY2()));
            }


            x1 = Double.parseDouble(t1x.getText());
            y1 = Double.parseDouble(t1y.getText());
            x2 = Double.parseDouble(t2x.getText());
            y2 = Double.parseDouble(t2y.getText());

            System.out.println("x1 " + x1 + "y1 " + y1 + "x2 " + x2 + "y2 " + y2);


            razdaljaE = evklidskaRazdalja(x1, y1, x2, y2);
            razdaljaM = manhattanovaRazdalja(x1, y1, x2, y2);
            razdaljaC = chebyshevaRazdalja(x1, y1, x2, y2);


            solution = 1;


        }

    };

    ActionListener izracunaj = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (solution) {
                case 1: {
                    System.out.println("IZRACUNAJ" + "x1 " + x1 + "y1 " + y1 + "x2 " + x2 + "y2 " + y2);
                    drawArea.drawingDveTocki(x1, y1, x2, y2);
                    JOptionPane.showMessageDialog(panelMain,
                            "Evklidska razdalja:  " + razdaljaE + "\n" + "Manhattanova razdalja: " + razdaljaM + "\n" + "Chebysheva razdalja: " + razdaljaC, "Razdalja med T1 in T2", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
                case 2: {
                    if (sp >= 0 && sp <= v1.length()) {
                        drawArea.drawingDveTockiIDaljica(x1, y1, x2, y2, x3, y3);
                        drawArea.drawingTocka(tpx, tpy);
                        drawArea.red();
                        drawArea.drawingDaljica(x1, y1, tpx, tpy);


                        JOptionPane.showMessageDialog(panelMain, "Tp se nahaja na( " + tpx + "," + tpy + ")" + "\n"
                                + "Razdalja med T1 in Tp je " + razdalja + "\n" + side, "Razdalja med T1 in Tp", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        drawArea.drawingDveTockiIDaljica(x1, y1, x2, y2, x3, y3);

                        if (razdalja2 < razdalja3) {
                            JOptionPane.showMessageDialog(panelMain, "Projekcija T1 na daljico ne obstaja" + "\n"
                                    + "Razdalja med T1 in T2 je " + razdalja2 + "\n" + side, "Razdalja med T1 in T2", JOptionPane.INFORMATION_MESSAGE);

                            drawArea.red();
                            drawArea.drawingDaljica(x1, y1, x2, y2);
                        } else {
                            drawArea.red();
                            drawArea.drawingDaljica(x1, y1, x3, y3);

                            JOptionPane.showMessageDialog(panelMain, "Projekcija T1 na daljico ne obstaja" + "\n"
                                    + "Razdalja med T1 in T3 je " + razdalja3 + "\n" + side, "Razdalja med T1 in T3", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                }
                break;
                case 3: {
                    if (equal(d, a) && equal(d, b) && equal(d, 0)) {
                        daljica = " Daljici se sovpadata";
                        drawArea.drawingDveDaljici(x1, y1, x2, y2, x3, y3, x4, y4);
                        drawArea.red();
                        drawArea.drawingDaljica(x3, y3, x4, y4);

                        JOptionPane.showMessageDialog(panelMain, " Daljici T1 T2 in T3 T4 se sovpadata", daljica, JOptionPane.INFORMATION_MESSAGE);

                    } else if (equal(d, 0)) {
                        daljica = " Daljici sta vzaporedni";
                        drawArea.drawingDveDaljici(x1, y1, x2, y2, x3, y3, x4, y4);
                        JOptionPane.showMessageDialog(panelMain, " Daljici T1 T2 in T3 T4 sta vzaporedni", daljica, JOptionPane.INFORMATION_MESSAGE);
                    } else if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1) {
                        daljica = " Daljici se sekata";
                        tpx = x1 + ua * (x2 - x1);
                        tpy = y1 + ua * (y2 - y1);
                        drawArea.drawingDveDaljici(x1, y1, x2, y2, x3, y3, x4, y4);
                        drawArea.red();
                        drawArea.drawingTocka(tpx, tpy);
                        if (equal(tpx, x1) || equal(tpx, x2) || equal(tpx, x3) || equal(tpx, x4) || equal(tpx, y1) || equal(tpx, y2) || equal(tpx, y3) || equal(tpx, y4)) {
                            JOptionPane.showMessageDialog(panelMain, "Daljici T1 T2 in T3 T4 se dotikata" + "\n"
                                    + "Tacka dotikanja je ( " + tpx + "," + tpy + " )" + "\n", "Daljici se dotikata", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(panelMain, "Daljici T1 T2 in T3 T4 se sekata" + "\n"
                                    + "Tacka presecista je ( " + tpx + "," + tpy + " )" + "\n", daljica, JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        daljica = " Daljici se ne sekata";
                        drawArea.drawingDveDaljici(x1, y1, x2, y2, x3, y3, x4, y4);
                        JOptionPane.showMessageDialog(panelMain, "Daljici se ne sekata", daljica, JOptionPane.INFORMATION_MESSAGE);
                    }


                }
            } //kraj switch


        }
    };

    ActionListener drugiNacin = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("ena tocka ena daljica");
            drawArea.risi1 = 2;

            t3x.setVisible(true);
            t3y.setVisible(true);
            t3xLabel.setVisible(true);
            t3yLabel.setVisible(true);

            t4x.setVisible(false);
            t4y.setVisible(false);
            t4xLabel.setVisible(false);
            t4yLabel.setVisible(false);


            if (drawArea.clicked > 2) {
                t1x.setText(String.valueOf(drawArea.getX1()));
                t1y.setText(String.valueOf(drawArea.getY1()));
                t2x.setText(String.valueOf(drawArea.getX2()));
                t2y.setText(String.valueOf(drawArea.getY2()));
                t3x.setText(String.valueOf(drawArea.getX3()));
                t3y.setText(String.valueOf(drawArea.getY3()));
            }


            x1 = Double.parseDouble(t1x.getText());
            y1 = Double.parseDouble(t1y.getText());
            x2 = Double.parseDouble(t2x.getText());
            y2 = Double.parseDouble(t2y.getText());
            x3 = Double.parseDouble(t3x.getText());
            y3 = Double.parseDouble(t3y.getText());

            System.out.println("x1 " + x1 + "y1 " + y1 + "x2 " + x2 + "y2 " + y2 + "x3 " + x3 + "y3 " + y3);

            //PROJEKCIJA TACKE NA DALJICO

            v1 = new Vector2D(x3 - x2, y3 - y2);
            v2 = new Vector2D(x1 - x2, y1 - y2);
            Vector2D vN = v1.normalize();   //bazni vektor
            sp = vN.dotProduct(v2);       //skalarni produkt
            tpx = x2 + vN.dX * sp;
            tpy = y2 + vN.dY * sp;

            crossProduct = v1.crossProduct(v2);   //vektorski produkt

            if (crossProduct > 0) {
                side = "Nahaja se na levi strani ";
            } else if (crossProduct < 0) {
                side = " Nahaja se na desni strani ";
            } else {
                side = "Lezi na daljici";
            }

            razdalja = evklidskaRazdalja(x1, y1, tpx, tpy);
            razdalja2 = evklidskaRazdalja(x1, y1, x2, y2);
            razdalja3 = evklidskaRazdalja(x1, y1, x3, y3);

            solution = 2;
        }
    };

    ActionListener dveDaljici = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            drawArea.risi1 = 3;

            t3x.setVisible(true);
            t3y.setVisible(true);
            t3xLabel.setVisible(true);
            t3yLabel.setVisible(true);

            t4x.setVisible(true);
            t4y.setVisible(true);
            t4xLabel.setVisible(true);
            t4yLabel.setVisible(true);

            if (drawArea.clicked > 3) {
                t1x.setText(String.valueOf(drawArea.getX1()));
                t1y.setText(String.valueOf(drawArea.getY1()));
                t2x.setText(String.valueOf(drawArea.getX2()));
                t2y.setText(String.valueOf(drawArea.getY2()));
                t3x.setText(String.valueOf(drawArea.getX3()));
                t3y.setText(String.valueOf(drawArea.getY3()));
                t4x.setText(String.valueOf(drawArea.getX4()));
                t4y.setText(String.valueOf(drawArea.getY4()));
            }


            x1 = Double.parseDouble(t1x.getText());
            y1 = Double.parseDouble(t1y.getText());
            x2 = Double.parseDouble(t2x.getText());
            y2 = Double.parseDouble(t2y.getText());
            x3 = Double.parseDouble(t3x.getText());
            y3 = Double.parseDouble(t3y.getText());
            x4 = Double.parseDouble(t4x.getText());
            y4 = Double.parseDouble(t4y.getText());

            solution = 3;

           /*  Presečišče obstaja, če velja:
               T1+Ua(T2-T1) = T3+Ub(T4-T3)

            D = (T2-T1) x (T4-T3) = (x2 - x1)(y4 - y3) - (x4 - x3)(y2 - y1)
            A = (T4-T3) x (T1-T3) = (x4 - x3)(y1 - y3) - (x1 - x3)(y4 - y3)
            B = (T2-T1) x (T1-T3) = (x2 - x1)(y1 - y3) - (x1 - x3)(y2 - y1)*/

            d = (x2 - x1) * (y4 - y3) - (x4 - x3) * (y2 - y1);
            a = (x4 - x3) * (y1 - y3) - (x1 - x3) * (y4 - y3);
            b = (x2 - x1) * (y1 - y3) - (x1 - x3) * (y2 - y1);


            ua = a / d;
            ub = b / d;


        }
    };

    public double evklidskaRazdalja(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y1 - y2) * (y1 - y2) + (x1 - x2) * (x1 - x2));
    }

    public double manhattanovaRazdalja(double x1, double y1, double x2, double y2) {

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public double chebyshevaRazdalja(double x1, double y1, double x2, double y2) {

        return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
    }


    public static void main(String[] args) {

        new App().show();

    }

    public void show() {

        // create main frame
        JFrame frame = new JFrame("App");
        Container content = frame.getContentPane();
        // set layout on content pane
        content.setLayout(new BorderLayout());
        // create draw area
        // drawArea = new DrawArea();
        App app = new App();


        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(16, 1));
        JPanel buttons = new JPanel(new GridLayout(4, 1));


        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(clear);
        izracunajButton = new JButton("Izracunaj");
        izracunajButton.addActionListener(izracunaj);

        t1x = new JTextField(5);
        t2x = new JTextField(5);
        t3x = new JTextField(5);
        t4x = new JTextField(5);
        t1y = new JTextField(5);
        t2y = new JTextField(5);
        t3y = new JTextField(5);
        t4y = new JTextField(5);

        t1xLabel = new JLabel("T1(x)");
        t2xLabel = new JLabel("T2(x)");
        t3xLabel = new JLabel("T3(x)");
        t4xLabel = new JLabel("T4(x)");
        t1yLabel = new JLabel("T1(y)");
        t2yLabel = new JLabel("T2(y)");
        t3yLabel = new JLabel("T3(y)");
        t4yLabel = new JLabel("T4(y)");

        nacin = new JLabel();
        nacin.setText("Nacin");
        dveTockiRadioButton = new JRadioButton();
        dveTockiRadioButton.setText("Dve tocki ");
        dveTockiRadioButton.addActionListener(dveTocki);

        enaTockaEnaDaljicaRadioButton = new JRadioButton();
        enaTockaEnaDaljicaRadioButton.setText("Ena tocka ena daljica");
        enaTockaEnaDaljicaRadioButton.addActionListener(drugiNacin);


        dveDaljiciRadioButton = new JRadioButton();
        dveDaljiciRadioButton.setText("Dve daljici");
        dveDaljiciRadioButton.addActionListener(dveDaljici);

        ButtonGroup group = new ButtonGroup();
        group.add(dveTockiRadioButton);
        group.add(enaTockaEnaDaljicaRadioButton);
        group.add(dveDaljiciRadioButton);

        buttons.add(nacin);
        buttons.add(dveTockiRadioButton);
        buttons.add(enaTockaEnaDaljicaRadioButton);
        buttons.add(dveDaljiciRadioButton);


        controls.add(t1xLabel);
        controls.add(t1yLabel);
        controls.add(t1x);
        controls.add(t1y);

        controls.add(t2xLabel);
        controls.add(t2yLabel);
        controls.add(t2x);
        controls.add(t2y);

        controls.add(t3xLabel);
        controls.add(t3yLabel);
        controls.add(t3x);
        controls.add(t3y);

        controls.add(t4xLabel);
        controls.add(t4yLabel);
        controls.add(t4x);
        controls.add(t4y);
        controls.add(clearBtn);
        controls.add(izracunajButton);


        // add to content pane
        content.add(controls, BorderLayout.WEST);
        content.add(buttons, BorderLayout.NORTH);
        content.add(drawArea, BorderLayout.CENTER);

        JPanel main = app.panelMain;


        // add to content pane
        //content.add(main, BorderLayout.EAST);
        frame.setSize(800, 600);
        // can close frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // show
        frame.setVisible(true);

    }


}















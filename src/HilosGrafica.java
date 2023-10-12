import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HilosGrafica extends JFrame {
    static ArrayList<Integer> datosHombres = new ArrayList<>();
    static ArrayList<Integer> datosMujeres = new ArrayList<>();
    static JPanel panelGrafica;
    static public int cantidadHombres;
    static public int cantidadMujeres;


    public HilosGrafica() {
        cantidadMujeres=0;
        cantidadHombres=0;

        setTitle("Generador de Gráfica de Barras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        panelGrafica = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(cantidadHombres!=0 && cantidadMujeres!=0){
                    dibujarGrafica(g);
                }

            }
        };

        panelPrincipal.add(panelGrafica, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        JTextField campoCantidadHombres = new JTextField(10);
        JTextField campoCantidadMujeres = new JTextField(10);
        JButton btnGuardar = new JButton("Guardar Datos");
        JButton btnGraficar = new JButton("Graficar");

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cantidadHombres = Integer.parseInt(campoCantidadHombres.getText());
                    cantidadMujeres = Integer.parseInt(campoCantidadMujeres.getText());
                    datosHombres.add(cantidadHombres);
                    datosMujeres.add(cantidadMujeres);
                    campoCantidadHombres.setText("");
                    campoCantidadMujeres.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese números válidos.");
                }
            }
        });

        btnGraficar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelGrafica.repaint();
            }
        });

        JLabel lblHombres = new JLabel("Hombres: ");
        panelInferior.add(lblHombres);
        panelInferior.add(campoCantidadHombres);
        panelInferior.add(new JLabel("Mujeres: "));
        panelInferior.add(campoCantidadMujeres);
        panelInferior.add(btnGuardar);
        panelInferior.add(btnGraficar);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }


    static void dibujarGrafica(Graphics g) {

        int x = 50;
        int y = panelGrafica.getHeight() - 40;
        int anchoBarra = 40;
        int total = 0;
        int altoBarraHombres = 0;
        int altoBarraMujeres =0;
        double porcentajeHombres = 0;
        double porcentajeMujeres =0;

            total = cantidadHombres + cantidadMujeres;
            System.out.println(total);
            altoBarraHombres = (cantidadHombres * panelGrafica.getHeight()) / total;
            altoBarraMujeres = (cantidadMujeres * panelGrafica.getHeight()) / total;

            // Calcular porcentaje de hombres y mujeres
            porcentajeHombres = ((double) cantidadHombres / total) * 100.0;
            porcentajeMujeres = ((double) cantidadMujeres / total) * 100.0;


            // Dibujar las barras de hombres y mujeres
            g.setColor(Color.BLUE); // Color para hombres
            g.fillRect(x, y - altoBarraHombres, anchoBarra, altoBarraHombres);

            g.setColor(Color.PINK); // Color para mujeres
            g.fillRect(x + anchoBarra + 10, y - altoBarraMujeres, anchoBarra, altoBarraMujeres);


            // Imprimir porcentaje en la parte superior de las barras
            g.setColor(Color.BLACK);
            g.drawString(String.format("%.2f%%", porcentajeHombres), x, y - altoBarraHombres - 5);
            g.drawString(String.format("%.2f%%", porcentajeMujeres), x + anchoBarra + 10, y - altoBarraMujeres - 5);

            g.drawString(String.format("Hombres", porcentajeHombres), x, y - altoBarraHombres - 27);
            g.drawString(String.format("Mujeres", porcentajeMujeres), x + anchoBarra + 10, y - altoBarraMujeres - 25);

            x += anchoBarra * 2 + 20;


        }

    public static void main(String[] args) {
        HiloPintor h1 = new HiloPintor();
        h1.start();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HilosGrafica();
            }
        });
    }
}


package view;

import controller.CDivisas;
import controller.CTemperaturas;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame{

    public Menu() {
        initComponent();
    }
    
    private void initComponent() {
         try {
            URL url = getClass().getResource("/recursos/imgs/portada.png");
            i = ImageIO.read(new File(url.getPath()));
        } catch (IOException ex) {
             System.err.println(ex.getMessage());
        }
         
        mainPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(i, 0, 0, this);
            }
        };
        
        jbDivisas = new JButton("Divisas");
        jbDivisas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbDivisas.addActionListener((e)->{
            new CDivisas();
        });
        
        jbTempertaturas = new JButton("Temperaturas");
        jbTempertaturas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbTempertaturas.addActionListener((e)->{
            new CTemperaturas();
        });
        
        mainPanel.add(jbDivisas);
        
        mainPanel.add(jbTempertaturas);
                
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Selección de conversión");
        setSize(new Dimension(400, 250));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
        
    private JPanel mainPanel;
    private BufferedImage i = null;
    private JButton jbDivisas;
    private JButton jbTempertaturas;
}

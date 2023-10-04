package view;

import controller.CDivisas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.Timer;

public class Splash extends JWindow{
JLayeredPane layeredPane;
    public Splash() {
        jpBase = (JPanel) getContentPane();
        jpBase.setBackground(Color.BLACK);
        
        JLayeredPane layeredPane = new JDesktopPane();
        
        screem = Toolkit.getDefaultToolkit().getScreenSize();
        size = new Dimension(700, 400);
        int x = (screem.width-size.width)/2;
        int y = (screem.height-size.height)/2;
        setBounds(x,y,size.width, size.height);
        
        label = new JLabel();
        layeredPane.add(label, new Integer(0));
        
        alura = new JLabel();
        layeredPane.add(alura, new Integer(1));
                
        jpBase.add(layeredPane, BorderLayout.CENTER);
        setOpacity(0.0f);
        
        setVisible(true);
        label.setBounds(0,0,jpBase.getWidth(),jpBase.getHeight());
        alura.setSize(new Dimension(100, 100));
        showSplash(true);
    }
    
    public void showSplash(boolean full) {
        ic = new ImageIcon(getClass().getResource("/recursos/imgs/portada.png"));
        Icon i = new ImageIcon(ic.getImage().getScaledInstance(label.getWidth(), 
                label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(i);
        label.repaint();
        
        if(full){
            ImageIcon ico = new ImageIcon(getClass().getResource("/recursos/imgs/a.png"));
            Icon im = new ImageIcon(ico.getImage().getScaledInstance(alura.getWidth(), 
                    alura.getHeight(), Image.SCALE_DEFAULT));
            alura.setIcon(im);
            Open(12);
        }
    }
    
    private void Open(float opacidad){
        time = new Timer(110, (e)->{
            setOpacity(opacidadLess/opacidad);
            if (opacidadLess == opacidad) {
                try {
                    opacidadLess = 0.0f;
                    Thread.sleep(1000);
                    time.stop();
                    close();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else opacidadLess++;
            
        });
        time.start();
    }
            
    private void close(){
        time2 = new Timer(100, (e)->{
            angle+=0.1;
            AffineTransform af = new AffineTransform();
            af.rotate(angle, alura.getWidth()/2, alura.getHeight()/2);
            Graphics2D g2 = (Graphics2D) alura.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setTransform(af);
            g2.setColor(new Color(10, 100, 225));
            g2.fillOval(15,15,8,8);
            if(angle >= 6.399999999999994) suish=1;
            if(suish == 1) {
                for(int i = 0; i < 402; i+=8){
                    Dimension size = new Dimension(700-i, 400-i);
                    int x = (screem.width-size.width)/2;
                    int y = (screem.height-size.height)/2;
                    setBounds(x,y,size.width, size.height);
                    label.setBounds(0,0,700-i,400-i);
                    if(i < 400) showSplash(false);
                }
                suish=2;
                if(suish == 2 ){
                    time2.stop();
                    menu = new Menu();
                    dispose();
                }
            }
        });
        time2.start();
    }
    
    private CDivisas Precios_Divisas;
    private JPanel jpBase;
    private Dimension screem;
    private Dimension size;
    private Timer time;
    private JLabel alura;
    private JLabel label;
    private float opacidadLess = 0.0f;
    private double angle = 0.0;
    private byte suish = 0;
    private ImageIcon ic;
    private Timer time2 = null; 
    private Menu menu;
    //private 
}

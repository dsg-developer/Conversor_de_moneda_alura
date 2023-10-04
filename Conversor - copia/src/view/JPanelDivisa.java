/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Work-Game
 */
public class JPanelDivisa extends JPanel{
    public JPanelDivisa(Divisas frame) throws HeadlessException {
        initComponents(frame);
        frame.setLabelImg(s1JlPais, "/recursos/imgs/pais.png");
        frame.setLabelImg(s1JlMoneda, "/recursos/imgs/moneda.png");
        frame. setLabelImg(s2JlPais, "/recursos/imgs/pais.png");
        frame.setLabelImg(s2JlMoneda, "/recursos/imgs/moneda.png");
        frame.setLabelImg(s3JlPais, "/recursos/imgs/pais.png");
        frame.setLabelImg(s3JlMoneda, "/recursos/imgs/moneda.png");
        
        pop = new JPopupMenu(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                RoundRectangle2D r = new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),16,16);
                    g2.setColor(getBackground());
                    g2.setClip(r);
                    g2.fill(r);
            }
        };
        pop.setOpaque(false);
        //pop.setBackground(new Color(0,0,0,0));
        pop.setPreferredSize(new Dimension(170,341));
         
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 2;
        g.insets = new Insets(0, 0, 0, 12);
        
        jmiDolar.add(USA, g);
        
        pop.add(jmiDolar, g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 2;
        g.insets = new Insets(0, 0, 12, 0);
        
        jmiEuropa.add(europa, g);
        
        pop.add(jmiEuropa);
                
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 2;
        g.insets = new Insets(0, 0, 0, 12);
        
        jmiLondres.add(londres, g);
        
        pop.add(jmiLondres);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 2;
        g.insets = new Insets(0, 0, 0, 12);
        
        jmiRD.add(RD, g);
        
        pop.add(jmiRD);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 2;
        g.insets = new Insets(0, 0, 0,12);
        
        jmiJapon.add(japon, g);
        
        pop.add(jmiJapon);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 2;
        g.insets = new Insets(0, 0, 0, 12);
        
        jmiSurCorea.add(surCorea, g);
        
        pop.add(jmiSurCorea, g);
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents(Divisas frame) {
        jpPrincipal = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(getWidth()/2-320,getHeight()/2-170,new Color(96,205,247), 
                        getWidth(),getHeight()/2,new Color(88,107,215),false);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        BoxLayout bl = new BoxLayout(jpPrincipal, BoxLayout.Y_AXIS);
        jpPrincipal.setLayout(bl);
        
        GridBagConstraints g;
        
        //Seccion para digitar el monto que se desea convertir
        jpPrincipal.add(Box.createRigidArea(new Dimension(100,20)));
        
        jpMonto = new JPanel(new GridBagLayout()){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                RoundRectangle2D r = new RoundRectangle2D.Double(0,0,getWidth()-1,getHeight()-1,16,16);
                Container c = getParent();
                if(c != null){
                    g2.setColor(getBackground());
                    g2.fill(r);
                }
                g2.draw(r);
            }
        };
        jpMonto.setOpaque(false);
        jpMonto.setBackground(new Color(24,69,110));
        jpMonto.setMaximumSize(new Dimension(300, 132));
        
        //label que tiene la img del pais
        s1JlPais = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        s1JlPais.setMinimumSize(new Dimension(52,52));
        s1JlPais.setPreferredSize(new Dimension(52,52));
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 12);
        
        jpMonto.add(s1JlPais, g);
        
        //label que tiene la img de la moneda
        s1JlMoneda = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        s1JlMoneda.setMinimumSize(new Dimension(52,52));
        s1JlMoneda.setPreferredSize(new Dimension(52,52));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 0;
        g.gridwidth = 1;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 12);
        
        jpMonto.add(s1JlMoneda, g);
        
        //textfield para digitar el monto
        s1JtfMonto = new JTextField("Digita tu monto");
        s1JtfMonto.setBackground(new Color(24,69,110));
        s1JtfMonto.setForeground(Color.WHITE);
        s1JtfMonto.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        s1JtfMonto.setMinimumSize(new Dimension(150, 36));
        s1JtfMonto.setPreferredSize(new Dimension(150, 36));
        s1JtfMonto.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        s1JtfMonto.setCaretColor(Color.WHITE);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 0);
        
        jpMonto.add(s1JtfMonto, g);
        
        //label para seleccionar el pais.
        s1JlSelect = new JLabel("SELECCIONAR IDIOMA");
        s1JlSelect.setForeground(Color.WHITE);
        s1JlSelect.setHorizontalAlignment(SwingConstants.CENTER);
        s1JlSelect.setFont(new Font("Times New Roman", Font.BOLD, 12));
        s1JlSelect.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent me){mouseEncima(me, s1JlSelect);}
            
            @Override
            public void mouseExited(MouseEvent me){mouseFuera(me, s1JlSelect);}
            
            @Override
            public void mouseClicked(MouseEvent me){mouseClick(me, frame);}
        });
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 3;
        g.gridy = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(-15, 0, 0, 0);
        
        jpMonto.add(s1JlSelect, g);

        //label que indica el nombre de la moneda.
        s1JlNombreMoneda = new JLabel("None / None");
        s1JlNombreMoneda.setForeground(Color.WHITE);
        s1JlNombreMoneda.setHorizontalAlignment(SwingConstants.CENTER);
        s1JlNombreMoneda.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
        g = new GridBagConstraints();
        g.gridx = 4;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH;
        
        jpMonto.add(s1JlNombreMoneda, g);
        
        //label que indica lo que vale la moneda que se esta convitiendo.
        s1JlEquivalente = new JLabel("None / None");
        s1JlEquivalente.setForeground(Color.WHITE);
        s1JlEquivalente.setHorizontalAlignment(SwingConstants.CENTER);
        s1JlEquivalente.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
        g = new GridBagConstraints();
        g.gridx = 4;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH;
        
        jpMonto.add(s1JlEquivalente, g);
        
        jpPrincipal.add(jpMonto);
        
        //Seccion para saber a que moneda se esta convirtiendo el monto digitado
        jpPrincipal.add(Box.createRigidArea(new Dimension(100,20)));
        
        jpMoneda = new JPanel(new GridBagLayout()){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                RoundRectangle2D r = new RoundRectangle2D.Double(0,0,getWidth()-1,getHeight()-1,16,16);
                Container c = getParent();
                if(c != null){
                    g2.setColor(getBackground());
                    g2.fill(r);
                }
                g2.draw(r);
            }
        };
        jpMoneda.setOpaque(false);
        jpMoneda.setBackground(new Color(24,69,110));
        jpMoneda.setMaximumSize(new Dimension(300, 132));
        
        //label que tiene la img del pais
        s2JlPais = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        s2JlPais.setPreferredSize(new Dimension(52,52));
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 12);
        
        jpMoneda.add(s2JlPais, g);
        
        //label que tiene la img de la moneda
        s2JlMoneda = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        s2JlMoneda.setPreferredSize(new Dimension(52,52));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 0;
        g.gridwidth = 1;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 12);
        
        jpMoneda.add(s2JlMoneda, g);
        
        //textfield para digitar el monto
        s2JtfMonto = new JTextField("Valor de la moneda");
        s2JtfMonto.setBackground(new Color(24,69,110));
        s2JtfMonto.setForeground(Color.WHITE);
        s2JtfMonto.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        s2JtfMonto.setPreferredSize(new Dimension(150, 36));
        s2JtfMonto.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        s2JtfMonto.setCaretColor(Color.WHITE);
        s2JtfMonto.setHorizontalAlignment(SwingConstants.CENTER);
        s2JtfMonto.setEditable(false);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 0);
        
        jpMoneda.add(s2JtfMonto, g);
        
        //label para seleccionar el pais.
        s2JlSelect = new JLabel("SELECCIONAR IDIOMA");
        s2JlSelect.setForeground(Color.WHITE);
        s2JlSelect.setHorizontalAlignment(SwingConstants.CENTER);
        s2JlSelect.setFont(new Font("Times New Roman", Font.BOLD, 12));
        s2JlSelect.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent me){mouseEncima(me, s2JlSelect);}
            
            @Override
            public void mouseExited(MouseEvent me){mouseFuera(me, s2JlSelect);}
            
            @Override
            public void mouseClicked(MouseEvent me){s2JlmouseClick(me, frame);}
        });
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 3;
        g.gridy = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(-15, 0, 0, 0);
        
        jpMoneda.add(s2JlSelect, g);
        
        //label que indica el nombre de la moneda.
        s2JlNombreMoneda = new JLabel("None / None");
        s2JlNombreMoneda.setForeground(Color.WHITE);
        s2JlNombreMoneda.setHorizontalAlignment(SwingConstants.CENTER);
        s2JlNombreMoneda.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
        g = new GridBagConstraints();
        g.gridx = 4;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH;
        
        jpMoneda.add(s2JlNombreMoneda, g);
        
        //label que que indica lo que vale la moneda que se esta convitiendo.
        s2JlEquivalente = new JLabel("None / None");
        s2JlEquivalente.setForeground(Color.WHITE);
        s2JlEquivalente.setHorizontalAlignment(SwingConstants.CENTER);
        s2JlEquivalente.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
        g = new GridBagConstraints();
        g.gridx = 4;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH;
        
        jpMoneda.add(s2JlEquivalente, g);
        
        jpPrincipal.add(jpMoneda);
        
        //Seccion para ver el valor de la conversion
        jpPrincipal.add(Box.createRigidArea(new Dimension(100,20)));
        
        jpResultado = new JPanel(new GridBagLayout()){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                RoundRectangle2D r = new RoundRectangle2D.Double(0,0,getWidth()-1,getHeight()-1,16,16);
                Container c = getParent();
                if(c != null){
                    g2.setColor(getBackground());
                    g2.fill(r);
                }
                g2.draw(r);
            }
        };
        jpResultado.setOpaque(false);
        jpResultado.setBackground(new Color(24,69,110));
        jpResultado.setMaximumSize(new Dimension(300, 132));
        
        //lavel que tiene la img del pais
        s3JlPais = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        s3JlPais.setPreferredSize(new Dimension(52,52));
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 12);
        
        jpResultado.add(s3JlPais, g);
        
        //boton de intercambio de conversion
        s3JBIntercambio = new JButton("Intercambiar");
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 0;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 12);
        
        jpResultado.add(s3JBIntercambio, g);
        
        //label que tiene la img de la moneda
        s3JlMoneda = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        s3JlMoneda.setPreferredSize(new Dimension(52,52));
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 1;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 12);
        
        jpResultado.add(s3JlMoneda, g);
        
        jpPrincipal.add(jpResultado);
        
        //seleccion de pais
        jmiEuropa = new JMenuItem();
        jmiEuropa.setBackground(Color.WHITE);
        jmiEuropa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jmiEuropa.setLayout(new GridBagLayout());
                
        JLabel jlEuropa = new JLabel("Euro");
        jlEuropa.setBackground(Color.WHITE);
        jlEuropa.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 0;
        g.insets = new Insets(-8, 0, 0, 0);
        
        jmiEuropa.add(jlEuropa, g);
                        
        jmiLondres = new JMenuItem();
        jmiLondres.setBackground(Color.WHITE);
        jmiLondres.setLayout(new GridBagLayout());
        jmiLondres.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel jlLondres = new JLabel("GBP");
        jlLondres.setBackground(Color.WHITE);
        jlLondres.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        
        jmiLondres.add(jlLondres, g);
        
        jmiJapon = new JMenuItem();
        jmiJapon.setBackground(Color.WHITE);
        jmiJapon.setLayout(new GridBagLayout());
        jmiJapon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel jlJapon = new JLabel("Yen");
        jlJapon.setBackground(Color.WHITE);
        jlJapon.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        
        jmiJapon.add(jlJapon, g);
        
        jmiRD = new JMenuItem();
        jmiRD.setBackground(Color.WHITE);
        jmiRD.setLayout(new GridBagLayout());
        jmiRD.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel jlRD = new JLabel("Peso");
        jlRD.setBackground(Color.WHITE);
        jlRD.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        jmiRD.add(jlRD, g);
        
        jmiSurCorea = new JMenuItem();
        jmiSurCorea.setBackground(Color.WHITE);
        jmiSurCorea.setLayout(new GridBagLayout());
        jmiSurCorea.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel jlSurCorea = new JLabel("Won");
        jlSurCorea.setBackground(Color.WHITE);
        jlSurCorea.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        
        jmiSurCorea.add(jlSurCorea, g);
        
        jmiDolar = new JMenuItem();
        jmiDolar.setBackground(Color.WHITE);
        jmiDolar.setLayout(new GridBagLayout());
        jmiDolar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel jlUSA = new JLabel("Dolar");
        jlUSA.setBackground(Color.WHITE);
        jlUSA.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.insets = new Insets(0, 0, 0, 0);
        
        jmiDolar.add(jlUSA, g);
        
        europa = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        europa.setPreferredSize(new Dimension(58,40));
        
        //europa.setOpaque(false);
        
        londres = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        londres.setPreferredSize(new Dimension(38,38));
       
        japon = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        japon.setPreferredSize(new Dimension(56,38));
        
        RD = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        RD.setPreferredSize(new Dimension(42,42));
        
        surCorea = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        surCorea.setPreferredSize(new Dimension(38,38));
        
        USA = new JLabel(){
            @Override
            public void paintBorder(Graphics g){
                super.paintBorder(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Ellipse2D r = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);
                Container c = getParent();
                if(c != null){
                    g2.setColor(c.getBackground());
                    Area a = new Area(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
                    a.subtract(new Area(r));
                    g2.fill(a);
                }
                g2.draw(r);
            }
        };
        USA.setPreferredSize(new Dimension(38,38));
        
        panel1 = false;
    }
    
    private void initLabels(Divisas frame){
        frame.setLabelImg(europa, "/recursos/imgs/europe.png");
        frame.setLabelImg(londres, "/recursos/imgs/londres.png");
        frame.setLabelImg(japon, "/recursos/imgs/japon.png");
        frame.setLabelImg(RD, "/recursos/imgs/RD.png");
        frame.setLabelImg(surCorea, "/recursos/imgs/sulcorea.png");
        frame.setLabelImg(USA, "/recursos/imgs/usa.png");
    }
    
    private void mouseClick(MouseEvent me, Divisas frame){
        panel1 = true;
        pop.show(jpPrincipal, jpPrincipal.getWidth()-180, jpPrincipal.getHeight()-346);
        initLabels(frame);
    }
    
    private void s2JlmouseClick(MouseEvent me, Divisas frame){
        panel1 = false;
        pop.show(jpPrincipal, jpPrincipal.getWidth()-180, jpPrincipal.getHeight()-346);
        initLabels(frame);
    }
    
    private void mouseEncima(MouseEvent me, JLabel label){
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font f = label.getFont();
        Map<TextAttribute, Object> at = new HashMap<>(f.getAttributes());
        at.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(f.deriveFont(at));
    }
    
    private void mouseFuera(MouseEvent me, JLabel label){
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font f = label.getFont();
        Map<TextAttribute, Object> at = new HashMap<>(f.getAttributes());
        at.put(TextAttribute.UNDERLINE, -1);
        label.setFont(f.deriveFont(at));
    }
        
    public JMenuItem getJmiEuropa() {return jmiEuropa;}

    public JMenuItem getJmiLondres() {return jmiLondres;}

    public JMenuItem getJmiJapon() {return jmiJapon;}

    public JMenuItem getJmiRD() {return jmiRD;}

    public JMenuItem getJmiSurCorea() {return jmiSurCorea;}

    public JMenuItem getJmiDolar() {return jmiDolar;}

    public JLabel getS1JlPais() {return s1JlPais;}

    public JLabel getS1JlMoneda() {return s1JlMoneda;}

    public JLabel getS1JlNombreMoneda() {return s1JlNombreMoneda;}

    public JLabel getS1JlEquivalente() {return s1JlEquivalente;}

    public JLabel getS2JlPais() {return s2JlPais;}

    public JLabel getS2JlMoneda() {return s2JlMoneda;}

    public JLabel getS2JlNombreMoneda() {return s2JlNombreMoneda;}

    public JLabel getS2JlEquivalente() {return s2JlEquivalente;}

    public boolean isPanel1() {return panel1;}
    
    private JPanel jpPrincipal;
    private JPanel jpMonto;
    private JPanel jpMoneda;
    private JPanel jpResultado;
    private JLabel s1JlPais;
    private JLabel s1JlMoneda;
    private JLabel s1JlNombreMoneda;
    private JLabel s1JlSelect;
    private JLabel s1JlEquivalente;
    private JTextField s1JtfMonto;
    private JLabel s2JlPais;
    private JLabel s2JlMoneda;
    private JLabel s2JlNombreMoneda ;
    private JLabel s2JlSelect;
    private JLabel s2JlEquivalente;
    private JTextField s2JtfMonto;
    private JLabel s3JlPais;
    private JButton s3JBIntercambio;
    private JLabel s3JlMoneda;
    private JLabel s3JlNombreMoneda ;
    private JLabel s3JlEquivalente;
    private JTextField s3JtfMonto;
    private JLabel europa;
    private JLabel japon;
    private JLabel londres;
    private JLabel RD;
    private JLabel surCorea;
    private JLabel USA;
    private JPopupMenu pop;
    private GridBagConstraints g;
    private JMenuItem jmiEuropa;
    private JMenuItem jmiLondres;
    private JMenuItem jmiJapon;
    private JMenuItem jmiRD;
    private JMenuItem jmiSurCorea;
    private JMenuItem jmiDolar;
    private Timer animacion;
    private boolean panel1;
}

package view;

import java.awt.BorderLayout;
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
import java.awt.Image;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Divisas extends JFrame{

    public Divisas() throws HeadlessException {
        initComponents();
        setLabelImg(s1JlPais, "/recursos/imgs/pais.png");
        setLabelImg(s1JlMoneda, "/recursos/imgs/moneda.png");
        setLabelImg(s2JlPais, "/recursos/imgs/pais.png");
        setLabelImg(s2JlMoneda, "/recursos/imgs/moneda.png");
        setLabelImg(s3JlPais, "/recursos/imgs/pais.png");
        setLabelImg(s3JlMoneda, "/recursos/imgs/moneda.png");
        setLabelImg(s3JlNombreMoneda, "/recursos/imgs/moneda.png");
        ImageIcon i = new ImageIcon(getClass().getResource("/recursos/imgs/intercambio.png"));
        Icon ic = new ImageIcon(i.getImage().getScaledInstance(s3JBIntercambio.getWidth(),
                s3JBIntercambio.getHeight(), Image.SCALE_SMOOTH));
        s3JBIntercambio.setIcon(ic);
        
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
        
        JOptionPane.showMessageDialog(null, "Esta app, fue creada con fines educativos, las divisas calculadas aquí fueron\n"
                                                       + "puesta manualmente el día 02-08-2023, por lo que no se puede usar como referencia\n"
                                                       + "actual la conversion realizada en esta app, los datos fueron tomados de la pagina:\n"
                                                       + "https://www.xe.com/es/currencyconverter/convert/?Amount=1&From=KRW&To=JPY",
                                                    "Información importante", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
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
        int labelSize = 42;
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
        s1JlPais.setMinimumSize(new Dimension(labelSize,labelSize));
        s1JlPais.setPreferredSize(new Dimension(labelSize,labelSize));
        
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
        s1JlMoneda.setMinimumSize(new Dimension(labelSize,labelSize));
        s1JlMoneda.setPreferredSize(new Dimension(labelSize,labelSize));
        
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
        s1JtfMonto.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        s1JtfMonto.setMinimumSize(new Dimension(148, 36));
        s1JtfMonto.setPreferredSize(new Dimension(148, 36));
        s1JtfMonto.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        s1JtfMonto.setCaretColor(Color.WHITE);
        s1JtfMonto.setHorizontalAlignment(SwingConstants.CENTER);
        s1JtfMonto.setEditable(false);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 0);
        
        jpMonto.add(s1JtfMonto, g);
        
        //label para seleccionar el pais.
        s1JlSelect = new JLabel("SELECCIONAR MONEDA");
        s1JlSelect.setForeground(Color.WHITE);
        s1JlSelect.setHorizontalAlignment(SwingConstants.CENTER);
        s1JlSelect.setFont(new Font("Times New Roman", Font.BOLD, 9));
        s1JlSelect.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent me){mouseEncima(me, s1JlSelect);}
            
            @Override
            public void mouseExited(MouseEvent me){mouseFuera(me, s1JlSelect);}
            
            @Override
            public void mouseClicked(MouseEvent me){mouseClick(me);}
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
        s1JlNombreMoneda.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
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
        s1JlEquivalente.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
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
        s2JlPais.setPreferredSize(new Dimension(labelSize,labelSize));
        
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
        s2JlMoneda.setPreferredSize(new Dimension(labelSize,labelSize));
        
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
        s2JtfMonto.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        s2JtfMonto.setPreferredSize(new Dimension(148, 36));
        s2JtfMonto.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        s2JtfMonto.setCaretColor(Color.WHITE);
        s2JtfMonto.setHorizontalAlignment(SwingConstants.CENTER);
        s2JtfMonto.setEditable(false);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 3;
        
        jpMoneda.add(s2JtfMonto, g);
        
        //label para seleccionar el pais.
        s2JlSelect = new JLabel("SELECCIONAR MONEDA");
        s2JlSelect.setForeground(Color.WHITE);
        s2JlSelect.setHorizontalAlignment(SwingConstants.CENTER);
        s2JlSelect.setFont(new Font("Times New Roman", Font.BOLD, 9));
        s2JlSelect.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent me){mouseEncima(me, s2JlSelect);}
            
            @Override
            public void mouseExited(MouseEvent me){mouseFuera(me, s2JlSelect);}
            
            @Override
            public void mouseClicked(MouseEvent me){s2JlmouseClick(me);}
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
        s2JlNombreMoneda.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 4;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH;
        
        jpMoneda.add(s2JlNombreMoneda, g);
        
        //label que indica lo que vale la moneda que se esta convitiendo.
        s2JlEquivalente = new JLabel("None / None");
        s2JlEquivalente.setForeground(Color.WHITE);
        s2JlEquivalente.setHorizontalAlignment(SwingConstants.CENTER);
        s2JlEquivalente.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
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
        s3JlPais.setPreferredSize(new Dimension(labelSize,labelSize));
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 12);
        
        jpResultado.add(s3JlPais, g);
        
        //boton de intercambio de conversion
        s3JBIntercambio = new JButton();
        s3JBIntercambio.setEnabled(false);
        s3JBIntercambio.setOpaque(false);
        s3JBIntercambio.setPreferredSize(new Dimension(56, 30));
        s3JBIntercambio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
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
        s3JlMoneda.setPreferredSize(new Dimension(labelSize,labelSize));
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 1;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 12);
        
        jpResultado.add(s3JlMoneda, g);
        
        //Label que muestra el signo de la moneda
        s3JlNombreMoneda = new JLabel(){
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
        s3JlNombreMoneda.setHorizontalAlignment(SwingConstants.CENTER);
        s3JlNombreMoneda.setPreferredSize(new Dimension(labelSize,labelSize));
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 2;
        
        jpResultado.add(s3JlNombreMoneda, g);
        
        //Label que muestra la divisa
        s3JlEquivalente = new JLabel("0.0");
        s3JlEquivalente.setForeground(Color.WHITE);
        s3JlEquivalente.setHorizontalAlignment(SwingConstants.CENTER);
        s3JlEquivalente.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 2;
        g.gridwidth = 3;
        
        jpResultado.add(s3JlEquivalente, g);
                
        jpPrincipal.add(jpResultado);
        
        //seleccion de pais
        jmiEuropa = new JMenuItem();
        jmiEuropa.setBackground(Color.WHITE);
        jmiEuropa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jmiEuropa.setLayout(new GridBagLayout());
                
        JLabel jlEuropa = new JLabel("Euro");
        jlEuropa.setBackground(Color.WHITE);
        jlEuropa.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
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
        jlLondres.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        
        jmiLondres.add(jlLondres, g);
        
        jmiJapon = new JMenuItem();
        jmiJapon.setBackground(Color.WHITE);
        jmiJapon.setLayout(new GridBagLayout());
        jmiJapon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel jlJapon = new JLabel("Yen");
        jlJapon.setBackground(Color.WHITE);
        jlJapon.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        
        jmiJapon.add(jlJapon, g);
        
        jmiRD = new JMenuItem();
        jmiRD.setBackground(Color.WHITE);
        jmiRD.setLayout(new GridBagLayout());
        jmiRD.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel jlRD = new JLabel("Peso");
        jlRD.setBackground(Color.WHITE);
        jlRD.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        jmiRD.add(jlRD, g);
        
        jmiSurCorea = new JMenuItem();
        jmiSurCorea.setBackground(Color.WHITE);
        jmiSurCorea.setLayout(new GridBagLayout());
        jmiSurCorea.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel jlSurCorea = new JLabel("Won");
        jlSurCorea.setBackground(Color.WHITE);
        jlSurCorea.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        
        jmiSurCorea.add(jlSurCorea, g);
        
        jmiDolar = new JMenuItem();
        jmiDolar.setBackground(Color.WHITE);
        jmiDolar.setLayout(new GridBagLayout());
        jmiDolar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel jlUSA = new JLabel("Dolar");
        jlUSA.setBackground(Color.WHITE);
        jlUSA.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
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
        europa.setPreferredSize(new Dimension(48,30));
        
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
        londres.setPreferredSize(new Dimension(28,28));
       
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
        japon.setPreferredSize(new Dimension(46,28));
        
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
        RD.setPreferredSize(new Dimension(32,32));
        
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
        surCorea.setPreferredSize(new Dimension(28,28));
        
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
        USA.setPreferredSize(new Dimension(28,28));
        
        panel1 = false;
        setSize(new Dimension(400, 500));
        setLocationRelativeTo(null);
        getContentPane().add(jpPrincipal, BorderLayout.CENTER);
        setTitle("Conversor de Modenedas");
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
         
    private void initLabels(){
        setLabelImg(europa, "/recursos/imgs/europe.png");
        setLabelImg(londres, "/recursos/imgs/londres.png");
        setLabelImg(japon, "/recursos/imgs/japon.png");
        setLabelImg(RD, "/recursos/imgs/RD.png");
        setLabelImg(surCorea, "/recursos/imgs/sulcorea.png");
        setLabelImg(USA, "/recursos/imgs/usa.png");
    }
    
    private void mouseClick(MouseEvent me){
        panel1 = true;
        pop.show(jpPrincipal, jpPrincipal.getWidth()-180, jpPrincipal.getHeight()-346);
        initLabels();
    }
    
    private void s2JlmouseClick(MouseEvent me){
        panel1 = false;
        pop.show(jpPrincipal, jpPrincipal.getWidth()-180, jpPrincipal.getHeight()-346);
        initLabels();
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
    
    public JLabel setLabelImg(JLabel label, String path){
        ImageIcon ic = new ImageIcon(getClass().getResource(path));
        Icon i = new ImageIcon(ic.getImage().getScaledInstance(label.getWidth(), 
                label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(i);
        label.repaint();
        return label;
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

    public JTextField getS2JtfMonto() {return s2JtfMonto;}

    public JTextField getS1JtfMonto() {return s1JtfMonto;}

    public JLabel getS3JlPais() {return s3JlPais;}

    public JLabel getS3JlMoneda() {return s3JlMoneda;}

    public JButton getS3JBIntercambio() {return s3JBIntercambio;}

    public JLabel getS3JlNombreMoneda() {return s3JlNombreMoneda;}

    public JLabel getS3JlEquivalente() {return s3JlEquivalente;}

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

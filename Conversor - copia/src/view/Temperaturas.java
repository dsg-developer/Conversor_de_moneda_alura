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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Temperaturas extends JFrame{

    public Temperaturas() throws HeadlessException {
        initComponents();
        setLabelImg(s1JlGrado, "/recursos/imgs/temps.png");
        setLabelImg(s2JlGrado, "/recursos/imgs/temps.png");
        setLabelImg(s3JlGrado, "/recursos/imgs/temps.png");
        setLabelImg(s3JlMoneda, "/recursos/imgs/temps.png");
        setLabelImg(s3JlNombreMoneda, "/recursos/imgs/temps.png");
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
        pop.setPreferredSize(new Dimension(148,192));
                
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 2;
        g.insets = new Insets(0, 0, 12, 0);
        
        jmiCelcios.add(celcio, g);
        
        pop.add(jmiCelcios);
                
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 2;
        g.insets = new Insets(0, 0, 0, 12);
        
        jmiFarenheit.add(fahrenheit, g);
        
        pop.add(jmiFarenheit);
              
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 2;
        g.insets = new Insets(0, 0, 0,12);
        
        jmiKelvin.add(kelvin, g);
        
        pop.add(jmiKelvin);
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
        //Seccion para digitar el grado que se desea convertir
        jpPrincipal.add(Box.createRigidArea(new Dimension(100,20)));
        
        jpCantidad = new JPanel(new GridBagLayout()){
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
        jpCantidad.setOpaque(false);
        jpCantidad.setBackground(new Color(24,69,110));
        jpCantidad.setMaximumSize(new Dimension(300, 132));
        
        //label que tiene la img del Grado de temperatura
        s1JlGrado = new JLabel(){
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
        s1JlGrado.setMinimumSize(new Dimension(labelSize,labelSize));
        s1JlGrado.setPreferredSize(new Dimension(labelSize,labelSize));
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.insets = new Insets(0, 20, 0, 0);
        
        jpCantidad.add(s1JlGrado, g);
                
        //textfield para digitar el monto
        s1JtfGrado = new JTextField("Digita tu valor");
        s1JtfGrado.setBackground(new Color(24,69,110));
        s1JtfGrado.setForeground(Color.WHITE);
        s1JtfGrado.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        s1JtfGrado.setMinimumSize(new Dimension(148, 36));
        s1JtfGrado.setPreferredSize(new Dimension(148, 36));
        s1JtfGrado.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        s1JtfGrado.setCaretColor(Color.WHITE);
        s1JtfGrado.setHorizontalAlignment(SwingConstants.CENTER);
        s1JtfGrado.setEditable(false);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, -8, 0, 0);
        
        jpCantidad.add(s1JtfGrado, g);
        
        //label para seleccionar el grado.
        s1JlSelect = new JLabel("SELECCIONAR TEMP");
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
        
        jpCantidad.add(s1JlSelect, g);

        //label que indica el nombre del grado.
        s1JlNombreTemp = new JLabel("None / None");
        s1JlNombreTemp.setForeground(Color.WHITE);
        s1JlNombreTemp.setHorizontalAlignment(SwingConstants.CENTER);
        s1JlNombreTemp.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 4;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH;
        
        jpCantidad.add(s1JlNombreTemp, g);
        
        //label que indica lo que vale el grado que se esta convitiendo.
        s1JlEquivalente = new JLabel("None / None");
        s1JlEquivalente.setForeground(Color.WHITE);
        s1JlEquivalente.setHorizontalAlignment(SwingConstants.CENTER);
        s1JlEquivalente.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 4;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH;
        
        jpCantidad.add(s1JlEquivalente, g);
        
        jpPrincipal.add(jpCantidad);
        
        //Seccion para saber a que grado se esta convirtiendo la cantidad digitada
        jpPrincipal.add(Box.createRigidArea(new Dimension(100,20)));
        
        jpGrado = new JPanel(new GridBagLayout()){
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
        jpGrado.setOpaque(false);
        jpGrado.setBackground(new Color(24,69,110));
        jpGrado.setMaximumSize(new Dimension(300, 132));
        
        //label que tiene la img del grado
        s2JlGrado = new JLabel(){
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
        s2JlGrado.setPreferredSize(new Dimension(labelSize,labelSize));
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 20, 0, 0);
        
        jpGrado.add(s2JlGrado, g);
        
        //textfield para digitar la cantidad
        s2JtfGrado = new JTextField("Valor del grado");
        s2JtfGrado.setBackground(new Color(24,69,110));
        s2JtfGrado.setForeground(Color.WHITE);
        s2JtfGrado.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        s2JtfGrado.setPreferredSize(new Dimension(148, 36));
        s2JtfGrado.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        s2JtfGrado.setCaretColor(Color.WHITE);
        s2JtfGrado.setHorizontalAlignment(SwingConstants.CENTER);
        s2JtfGrado.setEditable(false);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 3;
        g.insets = new Insets(0, -8, 0, 0);
        
        jpGrado.add(s2JtfGrado, g);
        
        //label para seleccionar el grado.
        s2JlSelect = new JLabel("SELECCIONAR TEMP");
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
        
        jpGrado.add(s2JlSelect, g);
        
        //label que indica el nombre del grado.
        s2JlNombreMoneda = new JLabel("None / None");
        s2JlNombreMoneda.setForeground(Color.WHITE);
        s2JlNombreMoneda.setHorizontalAlignment(SwingConstants.CENTER);
        s2JlNombreMoneda.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 4;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH;
        
        jpGrado.add(s2JlNombreMoneda, g);
        
        //label que indica lo que vale el grado que se esta convitiendo.
        s2JlEquivalente = new JLabel("None / None");
        s2JlEquivalente.setForeground(Color.WHITE);
        s2JlEquivalente.setHorizontalAlignment(SwingConstants.CENTER);
        s2JlEquivalente.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 4;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH;
        
        jpGrado.add(s2JlEquivalente, g);
        
        jpPrincipal.add(jpGrado);
        
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
        
        //label que tiene la img del grado
        s3JlGrado = new JLabel(){
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
        s3JlGrado.setPreferredSize(new Dimension(labelSize,labelSize));
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.LINE_START;
        g.insets = new Insets(0, 0, 0, 12);
        
        jpResultado.add(s3JlGrado, g);
        
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
        
        //label que tiene la img del grado al que se va a convertir
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
        
        //Label que muestra el grado al que se esta convertiendo
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
        
        //Label que muestra el grado
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
        
        //seleccion de grado
        jmiCelcios = new JMenuItem();
        jmiCelcios.setBackground(Color.WHITE);
        jmiCelcios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jmiCelcios.setLayout(new GridBagLayout());
                
        JLabel jlCelcio = new JLabel("Celsius");
        jlCelcio.setBackground(Color.WHITE);
        jlCelcio.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 0;
        g.insets = new Insets(-8, 0, 0, 0);
        
        jmiCelcios.add(jlCelcio, g);
                        
        jmiFarenheit = new JMenuItem();
        jmiFarenheit.setBackground(Color.WHITE);
        jmiFarenheit.setLayout(new GridBagLayout());
        jmiFarenheit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel jlFarenheit = new JLabel("Fahrenheit");
        jlFarenheit.setBackground(Color.WHITE);
        jlFarenheit.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        
        jmiFarenheit.add(jlFarenheit, g);
        
        jmiKelvin = new JMenuItem();
        jmiKelvin.setBackground(Color.WHITE);
        jmiKelvin.setLayout(new GridBagLayout());
        jmiKelvin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel jlKelvin = new JLabel("Kelvin");
        jlKelvin.setBackground(Color.WHITE);
        jlKelvin.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        g = new GridBagConstraints();
        g.gridx = 2;
        
        jmiKelvin.add(jlKelvin, g);
                
        celcio = new JLabel(){
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
        celcio.setPreferredSize(new Dimension(36,36));
        
        fahrenheit = new JLabel(){
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
        fahrenheit.setPreferredSize(new Dimension(36,36));
       
        kelvin = new JLabel(){
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
        kelvin.setPreferredSize(new Dimension(36,36));
                
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
        setLabelImg(celcio, "/recursos/imgs/celcio.png");
        setLabelImg(fahrenheit, "/recursos/imgs/fahrenheit.png");
        setLabelImg(kelvin, "/recursos/imgs/kelvin.png");
    }
    
    private void mouseClick(MouseEvent me){
        panel1 = true;
        pop.show(jpPrincipal, jpPrincipal.getWidth()-160, jpPrincipal.getHeight()-200);
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
    
    public JMenuItem getJmiCelcio() {return jmiCelcios;}

    public JMenuItem getJmiFarenheit() {return jmiFarenheit;}

    public JMenuItem getJmiKelvin() {return jmiKelvin;}

    public JLabel getS1JlGrado() {return s1JlGrado;}

    public JLabel getS1JlNombreMoneda() {return s1JlNombreTemp;}

    public JLabel getS1JlEquivalente() {return s1JlEquivalente;}

    public JLabel getS2JlGrado() {return s2JlGrado;}

    public JLabel getS2JlNombreMoneda() {return s2JlNombreMoneda;}

    public JLabel getS2JlEquivalente() {return s2JlEquivalente;}

    public JTextField getS2JtfMonto() {return s2JtfGrado;}

    public JTextField getS1JtfGrado() {return s1JtfGrado;}

    public JLabel getS3JlGrado() {return s3JlGrado;}

    public JLabel getS3JlMoneda() {return s3JlMoneda;}

    public JButton getS3JBIntercambio() {return s3JBIntercambio;}

    public JLabel getS3JlNombreMoneda() {return s3JlNombreMoneda;}

    public JLabel getS3JlEquivalente() {return s3JlEquivalente;}
        
    public boolean isPanel1() {return panel1;}
    
    private JPanel jpPrincipal;
    private JPanel jpCantidad;
    private JPanel jpGrado;
    private JPanel jpResultado;
    private JLabel s1JlGrado;
    private JLabel s1JlNombreTemp;
    private JLabel s1JlSelect;
    private JLabel s1JlEquivalente;
    private JTextField s1JtfGrado;
    private JLabel s2JlGrado;
    private JLabel s2JlNombreMoneda ;
    private JLabel s2JlSelect;
    private JLabel s2JlEquivalente;
    private JTextField s2JtfGrado;
    private JLabel s3JlGrado;
    private JButton s3JBIntercambio;
    private JLabel s3JlMoneda;
    private JLabel s3JlNombreMoneda ;
    private JLabel s3JlEquivalente;
    private JLabel celcio;
    private JLabel kelvin;
    private JLabel fahrenheit;
    private JPopupMenu pop;
    private GridBagConstraints g;
    private JMenuItem jmiCelcios;
    private JMenuItem jmiFarenheit;
    private JMenuItem jmiKelvin;
    private boolean panel1;
}

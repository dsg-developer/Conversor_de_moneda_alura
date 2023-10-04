package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import model.TempConverter;
import model.TemperatureDB;
import view.Divisas;
import view.Temperaturas;

public class CTemperaturas {

    public CTemperaturas() {
        initC();
    }
    
    private void initC(){
        jfTemperaturas = new Temperaturas();
        grado = new TempConverter();
                        
        grados = new float[2];
        labelSize = 7f;
        
        jfTemperaturas.getJmiCelcio().addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                jmiCheckSelection(TemperatureDB.CELSIUS, "/recursos/imgs/celcio.png", 
                                     "Celsius / Celcio", labelSize);
            }
        });
        
        jfTemperaturas.getJmiFarenheit().addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                jmiCheckSelection(TemperatureDB.FAHRENHEIT, "/recursos/imgs/fahrenheit.png", 
                                   "Fahrenheit", labelSize);
            }
        });
        
        jfTemperaturas.getJmiKelvin().addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                jmiCheckSelection(TemperatureDB.KELVIN, "/recursos/imgs/kelvin.png", 
                             "Kelvin", labelSize);
            }
        });

        jfTemperaturas.getS1JtfGrado().addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(jfTemperaturas.getS1JtfGrado().getText().equals("Digita tu valor")){
                    jfTemperaturas.getS1JtfGrado().setText("");
                }
            }
                        
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(select1 != null && select2 != null) gettingText(e);
            }
            
        });
        
        jfTemperaturas.getS3JBIntercambio().addActionListener((e)->{
            cambio();
        });
    }
        
    public void cambio(){
        TemperatureDB[] mdb = {select1, select2};
        select1 = mdb[1];
        select2 = mdb[0];
        grados[0] = grado.convert(1, select1, select2);
        grados[1] = grado.convert(1, select2, select1);
        jfTemperaturas.getS1JlEquivalente().setText("1 "+select2+" = "+grados[1]+" "+select1);
        jfTemperaturas.getS2JlEquivalente().setText("1 "+select1+" = "+grados[0]+" "+select2);
        jfTemperaturas.getS2JtfMonto().setText(String.valueOf(grados[0]));
        Icon[] i = {
            jfTemperaturas.getS1JlGrado().getIcon(), jfTemperaturas.getS2JlGrado().getIcon()
        };
        jfTemperaturas.getS1JlGrado().setIcon(i[1]);
        jfTemperaturas.getS2JlGrado().setIcon(i[0]);
        jfTemperaturas.getS3JlGrado().setIcon(i[1]);
        jfTemperaturas.getS3JlMoneda().setIcon(i[0]);
        jfTemperaturas.getS3JlNombreMoneda().setIcon(i[0]);
        String[] n = {jfTemperaturas.getS1JlNombreMoneda().getText(), jfTemperaturas.getS2JlNombreMoneda().getText()};
        jfTemperaturas.getS1JlNombreMoneda().setText(n[1]);
        jfTemperaturas.getS2JlNombreMoneda().setText(n[0]);
        if (jfTemperaturas.getS1JtfGrado().getText().matches("[0-9]*")) {
            if(!jfTemperaturas.getS1JtfGrado().getText().isEmpty()) grados();
        }
    }
    
    private void jmiCheckSelection(TemperatureDB temperatura, String pathGrado, String nombre, float size){
        if(jfTemperaturas.isPanel1()){
            select1 = temperatura;
            jfTemperaturas.setLabelImg(jfTemperaturas.getS1JlGrado(), pathGrado);
            jfTemperaturas.getS1JlNombreMoneda().setText(nombre);
            if(select2 != null){
                grados[0] = grado.convert(1,select1, select2);
                grados[1] = grado.convert(1, select2, select1);
                jfTemperaturas.getS1JlEquivalente().setText("1 "+select2+" = "+grados[1]+" "+select1);
                jfTemperaturas.getS2JlEquivalente().setText("1 "+select1+" = "+grados[0]+" "+select2);
                jfTemperaturas.getS1JlEquivalente().setFont(jfTemperaturas.getS1JlEquivalente().getFont().deriveFont(size));
                jfTemperaturas.getS2JlEquivalente().setFont(jfTemperaturas.getS2JlEquivalente().getFont().deriveFont(size));
                jfTemperaturas.getS2JtfMonto().setText(String.valueOf(grados[0]));
                jfTemperaturas.setLabelImg(jfTemperaturas.getS3JlGrado(), pathGrado);
                jfTemperaturas.getS1JtfGrado().setEditable(true);
                jfTemperaturas.getS3JBIntercambio().setEnabled(true);
                if (jfTemperaturas.getS1JtfGrado().getText().matches("[0-9]*")) {
                    if(!jfTemperaturas.getS1JtfGrado().getText().isEmpty()) grados();
                }
            }else{
                jfTemperaturas.setLabelImg(jfTemperaturas.getS3JlGrado(), pathGrado);
            }
        }else{
            select2 = temperatura;
            jfTemperaturas.setLabelImg(jfTemperaturas.getS2JlGrado(), pathGrado);
            jfTemperaturas.getS2JlNombreMoneda().setText(nombre);
            if(select1 != null){
                grados[0] = grado.convert(1,select1, select2);
                grados[1] = grado.convert(1,select2, select1);
                jfTemperaturas.getS1JlEquivalente().setText("1 "+select2+" = "+grados[1]+" "+select1);
                jfTemperaturas.getS2JlEquivalente().setText("1 "+select1+" = "+grados[0]+" "+select2);
                jfTemperaturas.getS1JlEquivalente().setFont(jfTemperaturas.getS1JlEquivalente().getFont().deriveFont(size));
                jfTemperaturas.getS2JlEquivalente().setFont(jfTemperaturas.getS2JlEquivalente().getFont().deriveFont(size));
                jfTemperaturas.getS2JtfMonto().setText(String.valueOf(grados[0]));
                jfTemperaturas.setLabelImg(jfTemperaturas.getS3JlMoneda(), pathGrado);
                jfTemperaturas.getS1JtfGrado().setEditable(true);
                jfTemperaturas.getS3JBIntercambio().setEnabled(true);
                jfTemperaturas.setLabelImg(jfTemperaturas.getS3JlNombreMoneda(), pathGrado);
                if (jfTemperaturas.getS1JtfGrado().getText().matches("[0-9]*")) {
                    if(!jfTemperaturas.getS1JtfGrado().getText().isEmpty()) grados();
                }
            }else{
                jfTemperaturas.setLabelImg(jfTemperaturas.getS3JlMoneda(), pathGrado);
            }
        }
    }
    
    private void gettingText(KeyEvent ke){
        if(select1 == null || select2 == null){
            
        }
        if (jfTemperaturas.getS1JtfGrado().getText().matches("[0-9]\\.?[0-9]*")) {
                    grados();
        }
    }
    
    private void grados(){
        float valor = Float.parseFloat(jfTemperaturas.getS1JtfGrado().getText());
        float conversion = grado.convert(valor, select1, select2);
        jfTemperaturas.getS3JlEquivalente().setText(String.valueOf(conversion));
    }
    
    private Temperaturas jfTemperaturas;
    private TempConverter grado;
    private float[] grados;
    private TemperatureDB select1 = null, select2 = null;
    private float labelSize;
}

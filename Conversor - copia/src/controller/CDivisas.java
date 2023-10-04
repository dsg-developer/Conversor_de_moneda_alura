package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import model.MonedasDB;
import model.Precios;
import view.Divisas;

public class CDivisas {

    public CDivisas() {
        initC();
    }
    
    private void initC(){
        vprincipal = new Divisas();
        precios = new Precios();
                        
        moneda = new float[2];
        labelSize = 10.4f;
        vprincipal.getJmiDolar().addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                jmiCheckSelection(MonedasDB.DOLAR, "/recursos/imgs/usa.png", 
                        "/recursos/imgs/signo dolar2.png", "USA / Dolar", labelSize);
            }
        });
        
        vprincipal.getJmiEuropa().addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                jmiCheckSelection(MonedasDB.EURO, "/recursos/imgs/europe.png", 
                        "/recursos/imgs/signo euro.png", "EUR / Euro", labelSize);
            }
        });
        
        vprincipal.getJmiLondres().addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                jmiCheckSelection(MonedasDB.GBP, "/recursos/imgs/londres.png", 
                        "/recursos/imgs/signo libra esterlina2.png", "GBP", labelSize);
            }
        });
        
        vprincipal.getJmiRD().addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                jmiCheckSelection(MonedasDB.PESO, "/recursos/imgs/RD.png", 
                        "/recursos/imgs/signo RD.png", "DOP / Pesos", labelSize);
            }
        });
        
        vprincipal.getJmiJapon().addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                jmiCheckSelection(MonedasDB.YEN, "/recursos/imgs/japon.png", 
                        "/recursos/imgs/signo yuan2.png", "JPY / YEN", labelSize);
            }
        });
        
        vprincipal.getJmiSurCorea().addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                jmiCheckSelection(MonedasDB.WON, "/recursos/imgs/sulcorea.png", 
                        "/recursos/imgs/signo won.png", "KRW / WON", labelSize);
            }
        });

        vprincipal.getS1JtfMonto().addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(vprincipal.getS1JtfMonto().getText().equals("Digita tu monto")){
                    vprincipal.getS1JtfMonto().setText("");
                }
            }
                        
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(select1 != null && select2 != null) gettingText(e);
            }
        });
        
        vprincipal.getS3JBIntercambio().addActionListener((e)->{
            cambio();
        });
    }
        
    public void cambio(){
        MonedasDB[] mdb = {select1, select2};
        select1 = mdb[1];
        select2 = mdb[0];
        moneda[0] = precios.equivalencia(select1, select2);
        moneda[1] = precios.equivalencia(select2, select1);
        vprincipal.getS1JlEquivalente().setText("1 "+select2+" = "+moneda[1]+" "+select1);
        vprincipal.getS2JlEquivalente().setText("1 "+select1+" = "+moneda[0]+" "+select2);
        vprincipal.getS2JtfMonto().setText(String.valueOf(moneda[0]));
        Icon[] i = {
            vprincipal.getS1JlPais().getIcon(), vprincipal.getS1JlMoneda().getIcon(),
            vprincipal.getS2JlPais().getIcon(), vprincipal.getS2JlMoneda().getIcon()
        };
        vprincipal.getS1JlPais().setIcon(i[2]);
        vprincipal.getS1JlMoneda().setIcon(i[3]);
        vprincipal.getS2JlPais().setIcon(i[0]);
        vprincipal.getS2JlMoneda().setIcon(i[1]);
        vprincipal.getS3JlPais().setIcon(i[2]);
        vprincipal.getS3JlMoneda().setIcon(i[0]);
        vprincipal.getS3JlNombreMoneda().setIcon(i[1]);
        String[] n = {vprincipal.getS1JlNombreMoneda().getText(), vprincipal.getS2JlNombreMoneda().getText()};
        vprincipal.getS1JlNombreMoneda().setText(n[1]);
        vprincipal.getS2JlNombreMoneda().setText(n[0]);
        if (vprincipal.getS1JtfMonto().getText().matches("[0-9]*")) {
            if(!vprincipal.getS1JtfMonto().getText().isEmpty()) divisa();
        }
    }
    
    private void jmiCheckSelection(MonedasDB monedaDB, String pathPais, String pathMoneda, String signo, float size){
        if(vprincipal.isPanel1()){
            select1 = monedaDB;
            vprincipal.setLabelImg(vprincipal.getS1JlPais(), pathPais);
            vprincipal.setLabelImg(vprincipal.getS1JlMoneda(), pathMoneda);
            vprincipal.getS1JlNombreMoneda().setText(signo);
            if(select2 != null){
                moneda[0] = precios.equivalencia(select1, select2);
                moneda[1] = precios.equivalencia(select2, select1);
                vprincipal.getS1JlEquivalente().setText("1 "+select2+" = "+moneda[1]+" "+select1);
                vprincipal.getS2JlEquivalente().setText("1 "+select1+" = "+moneda[0]+" "+select2);
                vprincipal.getS1JlEquivalente().setFont(vprincipal.getS1JlEquivalente().getFont().deriveFont(size));
                vprincipal.getS2JlEquivalente().setFont(vprincipal.getS2JlEquivalente().getFont().deriveFont(size));
                vprincipal.getS2JtfMonto().setText(String.valueOf(moneda[0]));
                vprincipal.setLabelImg(vprincipal.getS3JlPais(), pathPais);
                vprincipal.getS1JtfMonto().setEditable(true);
                vprincipal.getS3JBIntercambio().setEnabled(true);
                if (vprincipal.getS1JtfMonto().getText().matches("[0-9]*")) {
                    if(!vprincipal.getS1JtfMonto().getText().isEmpty()) divisa();
                }
            }else{
                firstParth = pathPais;
                vprincipal.setLabelImg(vprincipal.getS3JlPais(), pathPais);
            }
        }else{
            select2 = monedaDB;
            vprincipal.setLabelImg(vprincipal.getS2JlPais(), pathPais);
            vprincipal.setLabelImg(vprincipal.getS2JlMoneda(), pathMoneda);
            vprincipal.getS2JlNombreMoneda().setText(signo);
            if(select1 != null){
                moneda[0] = precios.equivalencia(select1, select2);
                moneda[1] = precios.equivalencia(select2, select1);
                vprincipal.getS1JlEquivalente().setText("1 "+select2+" = "+moneda[1]+" "+select1);
                vprincipal.getS2JlEquivalente().setText("1 "+select1+" = "+moneda[0]+" "+select2);
                vprincipal.getS1JlEquivalente().setFont(vprincipal.getS1JlEquivalente().getFont().deriveFont(size));
                vprincipal.getS2JlEquivalente().setFont(vprincipal.getS2JlEquivalente().getFont().deriveFont(size));
                vprincipal.getS2JtfMonto().setText(String.valueOf(moneda[0]));
                vprincipal.setLabelImg(vprincipal.getS3JlMoneda(), pathPais);
                vprincipal.getS1JtfMonto().setEditable(true);
                vprincipal.getS3JBIntercambio().setEnabled(true);
                vprincipal.setLabelImg(vprincipal.getS3JlNombreMoneda(), pathMoneda);
                if (vprincipal.getS1JtfMonto().getText().matches("[0-9]*")) {
                    if(!vprincipal.getS1JtfMonto().getText().isEmpty()) divisa();
                }
            }else{
                firstParth = pathPais;
                firstParthSigno = pathMoneda;
                vprincipal.setLabelImg(vprincipal.getS3JlMoneda(), pathPais);
            }
        }
    }
    
    private void gettingText(KeyEvent ke){
        if (vprincipal.getS1JtfMonto().getText().matches("[0-9]\\.?[0-9]*")) {
                    divisa();
        }
    }
    
    private void divisa(){
        float valor = Float.parseFloat(vprincipal.getS1JtfMonto().getText());
        float conversion = precios.convertir(valor, select1, select2);
        vprincipal.getS3JlEquivalente().setText(String.valueOf(conversion));
    }
    
    private Divisas vprincipal;
    private Precios precios;
    private float[] moneda;
    private MonedasDB select1 = null, select2 = null;
    private float labelSize;
    private String firstParth;
    private String firstParthSigno;
}

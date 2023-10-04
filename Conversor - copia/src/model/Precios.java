package model;

public class Precios {

    public Precios() {
        //conversiones = {}
    }
    
    public float convertir(float valor, MonedasDB moneda1, MonedasDB moneda2){
        return valor*conversiones[moneda1.ordinal()][moneda2.ordinal()];
    }
    
    public float equivalencia(MonedasDB moneda1, MonedasDB moneda2){
        int a = moneda1.ordinal();
        int b = moneda2.ordinal();
        return conversiones[a][b];
    }
    
    private float[][] conversiones = {
        {1.0f, 0.91020429f, 0.78277917f, 56.180314f, 143.0712f, 1296.5142f},
        {1.0983779f, 1.0f, 0.8600282f, 61.702023f, 157.04985f, 1423.8512f},
        {1.2772252f, 1.1628874f, 1.0f, 71.760444f, 182.68608f, 1655.7192f},
        {0.017799746f, 0.016202374f, 0.013933511f, 1.0f, 2.5454621f, 23.070269f},
        {0.0069927429f, 0.0063640974f, 0.0054731126f, 0.39284667f, 1.0f, 9.0630844f},
        {0.00077149757f, 0.00070207921f, 0.00060392636f, 0.043363308f, 0.1103001f, 1.0f}
    };
}

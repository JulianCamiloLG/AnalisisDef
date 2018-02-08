/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis.modelos;

/**
 *
 * @author JulianCamilo
 */
public class Deposito {
    
    private String mineral;
    private int cantidadMineral;
    private int x;
    private int y;

    public Deposito() {
    }

    public Deposito(String mineral, int cantidadMineral) {
        this.mineral = mineral;
        this.cantidadMineral = cantidadMineral;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    /**
     * @return the mineral
     */
    public String getMineral() {
        return mineral;
    }

    /**
     * @param mineral the mineral to set
     */
    public void setMineral(String mineral) {
        this.mineral = mineral;
    }

    /**
     * @return the cantidadMineral
     */
    public int getCantidadMineral() {
        return cantidadMineral;
    }

    /**
     * @param cantidadMineral the cantidadMineral to set
     */
    public void setCantidadMineral(int cantidadMineral) {
        this.cantidadMineral = cantidadMineral;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    
    
    
}

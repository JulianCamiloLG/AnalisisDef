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
public class Minero {
    
    private int especialidad;
    private int velocidad;
    private int capacidadCarga;
    private boolean esComodin;
    private float paga;

    public Minero() {
    }

    public Minero(int especialidad, int velocidad, int capacidadCarga, boolean esComodin, float paga) {
        this.especialidad = especialidad;
        this.velocidad = velocidad;
        this.capacidadCarga = capacidadCarga;
        this.esComodin = esComodin;
        this.paga = paga;
    }

    /**
     * @return the especialidad
     */
    public int getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @return the velocidad
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * @param velocidad the velocidad to set
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * @return the capacidadCarga
     */
    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    /**
     * @param capacidadCarga the capacidadCarga to set
     */
    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    /**
     * @return the esComodin
     */
    public boolean isEsComodin() {
        return esComodin;
    }

    /**
     * @param esComodin the esComodin to set
     */
    public void setEsComodin(boolean esComodin) {
        this.esComodin = esComodin;
    }

    /**
     * @return the paga
     */
    public float getPaga() {
        return paga;
    }

    /**
     * @param paga the paga to set
     */
    public void setPaga(float paga) {
        this.paga = paga;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }



    
    
    
    
}

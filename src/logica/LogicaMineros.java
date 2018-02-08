/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import analisis.modelos.Mina;
import analisis.modelos.Minero;
import java.util.LinkedList;

/**
 *
 * @author JulianCamilo
 */
public class LogicaMineros {
    
    LinkedList<Minero> mineros;
    LogicaMinas manejoMinas;

    public LogicaMineros() {
    }
    
    public void AsignarMineros(int cantidadMineroOro,int cantidadMineroPlata,int cantidadMineroCobre,int cantidadMineroComodin){
        LinkedList<Mina> minas=manejoMinas.obtenerMinas();
        
    }
    
}

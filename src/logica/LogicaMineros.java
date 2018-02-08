/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import analisis.modelos.Mina;
import analisis.modelos.Minero;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 *
 * @author JulianCamilo
 */
public class LogicaMineros {
    
    LinkedList<Minero> mineros;
    LogicaMinas manejoMinas;
    int totalMinerosOro;
    int totalMinerosPlata;
    int totalMinerosCobre;
    int totalMinerosComodin;

    public LogicaMineros() {
    }

    public LogicaMineros( int totalMinerosOro, int totalMinerosPlata, int totalMinerosCobre, int totalMinerosComodin) {
        this.manejoMinas = new LogicaMinas();
        this.totalMinerosOro = totalMinerosOro;
        this.totalMinerosPlata = totalMinerosPlata;
        this.totalMinerosCobre = totalMinerosCobre;
        this.totalMinerosComodin = totalMinerosComodin;
        this.manejoMinas= new LogicaMinas();
    }
    
    
    public void AsignarMineros( LinkedList<Mina> minas) {
        LinkedHashMap<String, LinkedList<Mina>> minasXMaterial = minasXMaterial(minas);
        int[] minerosOro;
        int[] minerosPlata;
        int[] minerosCobre;
        if (!minasXMaterial.get("Oro").isEmpty()) {
            minerosOro = asignarMinerosMaterial(minasXMaterial.get("Oro"));
        }
        if (!minasXMaterial.get("Plata").isEmpty()) {
            minerosPlata = asignarMinerosMaterial(minasXMaterial.get("Plata"));
        }
        if (!minasXMaterial.get("Cobre").isEmpty()) {
            minerosCobre = asignarMinerosMaterial(minasXMaterial.get("Cobre"));
        }
        if (totalMinerosComodin != 0) {
            
        }

    }

    private LinkedHashMap<String, LinkedList<Mina>> minasXMaterial(LinkedList<Mina> minas) {
       //To change body of generated methods, choose Tools | Templates.
       LinkedHashMap<String, LinkedList<Mina>> result = new LinkedHashMap();
       LinkedList<Mina> nuevasMinasOro= new LinkedList();
       LinkedList<Mina> nuevasMinasPlata= new LinkedList();
       LinkedList<Mina> nuevasMinasCobre= new LinkedList();
       String oro="Oro";
       String plata="Plata";
       String cobre="Cobre";
        for (Mina mina : minas) {
            if (mina.getMetal().equals("Oro")) {
                nuevasMinasOro.add(mina);
            }
            else if (mina.getMetal().equals("Plata")) {
                nuevasMinasPlata.add(mina);
            }
            else
                nuevasMinasCobre.add(mina);
        }
        result.put(oro, nuevasMinasOro);
        result.put(plata, nuevasMinasPlata);
        result.put(cobre, nuevasMinasCobre);
        
        return result;

        
    }

    private int[] asignarMinerosMaterial(LinkedList<Mina> get) {
        //To change body of generated methods, choose Tools | Templates.}
        int aux = totalMinerosOro;
        int total=0;
        int carry=0;
        int minerosXmina[]= new int[get.size()];
        for (Mina mina : get) {
            if(aux!=0){
                minerosXmina[carry]=mina.getDepositos().size();
                aux-=mina.getDepositos().size();
                carry++;
            }
        }
        if(aux!=0){
            int i=0;
            while(aux>0){
                if (i<minerosXmina.length) {
                    if(minerosXmina[i]<=get.get(i).getMaxmineros()){
                        minerosXmina[i]+=1;
                        aux--;
                        i++;
                    }
                }
                else
                    i=0;
            }
        }
        
        return minerosXmina;
        
    }
    
}

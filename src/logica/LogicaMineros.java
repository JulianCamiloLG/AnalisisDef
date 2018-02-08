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
    }
    
    
    public void AsignarMineros(int cantidadMineroOro,int cantidadMineroPlata,int cantidadMineroCobre,int cantidadMineroComodin){
        LinkedList<Mina> minas=manejoMinas.obtenerMinas();
        LinkedHashMap<String, LinkedList<Mina>> minasXMaterial=minasXMaterial(minas);
        int[] minerosOro=asignarMinerosMaterial(minasXMaterial.get("Oro"));
        int[] minerosPlata=asignarMinerosMaterial(minasXMaterial.get("Plata"));
        int[] minerosCobre=asignarMinerosMaterial(minasXMaterial.get("Cobre"));
        if (totalMinerosComodin!=0) {
            
        }
        
    }

    private int[] calcularTotal(LinkedList<Mina> minas) {
        //To change body of generated methods, choose Tools | Templates.
        int total[]= new int[3];
        int totalDepositosOro=0;
        int totalDepositosPlata=0;
        int totalDepositosCobre=0;
        for (Mina mina : minas) {
            if (mina.getMetal().equals("Oro")) {
                totalDepositosOro+=mina.getValorMineral();
            }
            else if (mina.getMetal().equals("Plata")) {
                totalDepositosPlata+=mina.getValorMineral();
            }else{
                totalDepositosCobre+=mina.getValorTotal();
            }
        }
        total[0]=totalDepositosOro;
        total[1]=totalDepositosPlata;
        total[2]=totalDepositosCobre;
        
        return total;
    }

    private LinkedHashMap<String, Integer> obtenerDepositosMina(LinkedList<Mina> minas) {
         //To change body of generated methods, choose Tools | Templates.
         LinkedHashMap<String,Integer> depositos = new LinkedHashMap();
         String nombreMina="";
         int totalDepositos=0;
         for (Mina mina : minas) {
            nombreMina=mina.getNombreMina();
            totalDepositos=mina.getDepositos().size();
            depositos.put(nombreMina, totalDepositos);
        }
         return depositos;
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
        get.sort((o1, o2) -> {
            return o1.getDepositos().size()<o2.getDepositos().size()? -1: o1.getDepositos().size()==o2.getDepositos().size()?0:1; //To change body of generated lambdas, choose Tools | Templates.
        });
        System.out.println(get);
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
            while(aux<0){
                if (i<minerosXmina.length) {
                    minerosXmina[i]+=1;
                    aux--;
                    i++;
                }
                else
                    i=0;
            }
        }
        return minerosXmina;
        
    }
    
}

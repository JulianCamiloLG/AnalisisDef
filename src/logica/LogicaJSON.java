/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import javax.json.*;


/**
 *
 * @author JulianCamilo
 */
public class LogicaJSON{
    
    private String route;

    public LogicaJSON() {
       
    }

    public LogicaJSON(String route) {
        this.route = route;
    }
    
    public Object[] AbrirJSON() throws FileNotFoundException{
        Object datos[] = new Object[3];
        LinkedHashMap<String,String[]> infoMinasCompleta;
        String aMinas[];
        int aMineros[];
        File archivo = new File(route);
        InputStream lector = new FileInputStream(archivo);
        JsonReader lectorJson = Json.createReader(lector);
        JsonArray arregloCompleto =  lectorJson.readArray();
        
        aMineros = extraerInfoMineros(arregloCompleto.getJsonObject(0));
        aMinas=extraerMinas(arregloCompleto.getJsonObject(1));
        infoMinasCompleta= todasLasMinas(arregloCompleto.getJsonObject(2));
        
        datos[0]=aMineros;
        datos[1]=aMinas;
        datos[2]=infoMinasCompleta;
        return datos;
    }

    private int[] extraerInfoMineros(JsonObject mineros) {
        int arregloMineros[] = new int[5];
        JsonObject minerosCompleto=mineros.getJsonObject("infomineros");
        int cont=0;
        for (JsonValue object : minerosCompleto.values()) {
            arregloMineros[cont]=Integer.parseInt(object.toString());
            cont++;
        }
        return arregloMineros;
    }

    private String[] extraerMinas(JsonObject minas) {
        String aMinas[]= new String[7];
        JsonObject generalMina=minas.getJsonObject("infominas");
        int cont=0;
        for (JsonValue value : generalMina.values()) {
            aMinas[cont]=value.toString();
            cont++; 
        }
        return aMinas;
    }

    private LinkedHashMap<String, String[]> todasLasMinas(JsonObject jsonObject) {
        JsonArray arregloMinas = jsonObject.getJsonArray("minas");
        LinkedHashMap<String, String[]> infoMinasCompleta= new LinkedHashMap<>();
        String detalles[]= new String[12];
        for (JsonValue arregloMina : arregloMinas) {
            int cont=0;
            JsonObject minaActual= (JsonObject) arregloMina;
            String nombreMina = "Mina "+minaActual.keySet().toString().substring(1, 2);
            JsonObject detalleMina = minaActual.getJsonObject(minaActual.keySet().toString().substring(1, 2));
            for (JsonValue string : detalleMina.values()) {
                detalles[cont]=string.toString();
                cont++;
            }
            infoMinasCompleta.put(nombreMina, detalles);
            detalles=new String[12];
        }
        return infoMinasCompleta;
    }
    
}

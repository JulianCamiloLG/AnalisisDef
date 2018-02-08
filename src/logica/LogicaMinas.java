/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import analisis.modelos.Deposito;
import analisis.modelos.Mina;
import analisis.modelos.Node;
import java.util.LinkedList;

/**
 *
 * @author JulianCamilo
 */
public class LogicaMinas {

    private LinkedList<Mina> minas;

    public LogicaMinas() {
        this.minas = new LinkedList<>();

    }

    /**
     * Metodo para crear la mina inicial y vacia
     *
     * @param matrizpaneles
     * @param filas_columnas
     * @param mineral
     * @param maxMineros
     * @param nombreMina
     * @param x
     * @param y
     */
    public void crearMinaIniciale(int[][] matrizpaneles, int filas_columnas, String mineral, int maxMineros, String nombreMina, int capacidadDeposito) {
        Mina mina = new Mina(matrizpaneles, filas_columnas, maxMineros, mineral, capacidadDeposito, maxMineros, nombreMina);
        this.minas.add(mina);
    }

    private void convertMatrixToNodes(int posicion) {
        LinkedList<Node> listOfNodes = new LinkedList<>();
        LinkedList<Node> listOfDepositos = new LinkedList<>();
        for (int i = 0; i < minas.get(posicion).getMatrizdepaneles().length; i++) {
            for (int j = 0; j < minas.get(posicion).getMatrizdepaneles()[i].length; j++) {
                if (minas.get(posicion).getMatrizdepaneles()[i][j] > 0) {
                    Node n = new Node(i, j);
                    if (minas.get(posicion).getMatrizdepaneles()[i][j] == 2) {
                        n.setType(2);
                    } else if (minas.get(posicion).getMatrizdepaneles()[i][j] == 3) {
                        n.setType(3);
                    }
                    listOfNodes.add(n);
                }
            }
        }
        for (int i = 0; i < listOfNodes.size(); i++) {
            for (Node n : listOfNodes) {
                LinkedList<Node> neighbors = new LinkedList<>();
                if ((listOfNodes.get(i).getX() == n.getX()) && ((listOfNodes.get(i).getY() + 1) == n.getY())) {
                    listOfNodes.get(i).addNeighbor(n);
                }
                if ((listOfNodes.get(i).getX() == n.getX()) && ((listOfNodes.get(i).getY() - 1) == n.getY())) {
                    listOfNodes.get(i).addNeighbor(n);
                }
                if (((listOfNodes.get(i).getX() + 1) == n.getX()) && ((listOfNodes.get(i).getY()) == n.getY())) {
                    listOfNodes.get(i).addNeighbor(n);
                }
                if (((listOfNodes.get(i).getX() - 1) == n.getX()) && ((listOfNodes.get(i).getY()) == n.getY())) {
                    listOfNodes.get(i).addNeighbor(n);
                }
            }
            if (listOfNodes.get(i).getType() == 2) {
                listOfDepositos.add(listOfNodes.get(i));
            } else if (listOfNodes.get(i).getType() == 3) {
                minas.get(posicion).setNodoEntrada(listOfNodes.get(i));
            }
        }
        minas.get(posicion).setNodos(listOfNodes);
        minas.get(posicion).setNodosDeposito(listOfDepositos);
    }

    //Crea los caminos de la mina.
    public void crearCaminosMina(int posicion) {
        convertMatrixToNodes(posicion);
        for (Node d : minas.get(posicion).getNodosDeposito()) {
            //for (Node n : d.getNeighbors()) {
            PathCalculator path = new PathCalculator(minas.get(posicion).getNodoEntrada(), d);
            path.FindPath();
            System.out.println("" + path.getPath().toString());
            //}
        }
    }

    public void minasPath() {
        for (int i = 0; i < minas.size(); i++) {
            crearCaminosMina(i);
        }
    }

    /**
     * Metodo para cambiar el valor de una casilla en la mina 1= camino
     * 2=deposito
     *
     * @param posicionMina la posicion en la lista que tiene la mina
     * @param columna la columna donde se insertara el cambio
     * @param fila la fila donde se insertara el cambio
     * @param deposito el valor nuevo en la casilla
     * @param cantidadMineral La cantidad de mienral que posee el deposito
     * @return si hubo o no cambio
     */
//    public boolean modificarmina(int posicionMina, int columna, int fila, int deposito, int cantidadMineral){
//        Mina modificar = this.minas.get(posicionMina);
//        boolean result=false;
//        int[][] matriz = modificar.getMatrizdepaneles();
//        if(deposito!=0){
//            matriz[fila][columna]= deposito;
//            this.minas.get(posicionMina).setMatrizdepaneles(matriz);
//            crearNuevoDeposito(deposito,posicionMina, cantidadMineral);
//            result=true;
//        }
//        return result;
//    }
    /**
     *
     * @param cantidadDeposito
     * @param posicionI
     * @param posicionJ
     * @param nombreMina
     * @return
     */
    public boolean crearNuevoDeposito(int posicionI, int posicionJ, String nombreMina) {
        boolean result = false;
        int cantidadMaterialActual = 0;
        int posicion = buscarMinaNombre(nombreMina);
        if (posicion != -1) {
                if (this.minas.get(posicion).getMatrizdepaneles()[posicionI][posicionJ] == 3) {
                    this.minas.get(posicion).setTieneEntrada(false);
                }
                Deposito nuevo = new Deposito(this.minas.get(posicion).getMetal(),(int)this.minas.get(posicion).getValorTotal());
                this.minas.get(posicion).getDepositos().add(nuevo);
                this.minas.get(posicion).getMatrizdepaneles()[posicionI][posicionJ] = 2;
                result = true;   
        }
        return result;
    }

    /**
     * Metodo para asignarle a una mina sus mineros
     *
     * @param posicionMina la posicion en la lista que tiene la mina
     * @param mineros la lista con los mineros que se desean ingresar a esa mina
     * @return si se lograron ingresar o no
     */
    public boolean ingresarMineros(int posicionMina, LinkedList mineros) {
        boolean result = false;
        if (this.minas.get(posicionMina).getMaxmineros() == mineros.size()) {
            result = true;
        }
        return result;
    }

    private int buscarMinaNombre(String nombreMina) {
        int indexMina = -1;
        for (Mina mina : minas) {
            if (mina.getNombreMina().equals(nombreMina)) {
                indexMina = minas.indexOf(mina);
            }
        }
        return indexMina;
    }

    public boolean crearCamino(String nombreMina, int posicion_i_matriz, int posicion_j_matriz) {
        boolean result = false;
        int minaModificar = buscarMinaNombre(nombreMina);
        if (minaModificar != -1) {
            if (this.minas.get(minaModificar).getMatrizdepaneles()[posicion_i_matriz][posicion_j_matriz] == 3) {
                this.minas.get(minaModificar).setTieneEntrada(false);
            }
            this.minas.get(minaModificar).getMatrizdepaneles()[posicion_i_matriz][posicion_j_matriz] = 1;
            result = true;
        }
        return result;

    }

    public boolean crearEntrada(String nombreMina, int posicion_i_matriz, int posicion_j_matriz) {
        boolean result = false;
        int minaModificar = buscarMinaNombre(nombreMina);
        if (minaModificar != -1) {
            this.minas.get(minaModificar).getMatrizdepaneles()[posicion_i_matriz][posicion_j_matriz] = 3;
            this.minas.get(minaModificar).setTieneEntrada(true);
            result = true;
        }
        return result;
    }

    public boolean cancelarCambio(String nombreMina, int posicion_i_matriz, int posicion_j_matriz) {
        boolean result = false;
        int minaModificar = buscarMinaNombre(nombreMina);
        if (minaModificar != -1) {
            if (this.minas.get(minaModificar).getMatrizdepaneles()[posicion_i_matriz][posicion_j_matriz] == 3) {
                this.minas.get(minaModificar).setTieneEntrada(false);
            }
            this.minas.get(minaModificar).getMatrizdepaneles()[posicion_i_matriz][posicion_j_matriz] = 0;
            result = true;
        }
        return result;
    }

    public boolean isEntrada(int posicion_i_matriz, int posicion_j_matriz, String nombreMina) {
        boolean result = false;
        int minaBuscada = buscarMinaNombre(nombreMina);
        if (posicion_i_matriz == 0 || posicion_i_matriz == this.minas.get(minaBuscada).getFilas_columnas() - 1 || posicion_j_matriz == 0 || posicion_j_matriz == this.minas.get(minaBuscada).getFilas_columnas() - 1) {
            if (!this.minas.get(minaBuscada).isTieneEntrada()) {
                result = true;
            }
        }
        return result;
    }

    public LinkedList<Mina> getMinas() {
        return this.minas;
    }
    
    
}

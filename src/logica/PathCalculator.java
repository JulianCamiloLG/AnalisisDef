/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;
/*
 * Clase que controla los caminos más cortos
 * Implementación de la búsqueda A*
 * A star algorithm, para mas info http://www.cokeandcode.com/main/tutorials/path-finding/
 * https://www.geeksforgeeks.org/a-search-algorithm/
 */
import analisis.modelos.Node;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author unalman
 */
public class PathCalculator {

    private Node start;
    private Node end;
    private boolean pathFound;
    private PriorityQueue<Node> openList;
    private ArrayList<Node> closedList;
    private int cost = 1;
    private LinkedList<Node> path;

    public PathCalculator(Node start, Node end) {
        this.start = start;
        this.end = end;
        this.pathFound = false;
        this.closedList = new ArrayList<Node>();
        this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node0, Node node1) {
                return Double.compare(node0.getCost(), node1.getCost());
            }
        });
        this.path = new LinkedList<Node>();
        this.openList.add(start);
    }

    public void FindPath() {
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            closedList.add(current);
            if (end.equals(current)) {
                pathFound = true;
                getPath(current);
            } else {
                addAdjacents(current);
            }
        }
    }

    //el algoritmo necesita encontrar sus adyacencias, esto se hace con la lista de vecinos
    // como estamos hablando de caminos, el tiene que saber de donde viene, por eso el padre.
    public void addAdjacents(Node current) {
        for (Node adjacent : current.getNeighbors()) {
            if (!openList.contains(adjacent) && !closedList.contains(adjacent)) {
                setHeuristic(adjacent);
                openList.add(adjacent);
                adjacent.setPredecessor(current);
            }
        }
    }

    //la heuristica se calcula para tomar la mejor decisión 
    public void setHeuristic(Node node) {
        node.setCost((int) Math.sqrt(Math.pow(node.getX() - end.getX(), 2) + Math.pow(node.getY() - end.getY(), 2)));
    }

    public void getPath(Node current) {
        while (current != start) {
            path.addFirst(current);
            current.setInRoute(true);
            current = current.getPredecessor();
        }
    }

    public LinkedList<Node> getPath() {
        return path;
    }
   
}
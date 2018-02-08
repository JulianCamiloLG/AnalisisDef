/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis.modelos;

import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class Node {
    private int x;
    private int y;
    private int cost;
    private int type;
    private ArrayList<Node> neighbors;
    private Node predecessor;
    private boolean inRoute;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = 1;
        this.cost = 0;
        neighbors = new ArrayList<>();
        this.predecessor = null;
        this.inRoute = false;
    }
    
    public void addNeighbor(Node node){
        neighbors.add(node);
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setInRoute(boolean inRoute) {
        this.inRoute = inRoute;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getCost() {
        return cost;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    @Override
    public String toString() {
        return ("{"+this.x+"-"+this.y+"}"); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

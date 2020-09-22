
package Grafos;

import java.util.*;

public class Vertex{

    Object id;
    Vertex next; //Esto y sus metodos getter and setter se usan solo en Implementacion2 
    List<Edge> edge; //Esto y sus metodos getter and setter se usan solo en Implementacion2 

    public Vertex(Object ele) {
        this.id = ele;
        this.next = null;
        this.edge = new ArrayList<Edge>();
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Vertex getNext() {
        return next;
    }

    public void setNext(Vertex next) {
        this.next = next;
    }

    public List<Edge> getEdge() {
        return edge;
    }

    public void setEdge(List<Edge> edge) {
        this.edge = edge;
    }

   

    @Override
    public String toString() {
        return "Vertex{" + "id=" + id + '}';
    }

    
    
}

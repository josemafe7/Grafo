
package Grafos;

public class Edge {

    Object element;
    Vertex origen;
    Vertex destino;
    


    public Edge(Object element, Vertex origen, Vertex destino) {
        this.element = element;
        this.origen = origen;
        this.destino = destino;
    }



    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public Vertex getOrigen() {
        return origen;
    }

    public void setOrigen(Vertex origen) {
        this.origen = origen;
    }

    public Vertex getDestino() {
        return destino;
    }

    public void setDestino(Vertex destino) {
        this.destino = destino;
    }

    

    @Override
    public String toString() {
        return "Edge{" + "element=" + element + '}';
    }
    
    
    
}

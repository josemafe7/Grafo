
package Grafos;

import java.util.*;

public class Implementacion2Graph implements Graph{

    private Vertex vertices;

    public Implementacion2Graph() {
        this.vertices = null;
    }

    public Implementacion2Graph(Vertex vertices) {
        this.vertices = vertices;
    }

    public Vertex[] endVertices(Edge e) {
        boolean enc = false;
        Vertex v[] = new Vertex[2];
        Vertex vertice = vertices;
        while (vertice != null && !enc) {
            Iterator it = vertice.getEdge().iterator();
            while (it.hasNext() && !enc) {
                Edge aristas = (Edge) it.next();
                if (aristas.equals(e)) {
                    v[0] = aristas.getOrigen();
                    v[1] = aristas.getDestino();
                    enc = true;
                }
            }
            vertice = vertice.getNext();

        }

        return v;
    }

    public Vertex opposite(Vertex v, Edge e) {
        Vertex[] ver = endVertices(e);

        if (ver[0].equals(v)) {
            return ver[1];
        } else {
            return ver[0];
        }

    }

    public boolean areAdjacent(Vertex w1, Vertex w2) {
        Vertex v = vertices;
        boolean enc = false, res = false;
        while (v != null && !enc) {
            if (v.equals(w1)) {
                enc = true;
            } else {
                v = v.getNext();
            }
        }
        if (enc == true) {
            Iterator it = v.getEdge().iterator();
            while (it.hasNext() && !res) {
                Edge e = (Edge) it.next();
                if (e.getDestino().equals(w2)) {
                    res = true;
                }
            }
        }

        return res;

    }

    public boolean replace(Vertex v, Object x) {
        boolean res = false;
        Vertex vertice = vertices;
        while (vertice != null && !res) {
            if (vertice.equals(v)) {
                vertice.setId(x);
                res = true;
            } else {
                v = v.getNext();
            }
        }
        return res;

    }

    public boolean replace(Edge e, Object x) {
        boolean res = false;
        Iterator it = edges();
        while (it.hasNext() && !res) {
            Edge arista = (Edge) it.next();
            if (arista.equals(e)) { //Idea mejor: Redifinir equals de edge, para que mire absolutamente todos los campos de Arista
                arista.setElement(x);
                res = true;
            }
        }
        return res;
    }

    public boolean insertVertex(Object o) {
        if (o != null) {
            if (vertices == null){
                vertices = new Vertex(o);
                return true;
            } else {
            Vertex v = vertices;
            while (v.getNext() != null) {
                v = v.getNext();
            }
            v.setNext(new Vertex(o));
            return true;
            }
        } else {
            return false;
        }
    }

    public boolean insertEdge(Vertex v, Vertex w, Object o) {
        boolean enc = false, enc2 = false;;
        Iterator it = vertices();
        Vertex vertice = null;
        Vertex dest = null;
        while(it.hasNext() && !enc){
            vertice = (Vertex) it.next();
            if (vertice.equals(v)){ 
                enc = true;
                Iterator it2 = vertices();
                while(it2.hasNext() && !enc2){
                    dest = (Vertex)it2.next();
                    if (dest.equals(w)){
                        enc2= true;
                    }
                }
                
            
            }
        }
        
        if (enc == true && enc2 == true){
            vertice.getEdge().add(new Edge (o, vertice, dest));
            return true;
        } else {
            return false;
        }


    }


    public boolean removeVertex(Vertex v) { 
        if (vertices.equals(v)){
            vertices = vertices.getNext();
            Iterator it = edges();
                while(it.hasNext()){
                    Edge e = (Edge) it.next();
                    if(e.getOrigen().equals(v) || e.getDestino().equals(v)){
                        removeEdge(e);
                    }
                }
            return true;
        } else {
            boolean enc = false;
            Vertex ver = vertices;
            while( ver.getNext() != null && !enc){
                if (ver.getNext().equals(v))
                    enc = true;
                else
                    ver = ver.getNext();
            }
            if (enc){
                /*Vertex quitarAristas = ver.getNext();
                Iterator it = quitarAristas.getEdge().iterator();
                while(it.hasNext()){
                    removeEdge((Edge) it.next());
                }                */
                Iterator it = edges();
                while(it.hasNext()){
                    Edge e = (Edge) it.next();
                    if(e.getOrigen().equals(v) || e.getDestino().equals(v)){
                        removeEdge(e);
                    }
                }
                ver.setNext(ver.getNext().getNext());
                
                return true;
            } else
                return false;
        }

    }


    public boolean removeEdge(Edge e) {
        boolean res1 = false, res2= false;
        Vertex[] v = endVertices(e);
  
        res1 = v[0].getEdge().remove(e);
        res2 = v[1].getEdge().remove(e);
        
        if(res1 || res2){
            return true;
        } else {
            return false;
        }
    }


    public Iterator vertices() {
        Collection c = new ArrayList();
        Vertex v = vertices;
        while( v != null){
            c.add(v);
            v = v.getNext();
        }
        return c.iterator();
    }

    public Iterator edges() {

        Collection c = new ArrayList();
        Iterator it = vertices();
        while (it.hasNext()) {
            Vertex v = (Vertex) it.next();
            List aristas = v.getEdge();
            c.addAll(aristas);
        }
        return c.iterator();

    }


}


package Grafos;

import java.util.*;

public class Implementacion1Graph implements Graph {

    private List<Vertex> vertices;
    private Edge[][] aristas;
    private int numVertices;
    private static final int MAX_TAM = 100;

    public Implementacion1Graph() {
        this.vertices = new ArrayList(MAX_TAM);
        this.aristas = new Edge[MAX_TAM][MAX_TAM];
        this.numVertices = 0;

    }

    public Implementacion1Graph(List<Vertex> vertices, Edge[][] aristas, int numVertices) {
        this.vertices = vertices;
        this.aristas = aristas;
        this.numVertices = numVertices;
    }

    public Vertex[] endVertices(Edge e) { //busco el vertice en la tabla de aristas, si está me quedo con su fila y columna que serian sus vertices
        Vertex[] v = new Vertex[2];
        boolean enc = false;
        Iterator it = edges();
        while (it.hasNext() && !enc) {
            Edge arista = (Edge) it.next();
            if (arista.equals(e)) {
                v[0] = arista.getOrigen();
                v[1] = arista.getDestino();
                enc = true;
            }
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
        boolean enc1 = false, enc2 = false;
        Vertex v = null;
        int i = 0;
        Iterator it = vertices.iterator();
        while (it.hasNext() && !enc1) {
            v = (Vertex) it.next();
            if (v.equals(w1)) {
                enc1 = true;
            } else {
                i++;
            }
        }

        if (enc1 == true) {
            for(int j = 0; j<numVertices && !enc2;j++){
                if(aristas[i][j] != null){
                if (aristas[i][j].getDestino().equals(w2)) { // Preguntar Si A -> B (no A<->B) A es ady con B, B no con A (eso he implementado)
                    enc2 = true;
                }
                }
            }
            

            return enc2;
        } else {
            return false;
        }

    }

    public boolean replace(Vertex v, Object x) {
        boolean enc = false;
        Iterator it = vertices.iterator();
        while (it.hasNext() && !enc) {
            Vertex vertice = (Vertex) it.next();
            if (vertice.equals(v)) {
                enc = true;
                vertice.setId(x);
            }
        }

        return enc;

    }

    public boolean replace(Edge e, Object x) { //Lo cambio solo en la lista, tambien en matriz hay que cambiarlo supongo

        boolean enc = false;

        for (int i = 0; i < numVertices && !enc; i++) {
            for (int j = 0; j < numVertices && !enc; j++) {
                if (aristas[i][j] != null) {
                    if (aristas[i][j].equals(e)) {
                        aristas[i][j].setElement(x);
                        enc = true;
                    }
                }
            }
        }

        return enc;

    }

    public boolean insertVertex(Object o) {
        if (o != null) {
            Vertex v = new Vertex(o);
            numVertices++;
            return vertices.add(v);
        } else {
            return false;
        }
    }

    public boolean insertEdge(Vertex v, Vertex w, Object o) {
        if (o != null) {
            int i = 0, j = 0;
            boolean enc = false, enc2 = false;
            Vertex verticeOr = null, verticeDest = null;
            Iterator it = vertices();
            while (it.hasNext() && !enc) {
                verticeOr = (Vertex) it.next();
                if (verticeOr.equals(v)) {
                    enc = true;
                } else {
                    i++;
                }
            }

            it = vertices();
            while (it.hasNext() && !enc2) {
                verticeDest = (Vertex) it.next();
                if (verticeDest.equals(w)) {
                    enc2 = true;
                } else {
                    j++;
                }
            }

            if (enc && enc2) {
                aristas[i][j] = new Edge(o, verticeOr, verticeDest);
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public boolean removeVertex(Vertex v) {
        int i = 0;
        boolean enc = false;

        Iterator it = vertices();
        while (it.hasNext() && !enc) {
            Vertex vertice = (Vertex) it.next();
            if (vertice.equals(v)) {
                vertices.remove(v);
                enc = true;
            } else {
                i++;
            }
        }

        if (enc == true) {
            Edge[][] e = new Edge[MAX_TAM][MAX_TAM];
            int posf = 0;
            for (int k = 0; k < numVertices; k++) {
                int posc = 0;
                for (int z = 0; z < numVertices; z++) {
                    if (i != k && i != z) {
                        e[posf][posc] = aristas[k][z];
                        posc++;
                    }
                }
                if (i != k) {
                    posf++;
                }
            }
            numVertices--;
            aristas = e.clone();
        }

        return enc;

        /*
        boolean res = vertices.remove(v);
        if (res)
            numVertices--;
        return res;
         */
    }

    public boolean removeEdge(Edge e) {
        boolean res = false;
        for (int i = 0; i < numVertices && !res; i++) {
            for (int j = 0; j < numVertices && !res; j++) {
                if (aristas[i][j] != null) {
                    if (aristas[i][j].equals(e)) {
                        aristas[i][j] = null;
                        res = true;
                    }
                    
                }
            }
        }
        return res;
    }

    public Iterator vertices() {
        return vertices.iterator();
    }

    public Iterator edges() {
        Collection c = new ArrayList();
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (aristas[i][j] != null) {
                    c.add(aristas[i][j]);
                }
            }
        }
        return c.iterator();

    }

    
    
}

/*EPD11-P
*/

package Grafos;

import java.util.*;

/**
 *
 * @author José_Manuel_Fernández_Labrador
 */

public class Principal {

    public static void main(String[] args) throws Exception{
    
        Graph g = new Implementacion1Graph();
        //Graph g = new Implementacion2Graph();

        g.insertVertex(1);
        g.insertVertex(2);
        g.insertVertex(3);
        g.insertVertex(4);
        
        List <Vertex>l = new ArrayList<Vertex>();
        Iterator <Vertex>it = g.vertices();
        while(it.hasNext()){
            Vertex v = it.next();
            l.add(v);
        }
        
        g.insertEdge(l.get(0), l.get(1), 20);
        g.insertEdge(l.get(0), l.get(2), 30);
        g.insertEdge(l.get(1), l.get(2), 30);
        g.insertEdge(l.get(2), l.get(3), 40);
        //Esto se hace porque quiero que sea no dirigido, otra opcion sería añadirlo en el insertEdge que cuando inserte haga eso y lo opuesto.
        g.insertEdge(l.get(1), l.get(0), 20);
        g.insertEdge(l.get(2), l.get(0), 30);
        g.insertEdge(l.get(2), l.get(1), 30);
        g.insertEdge(l.get(3), l.get(2), 40);
        
        List <Edge>a = new ArrayList<Edge>();
        Iterator <Edge>ite = g.edges();
        while(ite.hasNext()){
            Edge ee = ite.next();
            a.add(ee);
        }
        
        
        System.out.print("\n\nRecorrer vértices: \n");
        Iterator it1 = g.vertices();
        while(it1.hasNext())
            System.out.println(it1.next());
        
        System.out.print("\n\nRecorrer aristas: \n");
        Iterator it2 = g.edges();
        while(it2.hasNext())
            System.out.println(it2.next());
        
        
        System.out.print("\n\nVértices terminales de una arista: ");
        Vertex[]b=g.endVertices(a.get(0));
        for(int i=0;i<b.length;i++){
            System.out.print(b[i]+" ");
        }
        

        System.out.print("\n\nVértice opuesto a una arista: ");
        System.out.println(g.opposite(l.get(0), a.get(0)));
        
        System.out.print("\n\nVértices adyacentes: ");
        System.out.println(g.areAdjacent(l.get(0), l.get(1)));
        
        
        System.out.print("\n\nReemplazar vértice: ");
        g.replace(l.get(0), "A");
        System.out.println(l.get(0));
        
        System.out.print("\n\nReemplazar arista: ");
        g.replace(a.get(0), 10);
        System.out.println(a.get(0));
        
        System.out.print("\n\nEliminar vértice: \n");
        g.removeVertex(l.get(0));
        Iterator it3 = g.edges();
        while(it3.hasNext())
            System.out.println(it3.next());
        
        System.out.print("\n\nEliminar arista: \n");
        g.removeEdge(a.get(7));
        Iterator it4 = g.edges();
        while(it4.hasNext())
            System.out.println(it4.next());
    }
    
}

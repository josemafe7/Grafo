
package Grafos;

import java.util.*;

public class FabricaColores {

    public static void main(String[] args) {
        Graph g = new Implementacion1Graph();
        //Graph g = new Implementacion2Graph();
        
        insertarDatos(g);  
        
        int[] s = new int[5]; //Porque hay 5 colores en la fábrica
        double[] minutero = new double[1];
        solucionaProblema(g, s, minutero);
        imprimirResultados(s, minutero);
        
    }
    
    public static boolean seleccionada(int[] s, int tarea) {
        int i = 0;
        boolean sel = false;
        
        while (i < s.length && !sel) {
            if (s[i++] == tarea) {
                sel = true;
            }
        }
        
        return sel;
    }
    
    public static int mejorColor(double[][] m, int[] s, int fabricando, double[] minutero){
        double min = Double.MAX_VALUE;
        int mejorColorR = -1;

        
        for (int tarea = 0; tarea < m.length; tarea++) {
                if (!seleccionada(s, tarea) && m[fabricando][tarea] < min && fabricando != tarea) {
                    min = m[fabricando][tarea];
                    mejorColorR = tarea;
                    
                }
        }
        
        minutero[0] += min;
        
        return mejorColorR;
    }
    
    public static void solucionaProblema(Graph g, int[] s, double[] minutero){
        
        for (int i = 0; i < s.length; i++) {
            s[i] = -1;
        }
        
        Iterator it = g.vertices();
        List l = new ArrayList();
        while(it.hasNext()){
            l.add((Vertex) it.next());
        }
        
        
        
        double[][] m = new double[l.size()][l.size()];
        
        it = g.edges();
        while(it.hasNext()){
            Edge e = (Edge) it.next();
            m[l.indexOf(e.getOrigen())][l.indexOf(e.getDestino())] = (double) e.getElement();
        }
        
        //
        double menorValor = Double.MAX_VALUE;
        int fil = -1;
        for(int i = 0; i<m.length; i++){
            for(int j=0; j<m[0].length; j++){
                if(m[i][j] < menorValor && i != j){
                    menorValor = m[i][j];
                    fil = i;
                }
            }
        }
        s[0] = fil;

        for (int contador = 1; contador < s.length; contador++) {
            s[contador] = mejorColor(m, s, fil, minutero);
            fil = s[contador];
        }
        
        /*
        for (int fabricar = 0; fabricar < v.length; fabricar++) {
            v[fabricar] = mejorColor(m, v, fabricar, minutero);
        }
        */

    }
    
    public static int[] horario(double contador){
        int[] v = new int[2];
        int horas = 0, minutos = 0;
        while(contador>60){
            contador = contador-60;
            horas++;
        }
        minutos = (int) contador;
        v[0] = horas;
        v[1] = minutos;
        return v;
    }
    
    public static void imprimirResultados(int[] s, double[] minutero){
        int[] horario = horario((5*24*60) - minutero[0]);

        
        
        System.out.println("El tiempo de producción semanal de pintura es: " + horario[0] + " horas y " + horario[1] + " minutos " );
        
        horario = horario(minutero[0]);
        
        System.out.println("El tiempo total de disposición es: " + horario[0] + " horas y " + horario[1] + " minutos ");
        System.out.print("La secuencia de producción: ");
        
        for(int i = 0; i<s.length; i++){
            String cad = "";
            if (s[i] == 0){
                cad = "Blanco";
            } else if(s[i] == 1){
                cad = "Amarillo";
            } else if (s[i] == 2){
                cad = "Naranja";
            } else if (s[i] == 3){
                cad = "Rojo";
            } else if(s[i] == 4){
                cad = "Negro";
            }
            if (i == s.length-1)
                System.out.println(" " + cad);
            else
                System.out.print(" " + cad + " -");
        }
    }
    
    public static void insertarDatos(Graph g){
        g.insertVertex("Blanco");   // l0
        g.insertVertex("Amarillo"); // l1
        g.insertVertex("Naranja");  // l2
        g.insertVertex("Rojo");     // l3
        g.insertVertex("Negro");    // l4
        
        Iterator it = g.vertices();
        List<Vertex> l = new ArrayList<Vertex>();
        while(it.hasNext()){
            l.add((Vertex) it.next());
        }
        
        g.insertEdge(l.get(0), l.get(1), (double) 150);
        g.insertEdge(l.get(0), l.get(2), (double) 120);
        g.insertEdge(l.get(0), l.get(3), (double) 100);
        g.insertEdge(l.get(0), l.get(4), (double) 110);
        g.insertEdge(l.get(1), l.get(0), (double) 170);
        g.insertEdge(l.get(1), l.get(2), (double) 110);
        g.insertEdge(l.get(1), l.get(3), (double)90);
        g.insertEdge(l.get(1), l.get(4), (double) 100);
        g.insertEdge(l.get(2), l.get(0), (double) 200);
        g.insertEdge(l.get(2), l.get(1), (double) 170);
        g.insertEdge(l.get(2), l.get(3), (double) 80);
        g.insertEdge(l.get(2), l.get(4), (double) 100);
        g.insertEdge(l.get(3), l.get(0), (double) 220);
        g.insertEdge(l.get(3), l.get(1), (double) 190);
        g.insertEdge(l.get(3), l.get(2), (double) 100);
        g.insertEdge(l.get(3), l.get(4), (double) 90);
        g.insertEdge(l.get(4), l.get(0), (double) 300);
        g.insertEdge(l.get(4), l.get(1), (double) 210);
        g.insertEdge(l.get(4), l.get(2), (double) 180);
        g.insertEdge(l.get(4), l.get(3), (double) 130);
    }
    
}

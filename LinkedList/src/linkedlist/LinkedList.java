/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package linkedlist;

import java.util.Iterator;

/**
 *
 * @author labitson
 */
public class LinkedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Alumnito alumn1 = new Alumnito("Brianda", 25, "216578", 10.00);
        Alumnito alumn2 = new Alumnito("Leyen", 25, "216677", 10.00);
        Alumnito alumn3 = new Alumnito("Marco", 50, "002121", 9.00);
        Alumnito alumn4 = new Alumnito("Irma", 25, "235689", 10.00);
        Alumnito alumn5 = new Alumnito("Mateo", 23, "262847", 10.00);
        
        ListaEnlazada lista = new ListaEnlazada<Alumnito>();
        lista.append(alumn1);
        lista.append(alumn2);
        lista.append(alumn3);
        
        lista.insert(alumn4, 2);
        
        lista.removeObj(alumn2);
        
        System.out.println(lista.indexOf(alumn1));
        
        Iterator i = lista.iterator();
        
        System.out.println(lista.toString());
        
        
        System.out.println("Iterator: una sola vuelta");
        while (i.hasNext()) {
            System.out.println(i.next());
        }

        lista.irInicio();
        System.out.println("irInicio(): " + lista.getActual()); // Brianda (cabeza)
 
        lista.previous();
        System.out.println("previous() desde la cabeza: " + lista.getActual()); // Marco (cola)
 
        lista.irFinal();
        System.out.println("irFinal(): " + lista.getActual()); // Marco (cola)
 
        lista.next();
        System.out.println("next() desde la cola: " + lista.getActual()); // Brianda (cabeza)
 
        System.out.println("7 pasos con next() en una lista de 3 (da vueltas)");
        lista.irInicio();
        for (int k = 0; k < 7; k++) {
            System.out.println("paso " + k + ": " + lista.getActual());
            lista.next();
        }
 
        System.out.println("4 pasos con previous() (hacia atras, tambien da vueltas)");
        lista.irInicio();
        for (int k = 0; k < 4; k++) {
            System.out.println("paso " + k + ": " + lista.getActual());
            lista.previous();
        }
 
        System.out.println("Invertir");
        lista.invertir();
        System.out.println(lista);
 
        System.out.println("Concatenar");
        ListaEnlazada otra = new ListaEnlazada<Alumnito>();
        otra.append(alumn5);
        lista.concatenar(otra);
        System.out.println(lista);
 
        System.out.println("Remoce");
        System.out.println("remove(0) devolvio: " + lista.remove(0));
        System.out.println(lista);
 
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlist;

import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author labitson
 */
public class ListaEnlazada<T> implements IList<T>, Iterable<T> {

    protected NodoSimple<T> inicio;
    protected int nElementos;
    private Scanner sc;
    private NodoSimple<T> apuntador;

    public ListaEnlazada() {
        inicio = null;
        nElementos = 0;
    }

    private class NodoSimple<T> {

        private T dato;
        private NodoSimple<T> sig;
        private NodoSimple<T> anterior;

        public NodoSimple(T dato) {
            this.dato = dato;
        }

    }

    private class ListIterator<T> implements Iterator<T> {

        private NodoSimple<T> nodoActual;
        private int recorridos = 0;

        public ListIterator(NodoSimple<T> inicio) {
            nodoActual = inicio;
        }

        @Override
        public boolean hasNext() {
            return recorridos < nElementos;
        }

        @Override
        public T next() {
            T dato = nodoActual.dato;
            nodoActual = nodoActual.sig;
            recorridos++;

            return dato;
        }
    }

    @Override
    public void append(T elemento) throws ListException {
        NodoSimple<T> nodoNuevo = new NodoSimple<>(elemento);
        NodoSimple<T> nodo = inicio;

        if (nodo == null) {
            inicio = nodoNuevo;
            nodoNuevo.sig= nodoNuevo;
            nodoNuevo.anterior = nodoNuevo;
            apuntador = nodoNuevo;

        } else {

            while (nodo.sig != inicio) {
                nodo = nodo.sig;
            }

            nodo.sig = nodoNuevo;
            nodoNuevo.anterior = nodo;
            nodoNuevo.sig = inicio;
            inicio.anterior = nodoNuevo;

        }

        nElementos++;

    }

    @Override
    public void insert(T elemento, int index) throws ListException {
        NodoSimple<T> nodoNuevo = new NodoSimple<>(elemento);

        if (index < 0 || index > nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        if (index == 0) {
            if (inicio != null) {
                nodoNuevo.sig = inicio;
                NodoSimple<T> cola = inicio.anterior;
                nodoNuevo.anterior = cola;
                cola.sig = nodoNuevo;
                inicio.anterior = nodoNuevo;
            }else{
                nodoNuevo.sig = nodoNuevo;
                nodoNuevo.anterior = nodoNuevo;
                apuntador = nodoNuevo;
            }
            inicio = nodoNuevo;
        } else {
            NodoSimple<T> nodo = inicio;

            for (int j = 0; j < index - 1; j++) {
                nodo = nodo.sig;
            }

            nodoNuevo.sig = nodo.sig;
            
            nodoNuevo.anterior = nodo;
            nodo.sig.anterior = nodoNuevo;

            nodo.sig = nodoNuevo;
        }

        nElementos++;
    }

    @Override
    public T remove(int index) throws ListException {
        T removed;
        if (empty()) {
            throw new ListException("Lista vacia");
        }
        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        if (index == 0) {
            removed = inicio.dato;
            if(nElementos ==1){
                inicio = null;
                apuntador = null;
            }else{
                NodoSimple<T> cola = inicio.anterior;
                if(apuntador == inicio){
                    apuntador = inicio.sig;
                }
                inicio = inicio.sig;
                
                inicio.anterior = cola;
                cola.sig = inicio;
            }
            
        } else {
            NodoSimple<T> nodo = inicio;
            for (int j = 0; j < index - 1; j++) {
                nodo = nodo.sig;
            }
            removed = nodo.sig.dato;
            if(apuntador == nodo.sig){
                apuntador = nodo.sig.sig;
            }
            nodo.sig = nodo.sig.sig;
            nodo.sig.anterior = nodo;
        }
        nElementos--;
        return removed;
    }

    public void invertir() {
        
        if(empty()){
            return;
        }
        
        NodoSimple<T> nuevoInicio = inicio.anterior;
        
        NodoSimple<T> actual = inicio;
        
        for (int i = 0; i <nElementos; i++){
            NodoSimple<T> siguiente = actual.sig;
            
            actual.sig= actual.anterior;
            actual.anterior = siguiente;
            
            actual = siguiente;
        }

     
        inicio = nuevoInicio;
    }

    public void concatenar(ListaEnlazada<T> otraLista) {

        NodoSimple<T> nodo = otraLista.inicio;
        
        int n = otraLista.nElementos;
        
        for(int i = 0; i<n; i++){
            append(nodo.dato);
            nodo= nodo.sig;
        }
    }

    @Override
    public boolean removeObj(T elemento) throws ListException {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        int posicion = indexOf(elemento);
        if (posicion == -1){
            return false;
        }
        remove(posicion);
        return true;
     
    }

    @Override
    public int indexOf(T elemento) {
        NodoSimple<T> nodo = inicio;
        int index = 0;

        while (index < nElementos) {

            if (nodo.dato.equals(elemento)) {
                return index;
            }

            nodo = nodo.sig;
            index++;
        }

        return -1;
    }

    @Override
    public T get(int index) throws ListException {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        NodoSimple<T> nodo = inicio;
        for (int j = 0; j < index; j++) {
            nodo = nodo.sig;
        }
        return nodo.dato;
    }

    @Override
    public void set(T elemento, int index) throws ListException {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        NodoSimple<T> nodo = inicio;

        for (int j = 0; j < index; j++) {
            nodo = nodo.sig;
        }

        nodo.dato = elemento;
    }

    @Override
    public void clear() {
        inicio = null;
        apuntador = null;
        nElementos = 0;
    }

    @Override
    public boolean empty() {
        return inicio == null;
    }

    @Override
    public int size() {
        return nElementos;
    }
    
    public void irInicio() {
        apuntador = inicio;  // el apuntador va a la cabeza
    }
 
    public void irFinal() {
        if (!empty()) {
            apuntador = inicio.anterior;// la cola es el anterior de la cabeza
        }
    }
 
    public void next() {
        if (!empty()) {
            apuntador = apuntador.sig; // en la cola, sig es la cabeza: da la vuelta solo
        }
    }
 
    public void previous() {
        if (!empty()) {
            apuntador = apuntador.anterior;// en la cabeza, ant es la cola: da la vuelta solo
        }
    }
 
    public T getActual() throws ListException {
        if (empty()) {
            throw new ListException("Lista vacia");
        }
        return apuntador.dato;   // el dato donde esta parado el apuntador
    }

    @Override
    public String toString() {
        String s = "[";
        NodoSimple<T> nodo = inicio;
        int i = 0;
        
        while (i < nElementos) {
            s += nodo.dato.toString();
            if (nodo.sig != inicio) {
                s += ", ";
            }
            nodo = nodo.sig;
            i++;
        }
        s += "]";
        return s;
    }

//    @Override
//    public java.util.Iterator<T> Iterator() {
//        return new ListIterator(inicio);
//    }
    @Override
    public java.util.Iterator<T> iterator() {
        return new ListIterator(inicio);
    }

}

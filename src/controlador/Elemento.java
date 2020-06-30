package controlador;

import javafx.beans.property.SimpleStringProperty;

public class Elemento{
    private String Elemento;
    private String nodo1;
    private String nodo2;
    private String nodo3;
    private String nodo4;

    public Elemento(String elemento, String nodo1, String nodo2, String nodo3, String nodo4) {
        Elemento = elemento;
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
        this.nodo3 = nodo3;
        this.nodo4 = nodo4;
    }

    public void setElemento(String elemento) {
        Elemento = elemento;
    }

    public String getElemento() {
        return Elemento;
    }

    public void setNodo1(String nodo1) {
        this.nodo1 = nodo1;
    }

    public void setNodo2(String nodo2) {
        this.nodo2 = nodo2;
    }

    public void setNodo3(String nodo3) {
        this.nodo3 = nodo3;
    }

    public void setNodo4(String nodo4) {
        this.nodo4 = nodo4;
    }

    public String getNodo1() {
        return nodo1;
    }

    public String getNodo2() {
        return nodo2;
    }

    public String getNodo3() {
        return nodo3;
    }

    public String getNodo4() {
        return nodo4;
    }
}
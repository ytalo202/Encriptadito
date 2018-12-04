package com.example.yoshino.encriptadito;

public class Avc {

   private int index;
    private String letra;

    public Avc(String letra) {
        this.letra = letra;
    }

    public Avc(int index) {
        this.index = index;
    }

    public Avc(int index, String letra) {
        this.index = index;
        this.letra = letra;
    }

    public Avc() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}

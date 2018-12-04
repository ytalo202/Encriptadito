package com.example.yoshino.encriptadito;

import android.util.Log;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Metodos {

    public ArrayList<String> convertStringToArraylist(String str) {
        ArrayList<String> charList = new ArrayList<String>();
        for(int i = 0; i<str.length();i++){
            charList.add(String.valueOf(str.charAt(i)));
        }
        return charList;
    }

    public String parsearString(ArrayList<String> avc) {
        String listString = "";

        for (String s : avc)
        {
            listString = listString+s;
        }
        return listString;
    }

    public int contar(String cadena) {
        int contador = 0;
        char caracter = 0;
        while (cadena.length() != 0) { // mientras la cadena tenga algún carácter la recorremos
            int contadorAux = 0;
            for (int j = 0; j < cadena.length(); j++) { // recorremos la cadena para contar los caracteres del indice cero
                if (cadena.charAt(0) == cadena.charAt(j)) {
                    contadorAux++;
                }
            }
            if (contadorAux > contador) { // si el contador del nuevo caracter es mayor al que ya estaba guardado, lo cambiamos
                contador = contadorAux;
                caracter = cadena.charAt(0);
            }
            // borramos los carácteres contados para ahorrar entrar mas veces para contarlos otra vez
            cadena = cadena.replaceAll(cadena.charAt(0) + "", "");
        }
        return contador;
    }


    public int buscarLetra(String letra, ArrayList<String> avc) {
        int indice = 0;
        for (int i = 0; i < avc.size(); i++) {
            if (avc.get(i).equals(letra)) {
                indice = i;
            }
        }
        return indice;
    }

    public ArrayList<String> empezarDesde(ArrayList<String> avc,String letra){
        ArrayList<String> lista = new ArrayList<>();
        lista.addAll(avc);
        int indice=0;
        for(int i=0;i<lista.size();i++) {
            if (lista.get(i).equals(letra)) {
                indice = i;
            }
        }
        int salto = lista.size() - indice;
        Collections.rotate(lista, salto);
       return lista;
    }

    public String getStringListCharacters(List<Character> list)
    {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }


    public List<Character> asList(final String string) {
        return new AbstractList<Character>() {
            public int size() { return string.length(); }
            public Character get(int index) { return string.charAt(index); }
        };
    }


    public ArrayList<String> parsearList(ArrayList<Avc> avc) {
        ArrayList<String> newList = new ArrayList<>();

        for (int i = 0; i < avc.size(); i++) {
            newList.add(avc.get(i).getLetra());
        }

        return newList;
    }


    public ArrayList<Avc> doTrama(ArrayList<String> avcEntra, String clave, boolean isAsc) {

        ArrayList<Avc> avcSale = new ArrayList<>();
        String p = clave;
        char[] l = p.toCharArray();
        int g = 0;
        int h = 0;
        int so = avcEntra.size() % l.length;
        int grupos = avcEntra.size() / l.length;

        for (int i = 0; i < avcEntra.size(); i++) {

            if (so != 0 && i >= avcEntra.size() - so) {
                avcSale.add(new Avc(avcEntra.get(i)));
            } else {
                avcSale.add(new Avc(l[g], avcEntra.get(i)));
                h++;
                if (h == grupos) {
                    g++;
                    h = 0;
                }
                if (isAsc) {
                    Collections.sort(avcSale, new SortbyAsc());
                } else {
                    Collections.sort(avcSale, new SortbyDes());
                }
            }
        }
        return avcSale;
    }


    public ArrayList<Avc> doTransposicion(ArrayList<String> avcEntra, String clave, boolean isAsc) {

        ArrayList<Avc> avcSale  = new ArrayList<>();
        ArrayList<Avc> avcNew  = new ArrayList<>();
        List<Avc> avcMedio  = new ArrayList<>();
        String p = clave;
        char[] l = p.toCharArray();
        int g = 0;

        for (int i = 0; i < avcEntra.size(); i++) {


            avcNew.add(new Avc(l[g], avcEntra.get(i)));
            g++;
            if (g == l.length || i + 1 == avcEntra.size()) {


                avcMedio = avcNew.subList(Math.abs(g - i - 1), i + 1);
                if (isAsc){
                    Collections.sort(avcMedio, new SortbyAsc());
                }
                else {
                    Collections.sort(avcMedio, new SortbyDes());
                }
                avcSale.addAll(avcMedio);
                g = 0;
            }
        }
        return avcSale;
    }
}

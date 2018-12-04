package com.example.yoshino.encriptadito;

import java.util.Comparator;

public class SortbyDes implements Comparator<Avc> {
    public int compare(Avc a, Avc b) {

        return  new Integer(b.getIndex()).compareTo(new Integer(a.getIndex()));
    }
}

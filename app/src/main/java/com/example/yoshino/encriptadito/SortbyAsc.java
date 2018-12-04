package com.example.yoshino.encriptadito;

import java.util.Comparator;

public class SortbyAsc implements Comparator<Avc> {
    public int compare(Avc a, Avc b) {
        return a.getIndex() - b.getIndex();
    }
}

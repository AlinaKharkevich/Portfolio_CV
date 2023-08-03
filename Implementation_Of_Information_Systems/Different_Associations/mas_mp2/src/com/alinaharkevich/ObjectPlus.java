package com.alinaharkevich;

import java.io.Serializable;
import java.util.*;

abstract class ObjectPlus {
    private static Set<ObjectPlus> extent = new HashSet<>();

    public ObjectPlus() {
        extent.add(this);
    }

    public static Set<ObjectPlus> getExtent() {
        return extent;
    }

    public static void showExtent() {
        for (ObjectPlus object : extent) {
            System.out.println(object);
        }
    }
}



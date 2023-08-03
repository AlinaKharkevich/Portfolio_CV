package com.alinaharkevich;

import java.util.*;

public class ObjectPlus {
    private static Map<Class<?>, List<Object>> extent = new HashMap<>();

    protected void addFromExtent() {
        List<Object> list = extent.get(this.getClass());
        if (list == null) {
            list = new ArrayList<>();
            extent.put(this.getClass(), list);
        }
        list.add(this);
    }

    protected void removeFromExtent() {
        List<Object> list = extent.get(this.getClass());
        if (list == null) {
            return;
        }
        list.remove(this);
    }

    public static <E> List<E> getExtent(Class<E> clazz) {
        List<E> list = (List<E>) extent.get(clazz);
        if (list == null) {
            list = new ArrayList<>();
        }
        return Collections.unmodifiableList(list);
    }
}
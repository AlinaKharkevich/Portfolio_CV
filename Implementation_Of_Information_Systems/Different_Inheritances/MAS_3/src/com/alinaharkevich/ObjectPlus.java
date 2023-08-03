package com.alinaharkevich;

import java.util.*;

public class ObjectPlus {
    //Dynemic inheritance
    private static Map<Class<?>, List<Object>> extent = new HashMap<>();
    protected Map<Object, Object> associations = new HashMap<>();

    protected void addFromExtent(){
        List list = extent.get(this.getClass());
        if(list == null){
            list = new ArrayList();
            extent.put(this.getClass(),list);
        }
        list.add(this);
    }

    protected void removeFromExtent(){
        List list = extent.get(this.getClass());
        if(list == null){
            return;
        }
        list.remove(this);
    }

    public static <E> List<E> getExtent(Class<E> e){
        List<E> list = (List<E>) extent.get(e);
        if(list == null){
            list = new ArrayList<>();
        }
        return Collections.unmodifiableList(list);
    }
}


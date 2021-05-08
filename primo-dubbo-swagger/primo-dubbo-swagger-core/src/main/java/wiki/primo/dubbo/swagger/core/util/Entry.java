/*
 * primo.wiki
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.core.util;

/**
 * @author chenhx
 * @version Entry.java, v 0.1 2019-08-19 17:28 chenhx
 */
public class Entry<K, V> {
    private K key;
    private V value;

    public Entry() {
    }

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(key) + ": " + String.valueOf(value);
    }

    public boolean isNullValue() {
        return key == null;
    }
}

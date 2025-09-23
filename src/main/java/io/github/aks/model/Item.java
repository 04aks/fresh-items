package io.github.aks.model;


public abstract class Item{
    int items = 0;

    public int getItems() {
        return items;
    }

    public void addItem() {
        items++;
    }
}

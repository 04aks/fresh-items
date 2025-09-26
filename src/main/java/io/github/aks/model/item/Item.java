package io.github.aks.model.item;


public abstract class Item{
    int items = 0;

    public int getItems() {
        return items;
    }

    public void addItem() {
        items++;
    }
}

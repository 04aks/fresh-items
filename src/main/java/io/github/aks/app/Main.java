package io.github.aks.app;

import io.github.aks.facade.FreshItems;
import io.github.aks.ui.Window;

public class Main {
    public static void main(String[] args) {
        FreshItems freshItems = new FreshItems();
        Window window = new Window(freshItems);
    }
}
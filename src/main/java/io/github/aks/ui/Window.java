package io.github.aks.ui;

import io.github.aks.facade.FreshItems;

import javax.swing.*;

public class Window extends JFrame {
    public Window(FreshItems freshItems){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Fresh Items");

        AppPanel hp = new AppPanel(freshItems);
        add(hp);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}

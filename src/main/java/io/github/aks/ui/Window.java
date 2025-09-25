package io.github.aks.ui;

import javax.swing.*;

public class Window extends JFrame {
    public Window(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Fresh Items");

        AppPanel hp = new AppPanel();
        add(hp);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}

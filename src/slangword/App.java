package slangword;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class App extends JFrame {
    SlangWord slangWord;
    private JPanel appPanel;
    private JButton btnSearchSlang;
    private JButton btnSearchDefinition;
    private JButton btnShowHistory;
    private JButton btnAddOne;
    private JButton btnEditOne;


    public App() {
        btnSearchSlang.setSize(10, 100);
        btnSearchSlang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slangWord = SlangWord.getInstance();
                System.out.println(slangWord.getAllKeyword());
            }
        });
    }

    public static void main(String[] args) {
        App app = new App();
        app.setContentPane(app.appPanel);
        app.setTitle("Slang word application");
        app.setSize(800, 450);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

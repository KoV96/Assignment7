package com.shpp.p2p.cs.okurylyk.assignment7.namesurfer.test;

import com.shpp.cs.a.simple.SimpleProgram;
import com.shpp.p2p.cs.okurylyk.assignment7.namesurfer.NameSurferConstants;
import com.shpp.p2p.cs.okurylyk.assignment7.namesurfer.NameSurferDataBase;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TestDataBase extends SimpleProgram implements NameSurferConstants {
    private JButton graphButton;
    private JTextField nameField;
    private final NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        graphButton = new JButton("Graph");
        JButton clearButton = new JButton("Clear");
        nameField = new JTextField(20);
        JLabel name = new JLabel("Name: ");

        add(name, NORTH);
        add(nameField, NORTH);
        add(graphButton, NORTH);
        add(clearButton, NORTH);

        nameField.addActionListener(this);
        graphButton.addActionListener(this);
        clearButton.addActionListener(this);

        addActionListeners();
    }

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nameField || e.getSource() == graphButton) {
            println("Graph: " + dataBase.findEntry(nameField.getText()));
        } else
            println("Clear");
    }
}

package com.shpp.p2p.cs.okurylyk.assignment7.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class
NameSurfer extends SimpleProgram implements NameSurferConstants {
    // Button that will build a graph
    private JButton graphButton;
    // Text field for name input
    private JTextField nameField;
    // Object of class NameSurferGraph which is responsible for graph building
    private NameSurferGraph graph;
    // Object of class NameSurferDataBase which is responsible for file reading and data saving
    private final NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);

    /* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        graphButton = new JButton("Graph");
        JButton clearButton = new JButton("Clear");
        nameField = new JTextField(20);
        JLabel name = new JLabel("Name: ");
        graph = new NameSurferGraph();

        add(name, NORTH);
        add(nameField, NORTH);
        add(graphButton, NORTH);
        add(clearButton, NORTH);
        add(graph);

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
        NameSurferEntry entry = dataBase.findEntry(nameField.getText());
        if (entry != null) {
            if (e.getSource() == nameField || e.getSource() == graphButton) {
                graph.addEntry(entry);
                graph.update();
            } else
                graph.clear();
        }
    }
}

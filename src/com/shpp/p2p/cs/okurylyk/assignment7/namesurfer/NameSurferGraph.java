package com.shpp.p2p.cs.okurylyk.assignment7.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private final ArrayList<NameSurferEntry> namesEntry;
    private static final Color BACK_COLOR = new Color(237, 249, 210);

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        namesEntry = new ArrayList<>();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        namesEntry.clear();
        update();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        namesEntry.add(entry);
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        setBackground(BACK_COLOR);
        buildGraph();
        for (int i = 0; i < namesEntry.size(); i++) {
            buildEntry(namesEntry.get(i), colorSetter(i + 1));
        }
    }

    /**
     * Starting build graph with entry params. Decade by decade adding on the screen labels and lines.
     *
     * @param entry Object of NameSurferEntry class. It includes all params of data with input name.
     * @param color Needs color that must change 4 times.
     */
    private void buildEntry(NameSurferEntry entry, Color color) {
        for (int i = 0; i < NDECADES; i++) {
            addLabels(i, entry, color);
            if (i != NDECADES - 1) {
                addLines(i, entry, color);
            }
        }
    }

    /**
     * This method is adding lines to the screen. Each line is building from one rank to the next.
     *
     * @param i     Current number of a loop which is represented decades.
     * @param entry Object of NameSurferEntry class. It includes all params of data with input name.
     * @param color Generated color according to the method colorSetter() from method update().
     */
    private void addLines(int i, NameSurferEntry entry, Color color) {
        double x1 = i * (getWidth() / (double) (NDECADES));
        double x2 = (i + 1) * (getWidth() / (double) (NDECADES));
        double y1 = getVertical(entry.getRank(i + 1));
        double y2 = getVertical(entry.getRank(i + 2));
        if (i == 0){
            addGraphPoint(x1, y1, color);
        }
        GLine graphLine = new GLine(x1, y1, x2, y2);
        graphLine.setColor(color);
        add(graphLine);
        addGraphPoint(x2, y2, color);
    }

    /**
     * Method that making graph point in needs coordinates.
     * @param x X coordinate of the point.
     * @param y Y coordinate of the point.
     * @param color Needed color of the point.
     */
    private void addGraphPoint(double x, double y, Color color) {
        double diameter = (getWidth() + getHeight()) /(double)(APPLICATION_HEIGHT / 2);
        GOval point = new GOval(x - (diameter / 2.0), y - (diameter / 2.0), diameter, diameter);
        point.setFilled(true);
        point.setColor(color);
        if (y != getHeight() - GRAPH_MARGIN_SIZE)
        add(point);
    }

    /**
     * This method is adding labels with name in right position on the graph.
     *
     * @param i     Current number of a loop which is represented decades.
     * @param entry Object of NameSurferEntry class. It includes all params of data with input name.
     * @param color Generated color according to the method colorSetter() from method update().
     */
    private void addLabels(int i, NameSurferEntry entry, Color color) {
        String labelName = createName(entry, i);
        double x = i * (getWidth() / (double) (NDECADES));
        double xAlignment = 10; // I made this cause if I add a point it will blocked Label.
        GLabel name = new GLabel(labelName, x + xAlignment, getVertical(entry.getRank(i + 1)));
        name.setColor(color);
        name.setFont(new Font("Cambria", Font.PLAIN, ((getHeight() + getWidth()) / 120)));
        add(name);
    }

    /**
     * This method creating name of label that will be on the screen. If rank is 0, label will be not showing rank
     * but will something like "*".
     *
     * @param entry Object of NameSurferEntry class. It includes all params of data with input name.
     * @param i     Current number of a loop which is represented decades.
     * @return Name of Label.
     */
    private String createName(NameSurferEntry entry, int i) {
        String name;
        if (entry.getRank(i + 1) == 0) {
            name = entry.getName() + " *";
        } else
            name = entry.getName() + " " + entry.getRank(i + 1);
        return name;
    }

    /**
     * Getting vertical coordinate Y with according to the current graph window size and max value of rank.
     *
     * @param rank Current rank
     * @return Y coordinate.
     */
    private double getVertical(int rank) {
        double graphHeight, y, yRel;
        yRel = ((double) (rank) / MAX_RANK);
        graphHeight = (getHeight() - (2 * GRAPH_MARGIN_SIZE));
        if (rank != 0) {
            y = yRel * graphHeight;
            y += GRAPH_MARGIN_SIZE;
        } else
            y = getHeight() - GRAPH_MARGIN_SIZE;
        return y;
    }

    /**
     * Set right color according to the task.
     *
     * @param i Number of entry object.
     * @return Needs color.
     */
    private Color colorSetter(int i) {
        if (i % 4 == 1) {
            return Color.BLUE;
        } else if (i % 4 == 2) {
            return Color.RED;
        } else if (i % 4 == 3) {
            return Color.MAGENTA;
        } else
            return Color.BLACK;
    }

    /**
     * This method is building graph window.
     */
    private void buildGraph() {
        buildVerticalLines();
        buildHorizontalLines();
    }

    /**
     * Builds two horizontal lines in top and bottom of the window.
     */
    private void buildHorizontalLines() {
        GLine topHorLine = new GLine(0, (GRAPH_MARGIN_SIZE), getWidth(), GRAPH_MARGIN_SIZE);
        GLine bottomHorLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE,
                getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
        add(topHorLine);
        add(bottomHorLine);

    }

    /**
     * Builds vertical lines in graph window, which are dividing them to the column according to each decade. And
     * also adding a labels with  decades name.
     */
    private void buildVerticalLines() {
        int colLength = getWidth() / NDECADES;
        double x = 0;
        for (int i = 0; i < NDECADES; i++) {
            GLine verticalLine = new GLine(x, 0, x, getHeight());
            add(verticalLine);
            GLabel year = new GLabel(makeYear(i), x, getHeight() - getAlignmentY());
            year.setFont(new Font("MV Boli", Font.PLAIN, 20));
            add(year);
            x += colLength;
        }
    }

    /**
     * Making a year for label name. Start position is 1900 and last is 2010.
     *
     * @param i Current decade.
     * @return String with needs year value.
     */
    private String makeYear(int i) {
        String result;
        if (i == 0) {
            return "1900";
        } else if (i == 10) {
            return "2000";
        }
        if (i < 10) {
            result = "19" + (i * 10);
        } else {
            result = "20" + (i - 1);
        }
        return result;
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}

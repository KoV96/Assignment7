package com.shpp.p2p.cs.okurylyk.assignment7.namesurfer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */


public class NameSurferEntry implements NameSurferConstants {
    // Name according to the file
    private final String name;
    // Ranks of current name by decades
    public int[] ranks = new int[NDECADES];

    /* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String[] lineArr = line.split(" ");
        name = lineArr[0];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = Integer.parseInt(lineArr[i + 1]);
        }
    }

    /* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        if (decade < 1) {
            return 0;
        } else
            return ranks[decade - 1];
    }

    /* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        StringBuilder result = new StringBuilder(name + " [");
        for (int i = 0; i < ranks.length; i++) {
            if (i != ranks.length - 1) {
                result.append(ranks[i]).append(" ");
            } else {
                result.append(ranks[i]).append("]");
            }
        }
        return result.toString();
    }
}


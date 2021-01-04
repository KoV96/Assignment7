package com.shpp.p2p.cs.okurylyk.assignment7.namesurfer.test;

import com.shpp.cs.a.simple.SimpleProgram;
import com.shpp.p2p.cs.okurylyk.assignment7.namesurfer.NameSurferEntry;

public class TestEntry extends SimpleProgram {

    public void run() {
        String testLine = "Sam 58 69 99 131 168 236 278 380 467 408 466 997";
        NameSurferEntry testResult = new NameSurferEntry(testLine);

        println("getName: " + testResult.getName());
        println("getRank: " + testResult.getRank(12));
        println("toString: " + testResult.toString());
    }
}

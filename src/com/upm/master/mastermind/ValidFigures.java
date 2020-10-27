package com.upm.master.mastermind;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 * Set of figures that will be valid for one execution of our MasterMind
 */
public class ValidFigures {
   private static final int N_FIGURES = 6;
   private Vector<Character> figures = new Vector<>();

   ValidFigures() {
   }

   ValidFigures add(Character cc) { figures.add(cc); return this; }
   boolean isCompleted() { return  figures.size() == N_FIGURES; }
   boolean contains(Character cc) { return figures.contains(cc); }
   int size() { return figures.size(); }
   Character at(int pos) { return figures.elementAt(pos); }
}

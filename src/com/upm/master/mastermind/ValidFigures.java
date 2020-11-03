package com.upm.master.mastermind;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 * Set of figures that will be valid for one execution of our MasterMind
 */
public class ValidFigures {
   private Vector<Character> figures = new Vector<>();

   public ValidFigures() {
   }

   public ValidFigures add(Character cc) { figures.add(cc); return this; }
   public boolean contains(Character cc) { return figures.contains(cc); }
   public int size() { return figures.size(); }
   public Character at(int pos) { return figures.elementAt(pos); }
}

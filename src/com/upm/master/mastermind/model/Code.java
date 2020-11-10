package com.upm.master.mastermind.model;

import java.util.Vector;

public final class Code {
   public static final class Builder {
      private Vector<Character> figures = new Vector<>(2);
      public Builder add(Character cc) { figures.add(cc); return this; }
      public int size() { return figures.size(); }
      public Code build() { return new Code(figures); }
   };

   private Vector<Character> figures;

   Code(Vector<Character> _figures) {
      this.figures = _figures;
   }
   public int size() { return figures.size(); }
   public Character figureAt(int pos) { return figures.elementAt(pos); }

   @Override
   public String toString() {
      return "Code{" + "figures=" + figures + '}';
   }

   @Override
   public int hashCode() {
      StringBuilder builder = new StringBuilder();
      for (Character cc : figures) {  builder.append(cc); }
      return builder.toString().hashCode();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Code code = (Code) o;

      return figures.equals(code.figures);
   }
}

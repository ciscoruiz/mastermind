package com.upm.master.mastermind.model;

public class Configuration {
   private static final int N_FIGURES = 6;
   private int maxAttempt;
   private int maxPlay;
   private ValidFigures validFigures;

   public int getMaxAttempt() {
      return maxAttempt;
   }

   public void setMaxAttempt(int maxAttempt) {
      this.maxAttempt = maxAttempt;
   }

   public ValidFigures getValidFigures() {
      return validFigures;
   }

   public void setValidFigures(ValidFigures validFigures) {
      if (validFigures.size() != N_FIGURES) {
         throw new RuntimeException("ValidFigures requires " + N_FIGURES + " to be valid");
      }
      this.validFigures = validFigures;
   }
}

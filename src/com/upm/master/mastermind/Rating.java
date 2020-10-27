package com.upm.master.mastermind;

public final class Rating {
   private int breaker;
   private int maker;

   Rating() { breaker = maker = 0; }

   public int getBreaker() {
      return breaker;
   }

   public int getMaker() {
      return maker;
   }

   public void playForBreaker() { breaker = 1; }
   public void playForMaker() { maker = 1; }

   public void sum(Rating other) {
      breaker += other.breaker;
      maker += other.maker;
   }
}

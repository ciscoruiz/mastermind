package com.upm.master.mastermind;

public final class Rating {
   private int breaker;
   private int maker;

   public Rating() { breaker = maker = 0; }

   public int getBreaker() {
      return breaker;
   }

   public int getMaker() {
      return maker;
   }

   public void setBreakerWins() { breaker = 1; }
   public void setMakerWins() { maker = 1; }

   public void summarize(Rating other) {
      breaker += other.breaker;
      maker += other.maker;
   }
}

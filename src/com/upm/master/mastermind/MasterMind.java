package com.upm.master.mastermind;

public final class MasterMind {
   Configuration configuration;
   int nplay = 0;
   Rating rating = new Rating();

   public MasterMind(Configuration configuration) {
      this.configuration = configuration;
   }
   public boolean continuePlaying() { return nplay < configuration.getMaxPlay(); }
   public void summarize(Rating rating) {  this.rating.summarize(rating);  nplay ++; }
   public int getNplay() {  return nplay;  }
   public int getMaxPlay() { return configuration.getMaxPlay(); }
   public Rating getRating() {  return rating; }
}

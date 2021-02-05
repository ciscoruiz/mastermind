package com.upm.master.mastermind.model;

public final class MasterMind {
   Configuration configuration;
   Rating rating = new Rating();

   public MasterMind(Configuration configuration) {
      this.configuration = configuration;
   }
   public void annotateResult(Rating rating) {  this.rating.summarize(rating); }
   public Rating getRating() {  return rating; }
}

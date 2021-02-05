package com.upm.master.mastermind.model;

public class Game {
   static final public int FIGURES_TO_GUESS = 4;
   private final Configuration configuration;
   private int attempt = 0;
   private CodeMaker codeMaker;

   public Game(Configuration configuration) {
      this.configuration = configuration;
      this.codeMaker = new CodeMaker(configuration);
      this.codeMaker.generateSecretCode();
   }

   public boolean breakerCanRetry() { ++ attempt; return attempt < configuration.getMaxAttempt(); }

   public Response evaluate(Code guessCode) {
      return codeMaker.evaluate(guessCode);
   }

   public Code getSecretCode() {
      return codeMaker.getCode();
   }

   public int getAttempt() {
      return attempt;
   }

   public int getMaxAttempt() {
      return configuration.getMaxAttempt();
   }

   public ValidFigures getValidFigures() {
      return configuration.getValidFigures();
   }
}

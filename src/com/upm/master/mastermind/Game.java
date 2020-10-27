package com.upm.master.mastermind;

public class Game {
   static final public int FIGURES_TO_GUESS = 4;
   private final MasterMind masterMind;

   public Game(MasterMind masterMind) {
      this.masterMind = masterMind;
   }
   public ValidFigures getValidFigures() {
      return masterMind.validFigures;
   }
   public Visualizer getVisualizer() { return masterMind.visualizer; }
   public Reader getReader() { return masterMind.reader; }
   public int getMaxAttempt() {  return masterMind.maxAttempt; }

   Rating play() {
      CodeMaker codeMaker = new CodeMaker(this);
      CodeBreaker codeBreaker = new CodeBreaker(this);
      Rating result = new Rating();

      int attempt = 0;
      while (++ attempt < masterMind.maxAttempt) {
         Code code = codeBreaker.play(attempt);
         Response response = codeMaker.evaluate(code);

         if (response.codeWasBroken()) {
            result.playForBreaker();
            masterMind.visualizer.breakerWins();
            return result;
         }

         codeBreaker.store(code, response);
         masterMind.visualizer.codeAndResponse(code, response);
      }

      result.playForMaker();
      masterMind.visualizer.makerWins();

      return result;
   }
}

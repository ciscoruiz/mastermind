package com.upm.master.mastermind;

public final class TtyVisualizer implements Visualizer {
   @Override
   public void startPlay(int nplay, int maxPlay) {
      System.out.println("Start play number " + nplay + " of " + maxPlay);
   }

   @Override
   public void makerWins() {
      System.out.println("CodeMaker WINS!!");
   }

   @Override
   public void breakerWins() {
      System.out.println("CodeBreaker WINS!!");
   }

   @Override
   public void codeAndResponse(Code code, Response response) {
      System.out.println(code.toString() + " -> " + response.toString());
   }

   @Override
   public void summary(Rating rating) {
      System.out.println("------- SUMMARY --------");
      System.out.println("Play(s) for Breaker=" + rating.getBreaker());
      System.out.println("Play(s) for Maker=" + rating.getMaker());
   }

   @Override
   public void askNextPlay() {
      System.out.println("Do you want to start a new game (Y/N)?");
   }

   @Override
   public void codeToBreak(Code code) {
      System.out.println("Code to break: " + code.toString());
   }

   @Override
   public void askCharacter(ValidFigures validFigures) {
      System.out.print("   Press key for one of character on the list { ");
      for (int ii = 0; ii < validFigures.size(); ++ ii) {
         System.out.print(validFigures.at(ii));
         System.out.print(" ");
      }
      System.out.println("}");
   }

   @Override
   public void notValid(Character character) {
      System.out.println("Character " + character + " is not a valid selection");
   }

   @Override
   public void askCode(int figuresToGuess, int attempt, int maxAttempt) {
      System.out.println("--- Choose " + figuresToGuess + " figures to try to guess code. Attempt " + attempt + " of " + maxAttempt);
   }
}

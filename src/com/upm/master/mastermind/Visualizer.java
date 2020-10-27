package com.upm.master.mastermind;

public interface Visualizer {
   void startPlay(int nplay, int maxPlay);
   void makerWins();
   void breakerWins();
   void codeAndResponse(Code code, Response response);
   void summary(Rating rating);
   void askNextPlay();
   void codeToBreak(Code code);
   void askCharacter(ValidFigures validFigures);
   void notValid(Character result);
   void askCode(int figuresToGuess, int attempt, int maxAttempt);
}

package com.upm.master.mastermind.model;

import java.util.HashSet;

public final class CodeMaker {
   private final Configuration configuration;
   private Code code = null;
   private HashSet<Character> figuresWithoutRepetition = new HashSet<Character>();

   public CodeMaker(Configuration configuration) {
      this.configuration = configuration;
   }

   void generateSecretCode() {
      Code.Builder builder = new Code.Builder();

      ValidFigures validFigures = configuration.getValidFigures();

      for (int ii = 0; ii < Game.FIGURES_TO_GUESS; ++ ii) {
         int pos = (int) (Math.random() * (validFigures.size()));
         builder.add(validFigures.at(pos));
      }
      code = builder.build();

      for(int ii = 0; ii < code.size(); ++ ii) {
         figuresWithoutRepetition.add(code.figureAt(ii));
      }
   }

   Response evaluate(Code guess) {
      Response result = new Response(code.size());

      if (code.size() != guess.size()) {
         return result;
      }

      for (int ii = 0; ii < guess.size(); ++ ii) {
         Character guessFigure = guess.figureAt(ii);

         if (code.figureAt(ii) == guessFigure) {
            result.putBlack(ii);
         }
         else if (figuresWithoutRepetition.contains(guessFigure)) {
            result.putWhite(ii);
         }
      }

      return result;
   }

   public Code getCode() {
      return code;
   }
}

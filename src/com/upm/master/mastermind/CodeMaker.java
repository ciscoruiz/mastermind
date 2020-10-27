package com.upm.master.mastermind;

import java.util.HashSet;

public class CodeMaker {
   private Code code;
   private HashSet<Character> figuresWithoutRepetition;

   public CodeMaker(Game game) {
      Code.Builder builder = new Code.Builder();

      ValidFigures validFigures = game.getValidFigures();

      for (int ii = 0; ii < Game.FIGURES_TO_GUESS; ++ ii) {
         int pos = (int) (Math.random() * (validFigures.size()));
         builder.add(validFigures.at(pos));
      }
      code = builder.build();
      game.getVisualizer().codeToBreak(code);

      figuresWithoutRepetition = new HashSet<Character>();
      for(int ii = 0; ii < code.size(); ++ ii) {
         figuresWithoutRepetition.add(code.figureAt(ii));
      }
   }

   public Response evaluate(Code guess) {
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
}

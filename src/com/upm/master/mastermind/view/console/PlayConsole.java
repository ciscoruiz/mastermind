package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.Response;
import com.upm.master.mastermind.model.ValidFigures;

public class PlayConsole {
   private CharacterReader reader = new CharacterReader();

   public void update(PlayController playController) {
      while (playController.continuePlaying()) {
         Code guessCode = askGuessCode(playController);
         Response response = playController.evaluate(guessCode);

         showResponse(guessCode, response);

         if (response.codeWasBroken()) {
            playController.codeBreakerWins();
            System.out.println("CodeBreaker WINS!!");
            return;
         }
      }

      System.out.println("CodeMaker WINS!!");
      playController.codeMakerWins();
   }

   private void showResponse(Code code, Response response) {
      System.out.println(code.toString() + " -> " + response.toString());
   }

   private Code askGuessCode(PlayController playController) {
      System.out.println(
         "--- Choose " + Game.FIGURES_TO_GUESS + " figures to try to guess code. Attempt " +
         playController.getAttempt() + " of " + playController.getMaxAttempt()
      );

      Code.Builder builder = new Code.Builder();
      while (builder.size() < Game.FIGURES_TO_GUESS) {
         builder.add(readValidFigure(playController));
      }
      return builder.build();
   }

   private Character readValidFigure(PlayController playController) {
      Character result;
      ValidFigures validFigures = playController.getValidFigures();

      while (true) {
         printValidFigures(validFigures);

         if ((result = reader.apply()) == null)
            continue;

         if (validFigures.contains(result)) {
            break;
         }

         System.out.println("Character " + result + " is not a valid selection");
      }

      return result;
   }

   private void printValidFigures(ValidFigures validFigures) {
      System.out.print("   Press key for one of character on the list { ");
      for (int ii = 0; ii < validFigures.size(); ++ ii) {
         System.out.print(validFigures.at(ii));
         System.out.print(" ");
      }
      System.out.println("}");
   }
}

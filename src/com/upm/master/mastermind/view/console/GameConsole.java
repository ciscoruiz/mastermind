package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.Response;
import com.upm.master.mastermind.model.ValidFigures;
import com.upm.master.mastermind.view.GameView;

public class GameConsole implements GameView {
   CharacterReader reader = new CharacterReader();

   @Override
   public void showSecretCode(Game game) {
      System.out.println("Code to break: " + game.getSecretCode().toString());
   }

   @Override
   public void breakerWins(Game game) {
      System.out.println("CodeBreaker WINS!!");
   }

   @Override
   public void makerWins(Game game) {
      System.out.println("CodeMaker WINS!!");
   }

   @Override
   public Code askGuessCode(Game game) {
      System.out.println(
         "--- Choose " + Game.FIGURES_TO_GUESS + " figures to try to guess code. Attempt " +
         game.getAttempt() + " of " + game.getMaxAttempt()
      );

      Code.Builder builder = new Code.Builder();
      while (builder.size() < Game.FIGURES_TO_GUESS) {
         builder.add(readValidFigure(game));
      }
      return builder.build();
   }

   @Override
   public void showResponse(Code code, Response response) {
      System.out.println(code.toString() + " -> " + response.toString());
   }

   private void printValidFigures(ValidFigures validFigures) {
      System.out.print("   Press key for one of character on the list { ");
      for (int ii = 0; ii < validFigures.size(); ++ ii) {
         System.out.print(validFigures.at(ii));
         System.out.print(" ");
      }
      System.out.println("}");
   }

   private Character readValidFigure(Game game) {
      Character result;
      ValidFigures validFigures = game.getValidFigures();

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
}

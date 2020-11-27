package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.Response;
import com.upm.master.mastermind.model.ValidFigures;
import com.upm.master.mastermind.view.console.menu.*;

import java.util.Vector;

public class PlayConsole {
   private final CharacterReader reader = new CharacterReader();
   private final MenuConsole menuConsole = new MenuConsole();
   private Command undoCommand;
   private boolean quit = false;

   PlayConsole() {
      menuConsole.add(new UndoCommand(this));
      menuConsole.add(new RedoCommand(this));
      menuConsole.add(new PlayCommand(this));
      menuConsole.add(new QuitCommand(this));
   }

   public void update(PlayController playController) {
      while (playController.continuePlaying() && !quit) {
         menuConsole.choose(playController);
      }

      System.out.println("CodeMaker WINS!!");
      playController.codeMakerWins();
   }

   public void play(PlayController playController) {
      playController.registry();

      Code guessCode = askGuessCode(playController);
      Response response = playController.evaluate(guessCode);

      showResponse(guessCode, response);

      if (response.codeWasBroken()) {
         playController.codeBreakerWins();
         System.out.println("CodeBreaker WINS!!");
      }
   }

   public void undo(PlayController playController) {
      playController.undo();
      System.out.println(
         "--- UNDO to Attempt " + playController.getAttempt() + " of " + playController.getMaxAttempt()
      );
   }

   public void redo(PlayController playController) {
      playController.redo();
      System.out.println(
         "--- REDO to Attempt " + playController.getAttempt() + " of " + playController.getMaxAttempt()
      );
   }

   public void quit() {
      quit = true;
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

      Vector<Character> figures = playController.getValidFigures().getFigures();

      while (builder.size() < Game.FIGURES_TO_GUESS) {
         builder.add(reader.apply("Press key for one character", figures));
      }
      return builder.build();
   }
}

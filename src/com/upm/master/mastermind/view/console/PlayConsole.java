package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.controller.model.PlayModelController;
import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.Response;
import com.upm.master.mastermind.view.console.menu.*;

import java.util.Vector;

public class PlayConsole {
   private final CharacterReader reader = new CharacterReader();
   private final MenuConsole menuConsole = new MenuConsole();
   private Command undoCommand;

   PlayConsole() {
      menuConsole.add(new UndoCommand(this));
      menuConsole.add(new RedoCommand(this));
      menuConsole.add(new PlayCommand(this));
      menuConsole.add(new QuitCommand(this));
   }

   public void update(PlayModelController playController) {
      while (playController.continueGame()) {
         menuConsole.choose(playController);
      }

      if (playController.reachMaxAttempt()) {
         System.out.println("CodeMaker WINS!!");
         playController.codeMakerWins();
      }
   }

   public void play(PlayModelController playController) {
      playController.registry();

      Code guessCode = askGuessCode(playController);
      Response response = playController.evaluate(guessCode);

      showResponse(guessCode, response);

      if (response.codeWasBroken()) {
         playController.codeBreakerWins();
         System.out.println("CodeBreaker WINS!!");
      }
   }

   public void undo(PlayModelController playController) {
      playController.undo();
      System.out.println(
         "--- UNDO to Attempt " + playController.getAttempt() + " of " + playController.getMaxAttempt()
      );
   }

   public void redo(PlayModelController playController) {
      playController.redo();
      System.out.println(
         "--- REDO to Attempt " + playController.getAttempt() + " of " + playController.getMaxAttempt()
      );
   }

   public void quit(PlayModelController playController) {
      playController.quit();
   }

   private void showResponse(Code code, Response response) {
      System.out.println(code.toString() + " -> " + response.toString());
   }

   private Code askGuessCode(PlayModelController playController) {
      System.out.println(
         "--- Choose " + Game.FIGURES_TO_GUESS + " figures to try to guess code. Attempt " +
         playController.getAttempt() + " of " + playController.getMaxAttempt()
      );

      Vector<Character> figures = playController.getValidFigures().getFigures();
      Code.Builder builder = new Code.Builder();
      while (builder.size() < Game.FIGURES_TO_GUESS) {
         builder.add(reader.apply("Press key for one character", figures));
      }
      return builder.build();
   }
}

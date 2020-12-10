package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.Response;
import com.upm.master.mastermind.view.console.menu.*;

import java.util.Vector;

public class PlayConsole {
   private final KeyboardReader reader = new KeyboardReader();
   private final MenuConsole menuConsole = new MenuConsole();
   private Command undoCommand;

   PlayConsole() {
      menuConsole.add(new UndoCommand(this));
      menuConsole.add(new RedoCommand(this));
      menuConsole.add(new PlayCommand(this));
      menuConsole.add(new QuitCommand(this));
   }

   public void update(PlayController playController) {
      while (playController.continueGame()) {
         menuConsole.choose(playController);
      }

      if (!playController.codeDiscovered() && !playController.isAborted()) {
         System.out.println("CodeMaker WINS!!");
         playController.codeMakerWins();
      }
   }

   public void play(PlayController playController) {
      playController.registry();

      Code guessCode = askGuessCode(playController);
      Response response = playController.evaluate(guessCode);

      showResponse(guessCode, response);

      System.out.println("codewasbroken=" + response.codeWasBroken());

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

   public void quit(PlayController playController) {
      playController.quit();
   }

   private void showResponse(Code code, Response response) {
      System.out.println(code.toString() + " -> " + response.toString());
   }

   private Code askGuessCode(PlayController playController) {
      System.out.println(
         "--- Choose " + Game.FIGURES_TO_GUESS + " figures to try to guess code. Attempt " +
         playController.getAttempt() + " of " + playController.getMaxAttempt()
      );

      Vector<Character> figures = playController.getValidFigures().getFigures();
      Code.Builder builder = new Code.Builder();

      String code = reader.readString("Press key for one character", Game.FIGURES_TO_GUESS, figures);
      for (int ii = 0; ii < code.length(); ++ ii) {
         builder.add(code.charAt(ii));
      }
      return builder.build();
   }
}

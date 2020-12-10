package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.controller.ResumeController;

import java.util.Arrays;
import java.util.Vector;

public class ResumeConsole {
   private KeyboardReader reader = new KeyboardReader();
   private final Character characters[] = { 'Y', 'N' };

   public void update(ResumeController resumeController) {
      if (askPlayNewGame())
         resumeController.resume();
      else
         resumeController.stop();
   }

   private boolean askPlayNewGame() {
      Character character = reader.readCharacter("Do you want to start a new game?", new Vector<>(Arrays.asList(characters)));

      character = Character.toUpperCase(character);

      if (character.equals('Y'))
         return true;

      return false;
   }
}


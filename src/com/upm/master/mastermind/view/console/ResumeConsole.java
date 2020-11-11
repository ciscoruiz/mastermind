package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.controller.ResumeController;

public class ResumeConsole {
   private CharacterReader reader = new CharacterReader();

   public void update(ResumeController resumeController) {
      if (askPlayNewGame())
         resumeController.resume();
      else
         resumeController.stop();
   }

   private boolean askPlayNewGame() {
      Character character = null;

      while (character == null) {
         System.out.println("Do you want to start a new game (Y/N)?");
         character = reader.apply();

         if (character == null)
            continue;

         character = Character.toUpperCase(character);

         if (character.equals('N'))
            return false;

         if (character.equals('Y'))
            return true;

         character = null;
      }

      return false;
   }
}


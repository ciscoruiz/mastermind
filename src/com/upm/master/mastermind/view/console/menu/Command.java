package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.view.console.PlayConsole;

public abstract class Command {
   public interface ActivationEvaluator {
      boolean evaluate(PlayController playController);
   }

   protected PlayConsole playConsole;
   private Character character;
   private String title;
   ActivationEvaluator activationEvaluator;

   boolean activate = true;

   protected Command(PlayConsole playConsole, Character character, String title, ActivationEvaluator activationEvaluator) {
      this.playConsole = playConsole;
      this.character = character;
      this.title = title;
      this.activationEvaluator = activationEvaluator;
   }

   public Character getCharacter() {
      return character;
   }

   public String getTitle() {
      return title;
   }

   public boolean isActivated(PlayController playController) {
      return activationEvaluator.evaluate(playController);
   }


   abstract void execute(PlayController playController);
}

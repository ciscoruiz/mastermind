package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.model.PlayModelController;
import com.upm.master.mastermind.view.console.PlayConsole;

public abstract class Command {
   public interface ActivationEvaluator {
      boolean evaluate(PlayModelController playController);
   }

   protected PlayConsole playConsole;
   private Character character;
   private String title;
   private ActivationEvaluator activationEvaluator;

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

   public boolean isActivated(PlayModelController playController) {
      return activationEvaluator.evaluate(playController);
   }

   abstract void execute(PlayModelController playController);
}

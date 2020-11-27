package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.view.console.PlayConsole;

public abstract class Command {
   protected PlayConsole playConsole;
   private Character character;
   private String title;
   boolean activate = true;

   protected Command(PlayConsole playConsole, Character character, String title) {
      this.playConsole = playConsole;
      this.character = character;
      this.title = title;
   }

   public Character getCharacter() {
      return character;
   }

   public String getTitle() {
      return title;
   }

   public boolean isActivate() {
      return activate;
   }

   abstract void execute(PlayController playController);
}

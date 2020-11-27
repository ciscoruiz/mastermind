package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.view.console.CharacterReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MenuConsole {
   private List<Command> commands = new ArrayList<>();
   private CharacterReader reader = new CharacterReader();

   public MenuConsole add(Command command) {
      commands.add(command);
      return this;
   }

   public void choose(PlayController playController) {
      for (Command command : commands) {
         if (command.isActivated(playController)) {
            System.out.println(command.getCharacter() + ".- " + command.getTitle());
         }
      }
      System.out.print("Choose an option: ");

      Character option = reader.apply("Choose an option", getValidOptions(playController));

      for (Command command : commands)  {
         if (command.getCharacter().equals(option)) {
            command.execute(playController);
            return;
         }
      }
   }

   private Vector<Character> getValidOptions(PlayController playController) {
      Vector<Character> result = new Vector<>();

      for (Command command : commands) {
         if (command.isActivated(playController)) {
            result.add(command.getCharacter());
         }
      }

      return result;
   }

}

package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.model.ValidFigures;
import com.upm.master.mastermind.view.console.CharacterReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MenuConsole {
   private List<Command> commands = new ArrayList<>();
   private CharacterReader reader = new CharacterReader();

   public MenuConsole add(Command command) {
      commands.add(command);
      return this;
   }

   public void choose(PlayController playController) {
      for (Command command : commands) {
         if (command.isActivate()) {
            System.out.println(command.getCharacter() + ".- " + command.getTitle());
         }
      }
      System.out.print("Elija una opción: ");

      Character option = readOption();

      for (Command command : commands)  {
         if (command.getCharacter().equals(option)) {
            command.execute(playController);
            return;
         }
      }
   }

   private Character readOption() {
      Character result;
      HashSet<Character> validOptions = getValidOptions();

      while (true) {
         if ((result = reader.apply()) == null)
            continue;

         if (validOptions.contains(result)) {
            break;
         }

         System.out.println("Character " + result + " is not a valid selection");
      }

      return result;
   }

   private HashSet<Character> getValidOptions() {
      HashSet<Character> result = new HashSet<>();

      for (Command command : commands) {
         if (command.isActivate()) {
            result.add(command.getCharacter());
         }
      }

      return result;
   }

}

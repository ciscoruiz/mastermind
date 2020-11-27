package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.model.PlayModelController;
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

   public void choose(PlayModelController playController) {
      Vector<Character>  validOptions = new Vector<>();

      for (Command command : commands) {
         if (command.isActivated(playController)) {
            System.out.println(command.getCharacter() + ".- " + command.getTitle());
            validOptions.add(command.getCharacter());
         }
      }

      Character option = reader.apply("Choose an option", validOptions);

      for (Command command : commands)  {
         if (command.getCharacter().equals(option)) {
            command.execute(playController);
            return;
         }
      }
   }
}

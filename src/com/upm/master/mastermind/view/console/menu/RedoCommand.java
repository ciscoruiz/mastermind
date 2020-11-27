package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.view.console.PlayConsole;

public class RedoCommand extends Command {
   public RedoCommand(PlayConsole playConsole) {
      super(playConsole, 'r', "Apply REDO");
   }

   void execute(PlayController playController) {
      playConsole.redo(playController);
   }
}

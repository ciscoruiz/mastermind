package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.view.console.PlayConsole;

public class UndoCommand extends Command {
   public UndoCommand(PlayConsole playConsole) {
      super(playConsole, 'u', "Apply UNDO");
   }

   void execute(PlayController playController) {
      playConsole.undo(playController);
   }
}

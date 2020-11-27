package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.model.PlayController;
import com.upm.master.mastermind.view.console.PlayConsole;

public class UndoCommand extends Command {
   public UndoCommand(PlayConsole playConsole) {
      super(playConsole, 'U', "Apply UNDO", (playController) -> { return playController.canApplyUndo(); });
   }

   void execute(PlayController playController) {
      playConsole.undo(playController);
   }
}

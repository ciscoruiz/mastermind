package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.model.PlayModelController;
import com.upm.master.mastermind.view.console.PlayConsole;

public class RedoCommand extends Command {
   public RedoCommand(PlayConsole playConsole) {
      super(playConsole, 'R', "Apply REDO", (playController) -> { return playController.canApplyRedo(); });
   }

   void execute(PlayModelController playController) {
      playConsole.redo(playController);
   }
}

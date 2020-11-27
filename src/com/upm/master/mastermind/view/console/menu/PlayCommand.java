package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.model.PlayModelController;
import com.upm.master.mastermind.view.console.PlayConsole;

public class PlayCommand extends Command {
   public PlayCommand(PlayConsole playConsole) {
      super(playConsole, 'P', "Play a new combination", (playController) -> { return true; });
   }

   void execute(PlayModelController playController) {
      playConsole.play(playController);
   }
}

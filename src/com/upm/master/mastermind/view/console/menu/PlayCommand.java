package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.view.console.PlayConsole;

public class PlayCommand extends Command {
   public PlayCommand(PlayConsole playConsole) {
      super(playConsole, 't', "Try a new combination");
   }

   void execute(PlayController playController) {
      playConsole.play(playController);
   }
}
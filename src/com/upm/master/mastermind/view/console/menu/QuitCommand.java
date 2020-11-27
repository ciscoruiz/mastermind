package com.upm.master.mastermind.view.console.menu;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.view.console.PlayConsole;

public class QuitCommand extends Command {
   public QuitCommand(PlayConsole playConsole) {
      super(playConsole, 'q', "Quit game");
   }

   void execute(PlayController playController) {
      playConsole.quit();
   }
}

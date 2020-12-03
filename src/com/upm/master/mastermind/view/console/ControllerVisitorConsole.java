package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.controller.*;

public class ControllerVisitorConsole implements ControllerVisitor {
   private StartConsole startConsole = new StartConsole();
   private PlayConsole playConsole = new PlayConsole();
   private ResumeConsole resumeConsole = new ResumeConsole();

   @Override
   public void visit(StartController startController) {
      startConsole.update(startController);
   }

   @Override
   public void visit(PlayController playController) {
      playConsole.update(playController);
   }

   @Override
   public void visit(ResumeController resumeController) {
      resumeConsole.update(resumeController);
   }
}

package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.controller.ControllerVisitor;
import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.controller.StartController;
import com.upm.master.mastermind.controller.model.*;
import com.upm.master.mastermind.view.ViewsContainer;

public class ViewsContainerConsole implements ViewsContainer, ControllerVisitor {
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
   public void visit(ResumeModelController resumeController) {
      resumeConsole.update(resumeController);
   }

   @Override
   public void updateView(ModelController controller) {
      controller.accept(this);
   }
}

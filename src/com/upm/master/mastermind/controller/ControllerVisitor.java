package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.controller.model.PlayController;
import com.upm.master.mastermind.controller.model.ResumeController;
import com.upm.master.mastermind.controller.model.StartController;

public interface ControllerVisitor {
   void visit(StartController startController);

   void visit(PlayController playController);

   void visit(ResumeController resumeController);
}


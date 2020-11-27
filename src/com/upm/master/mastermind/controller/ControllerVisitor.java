package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.controller.model.PlayModelController;
import com.upm.master.mastermind.controller.model.ResumeModelController;

public interface ControllerVisitor {
   void visit(StartController startController);

   void visit(PlayModelController playController);

   void visit(ResumeModelController resumeController);
}


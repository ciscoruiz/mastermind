package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.controller.model.PlayModelController;
import com.upm.master.mastermind.controller.model.ResumeModelController;
import com.upm.master.mastermind.controller.model.StartModelController;

public interface ControllerVisitor {
   void visit(StartModelController startController);

   void visit(PlayModelController playController);

   void visit(ResumeModelController resumeController);
}


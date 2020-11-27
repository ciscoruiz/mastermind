package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.controller.model.ResumeModelController;

public interface ControllerVisitor {
   void visit(StartController startController);

   void visit(PlayController playController);

   void visit(ResumeModelController resumeController);
}


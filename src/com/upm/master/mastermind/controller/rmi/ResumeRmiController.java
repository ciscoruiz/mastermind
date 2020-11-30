package com.upm.master.mastermind.controller.rmi;

import com.upm.master.mastermind.controller.ControllerVisitor;
import com.upm.master.mastermind.controller.ResumeController;
import com.upm.master.mastermind.rmi.MasterMindOperations;

import java.rmi.RemoteException;

public class ResumeRmiController extends RmiController implements ResumeController {
   public ResumeRmiController(MasterMindOperations masterMindOperations) {
      super(masterMindOperations);
   }

   @Override
   public void accept(ControllerVisitor controllerVisitor) {
      controllerVisitor.visit(this);
   }

   public void resume() {
      try {
         masterMindOperations.resume();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public void stop() {
      try {
         masterMindOperations.stop();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }
}

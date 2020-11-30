package com.upm.master.mastermind.controller.rmi;

import com.upm.master.mastermind.controller.ControllerVisitor;
import com.upm.master.mastermind.controller.StartController;
import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.rmi.MasterMindOperations;

import java.rmi.RemoteException;

public class StartRmiController extends RmiController implements StartController {
   public StartRmiController(MasterMindOperations masterMindOperations) {
      super(masterMindOperations);
   }

   @Override
   public void accept(ControllerVisitor controllerVisitor) {
      controllerVisitor.visit(this);
   }

   public void initializeGame() {
      try {
         masterMindOperations.initializeGame();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public Code getSecretCode() {
      try {
         return masterMindOperations.getSecretCode();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }
}

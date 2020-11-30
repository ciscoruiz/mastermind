package com.upm.master.mastermind.controller.rmi;

import com.upm.master.mastermind.controller.Controller;
import com.upm.master.mastermind.rmi.MasterMindOperations;

import java.rmi.RemoteException;

public abstract class RmiController implements Controller {
   protected MasterMindOperations masterMindOperations;

   public RmiController(MasterMindOperations masterMindOperations) {
      this.masterMindOperations = masterMindOperations;
   }

   @Override
   public void setNextState() {
      try {
         masterMindOperations.setNextState();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }
}

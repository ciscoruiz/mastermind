package com.upm.master.mastermind.runtime;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.ControllerVisitor;
import com.upm.master.mastermind.controller.ControllersContainer;
import com.upm.master.mastermind.controller.rmi.ControllerRmiFactory;
import com.upm.master.mastermind.rmi.MasterMindOperations;
import com.upm.master.mastermind.view.console.ControllerVisitorConsole;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MasterMindClient implements MasterMind {
   private ControllerVisitor controllerVisitor;
   private MasterMindOperations masterMindOperations;
   private ControllersContainer controllersContainer;

   static public MasterMind create() {
      ControllerVisitor controllerVisitor = new ControllerVisitorConsole();
      Registry registry = null;

      try {
         registry = LocateRegistry.getRegistry(MasterMindOperations.PORT);
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }

      MasterMindOperations masterMindOperations;

      try {
         masterMindOperations = (MasterMindOperations) registry.lookup(MasterMindOperations.SERVICE_NAME);
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
      catch (NotBoundException e) {
         throw new RuntimeException(e);
      }

      System.out.println("Client connected to " + MasterMindOperations.SERVICE_NAME + ":" + MasterMindOperations.PORT);

      return new MasterMindClient(controllerVisitor, masterMindOperations);
   }

   private MasterMindClient(ControllerVisitor controllerVisitor, MasterMindOperations masterMindOperations) {
      this.controllerVisitor = controllerVisitor;
      this.masterMindOperations = masterMindOperations;
      this.controllersContainer = new ControllersContainer(new ControllerRmiFactory(masterMindOperations));
   }

   public void play() {
      try {
         masterMindOperations.reset();
         while (masterMindOperations.stateEnablesContinuePlaying()) {
            controllersContainer.getController(masterMindOperations.getState()).accept(controllerVisitor);
         }
      }
      catch (RemoteException e) {
         e.printStackTrace();
      }
   }
}

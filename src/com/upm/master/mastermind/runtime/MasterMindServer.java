package com.upm.master.mastermind.runtime;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.ControllerVisitor;
import com.upm.master.mastermind.rmi.MasterMindOperations;
import com.upm.master.mastermind.view.console.ControllerVisitorConsole;

import java.rmi.registry.Registry;

public class MasterMindServer extends MasterMindOverModel {
   static public MasterMind create() {
      ControllerVisitor controllerVisitor = new ControllerVisitorConsole();
      return new MasterMindServer(controllerVisitor);
   }

   private MasterMindServer(ControllerVisitor controllerVisitor) {
      super(controllerVisitor);
   }

   public void play() {
      try {
         Registry registry = java.rmi.registry.LocateRegistry.createRegistry(MasterMindOperations.PORT);
         MasterMindOperations masterMindServerOperations = new MasterMindServerOperations(controllerFactory, state);
         registry.bind(MasterMindOperations.SERVICE_NAME, masterMindServerOperations);
         System.out.println("Server accepting connections  " + MasterMindOperations.SERVICE_NAME + ":" + MasterMindOperations.PORT);
      }
      catch (Exception e) {
         System.err.println("Server exception: " + e.toString());
         e.printStackTrace();
      }
   }
}

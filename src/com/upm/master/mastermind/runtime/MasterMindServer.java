package com.upm.master.mastermind.runtime;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.rmi.MasterMindOperations;
import com.upm.master.mastermind.view.ViewsContainer;
import com.upm.master.mastermind.view.console.ViewsContainerConsole;

import java.rmi.registry.Registry;

public class MasterMindServer extends MasterMindOverModel {
   static public MasterMind create() {
      ViewsContainer viewsContainer = new ViewsContainerConsole();
      return new MasterMindServer(viewsContainer);
   }

   MasterMindServer(ViewsContainer viewsContainer) {
      super(viewsContainer);
   }

   public void play() {
      try {
         Registry registry = java.rmi.registry.LocateRegistry.createRegistry(MasterMindOperations.PORT);
         MasterMindOperations masterMindServerOperations = new MasterMindServerOperations(this, state);
         registry.bind(MasterMindOperations.SERVICE_NAME, masterMindServerOperations);
         System.out.println("Server accepting connections  " + MasterMindOperations.SERVICE_NAME + ":" + MasterMindOperations.PORT);
      }
      catch (Exception e) {
         System.err.println("Server exception: " + e.toString());
         e.printStackTrace();
      }
   }
}

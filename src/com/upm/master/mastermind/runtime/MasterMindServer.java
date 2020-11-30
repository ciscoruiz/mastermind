package com.upm.master.mastermind.runtime;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.controller.ResumeController;
import com.upm.master.mastermind.controller.StartController;
import com.upm.master.mastermind.controller.ControllersContainer;
import com.upm.master.mastermind.controller.model.PlayModelController;
import com.upm.master.mastermind.controller.model.ResumeModelController;
import com.upm.master.mastermind.controller.model.StartModelController;
import com.upm.master.mastermind.rmi.MasterMindOperations;
import com.upm.master.mastermind.view.ViewsContainer;
import com.upm.master.mastermind.view.console.ViewsContainerConsole;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MasterMindServer extends MasterMindOverModel {
   private ControllersContainer controllersContainer;

   static public MasterMind create() {
      ViewsContainer viewsContainer = new ViewsContainerConsole();
      return new MasterMindServer(viewsContainer);
   }

   MasterMindServer(ViewsContainer viewsContainer) {
      super(viewsContainer);
   }

   public void play() {
      StartController startController = new StartModelController(game, state);
      PlayController playController = new PlayModelController(game, state, gameHistoryKeeper);
      ResumeController resumeController = new ResumeModelController(game, state, gameHistoryKeeper);

      try {
         Registry registry = LocateRegistry.getRegistry();

         MasterMindOperations masterMindServerOperations = new MasterMindServerOperations(this);
         MasterMindOperations stub = (MasterMindOperations) UnicastRemoteObject.exportObject(masterMindServerOperations, 0);

         Naming.bind(MasterMindOperations.SERVICE_NAME, stub);
      }
      catch (Exception e) {
         System.err.println("Server exception: " + e.toString());
         e.printStackTrace();
      }
   }
}

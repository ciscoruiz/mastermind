package com.upm.master.mastermind.impl;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.controller.ResumeController;
import com.upm.master.mastermind.controller.StartController;
import com.upm.master.mastermind.controller.model.ControllersContainer;
import com.upm.master.mastermind.controller.model.PlayModelController;
import com.upm.master.mastermind.controller.model.ResumeModelController;
import com.upm.master.mastermind.controller.model.StartModelController;
import com.upm.master.mastermind.rmi.MasterMindOperations;
import com.upm.master.mastermind.view.ViewsContainer;
import com.upm.master.mastermind.view.console.ViewsContainerConsole;

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
         MasterMindServerOperations masterMindServerOperations = new MasterMindServerOperations(startController, playController, resumeController);

         MasterMindOperations stub = (MasterMindOperations) UnicastRemoteObject.exportObject(masterMindServerOperations, 5000);

         Registry registry = LocateRegistry.getRegistry();

         registry.bind("MasterMindOperations", stub);
      } catch (Exception e) {
         System.err.println("Server exception: " + e.toString());
         e.printStackTrace();
      }
   }
}

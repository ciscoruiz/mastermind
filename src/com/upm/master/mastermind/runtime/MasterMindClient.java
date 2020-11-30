package com.upm.master.mastermind.runtime;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.ControllersContainer;
import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.controller.ResumeController;
import com.upm.master.mastermind.controller.StartController;
import com.upm.master.mastermind.controller.rmi.PlayRmiController;
import com.upm.master.mastermind.controller.rmi.ResumeRmiController;
import com.upm.master.mastermind.controller.rmi.StartRmiController;
import com.upm.master.mastermind.rmi.MasterMindOperations;
import com.upm.master.mastermind.view.ViewsContainer;
import com.upm.master.mastermind.view.console.ViewsContainerConsole;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MasterMindClient implements MasterMind {
   private ViewsContainer viewsContainer;
   private MasterMindOperations masterMindOperations;
   private ControllersContainer controllersContainer;

   static public MasterMind create() {
      ViewsContainer viewsContainer = new ViewsContainerConsole();
      Registry registry = null;

      try {
         registry = LocateRegistry.getRegistry();
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

      return new MasterMindClient(viewsContainer, masterMindOperations);
   }

   MasterMindClient(ViewsContainer viewsContainer, MasterMindOperations masterMindOperations) {
      this.viewsContainer = viewsContainer;
      this.masterMindOperations = masterMindOperations;
      this.controllersContainer = new ControllersContainer(this);
   }

   public void play() {
      try {
         while (masterMindOperations.stateEnablesContinuePlaying()) {
            viewsContainer.updateView(controllersContainer.getController(masterMindOperations.getState()));
         }
      }
      catch (RemoteException e) {
         e.printStackTrace();
      }
   }

   public StartController createStartController() {  return new StartRmiController(masterMindOperations); }
   public PlayController createPlayController() { return new PlayRmiController(masterMindOperations); }
   public ResumeController createResumeController() {  return new ResumeRmiController(masterMindOperations); }
}

package com.upm.master.mastermind.runtime;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.ControllerVisitor;
import com.upm.master.mastermind.controller.ControllersContainer;
import com.upm.master.mastermind.view.console.ControllerVisitorConsole;

public class MasterMindStandalone extends MasterMindOverModel {
   private ControllersContainer controllersContainer;

   static public MasterMind create() {
      ControllerVisitor controllerVisitor = new ControllerVisitorConsole();
      return new MasterMindStandalone(controllerVisitor);
   }

   private MasterMindStandalone(ControllerVisitor controllerVisitor) {
      super(controllerVisitor);
      this.controllersContainer = new ControllersContainer(controllerFactory);
   }

   public void play() {
      while (state.continuePlaying()) {
         controllersContainer.getController(state).accept(controllerVisitor);
      }
   }
}

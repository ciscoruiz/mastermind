package com.upm.master.mastermind.impl;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.model.ControllersContainer;
import com.upm.master.mastermind.view.ViewsContainer;
import com.upm.master.mastermind.view.console.ViewsContainerConsole;

public class MasterMindStandalone extends MasterMindOverModel {
   private ControllersContainer controllersContainer;

   static public MasterMind create() {
      ViewsContainer viewsContainer = new ViewsContainerConsole();
      return new MasterMindStandalone(viewsContainer);
   }

   MasterMindStandalone(ViewsContainer viewsContainer) {
      super(viewsContainer);
      this.controllersContainer = new ControllersContainer(game, state, gameHistoryKeeper);
   }

   public void play() {
      while (state.continuePlaying()) {
         viewsContainer.updateView(controllersContainer.getController(state));
      }
   }
}

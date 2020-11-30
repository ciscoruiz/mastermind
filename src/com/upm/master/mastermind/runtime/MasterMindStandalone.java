package com.upm.master.mastermind.runtime;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.ControllersContainer;
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
      this.controllersContainer = new ControllersContainer(this);
   }

   public void play() {
      while (state.continuePlaying()) {
         viewsContainer.updateView(controllersContainer.getController(state));
      }
   }
}

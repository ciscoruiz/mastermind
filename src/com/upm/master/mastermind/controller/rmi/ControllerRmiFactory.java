package com.upm.master.mastermind.controller.rmi;

import com.upm.master.mastermind.controller.ControllerAbstractFactory;
import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.controller.ResumeController;
import com.upm.master.mastermind.controller.StartController;
import com.upm.master.mastermind.rmi.MasterMindOperations;

public class ControllerRmiFactory implements ControllerAbstractFactory {
   private MasterMindOperations masterMindOperations;

   public ControllerRmiFactory(MasterMindOperations masterMindOperations) {
      this.masterMindOperations = masterMindOperations;
   }

   public StartController createStartController() {
      return new StartRmiController(masterMindOperations);
   }

   public PlayController createPlayController() {
      return new PlayRmiController(masterMindOperations);
   }

   public ResumeController createResumeController() {
      return new ResumeRmiController(masterMindOperations);
   }
}

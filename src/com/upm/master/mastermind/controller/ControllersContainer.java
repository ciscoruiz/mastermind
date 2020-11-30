package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.Controller;
import com.upm.master.mastermind.model.State;

import java.util.HashMap;

public class ControllersContainer {
   HashMap<State.Value, Controller> controllers = new HashMap<>();

   public ControllersContainer(MasterMind masterMind) {
      controllers.put(State.Value.INITIAL, masterMind.createStartController());
      controllers.put(State.Value.PLAYING, masterMind.createPlayController());
      controllers.put(State.Value.RESUME, masterMind.createResumeController());
   }

   public Controller getController(State state) {
      return controllers.get(state.getValue());
   }
}

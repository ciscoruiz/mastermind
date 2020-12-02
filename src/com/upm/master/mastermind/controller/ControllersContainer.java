package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.model.State;

import java.util.HashMap;

public class ControllersContainer {
   HashMap<State.Value, Controller> controllers = new HashMap<>();

   public ControllersContainer(ControllerAbstractFactory controllerAbstractFactory) {
      controllers.put(State.Value.INITIAL, controllerAbstractFactory.createStartController());
      controllers.put(State.Value.PLAYING, controllerAbstractFactory.createPlayController());
      controllers.put(State.Value.RESUME, controllerAbstractFactory.createResumeController());
   }

   public Controller getController(State state) {
      return controllers.get(state.getValue());
   }
}

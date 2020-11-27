package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.GameHistoryKeeper;
import com.upm.master.mastermind.model.State;

import java.util.HashMap;

public class ControllersContainer {
   HashMap<State.Value, Controller> controllers = new HashMap<>();

   public ControllersContainer(Game game, State state, GameHistoryKeeper gameHistoryKeeper) {
      controllers.put(State.Value.INITIAL, new StartController(game, state));
      controllers.put(State.Value.PLAYING, new PlayController(game, state, gameHistoryKeeper));
      controllers.put(State.Value.RESUME, new ResumeController(game, state));
   }

   public Controller getController(State state) {
      return controllers.get(state.getValue());
   }
}

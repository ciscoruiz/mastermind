package com.upm.master.mastermind.controller.model;

import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.GameHistoryKeeper;
import com.upm.master.mastermind.model.State;

import java.util.HashMap;

public class ControllersContainer {
   HashMap<State.Value, ModelController> controllers = new HashMap<>();

   public ControllersContainer(Game game, State state, GameHistoryKeeper gameHistoryKeeper) {
      controllers.put(State.Value.INITIAL, new StartModelController(game, state));
      controllers.put(State.Value.PLAYING, new PlayModelController(game, state, gameHistoryKeeper));
      controllers.put(State.Value.RESUME, new ResumeModelController(game, state, gameHistoryKeeper));
   }

   public ModelController getController(State state) {
      return controllers.get(state.getValue());
   }
}

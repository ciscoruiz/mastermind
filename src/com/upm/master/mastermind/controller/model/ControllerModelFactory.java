package com.upm.master.mastermind.controller.model;

import com.upm.master.mastermind.controller.ControllerAbstractFactory;
import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.controller.ResumeController;
import com.upm.master.mastermind.controller.StartController;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.State;
import com.upm.master.mastermind.model.GameHistoryKeeper;

public class ControllerModelFactory implements ControllerAbstractFactory {
   private Game game;
   private State state;
   private GameHistoryKeeper gameHistoryKeeper;

   public ControllerModelFactory(Game game, State state, GameHistoryKeeper gameHistoryKeeper) {
      this.game = game;
      this.state = state;
      this.gameHistoryKeeper = gameHistoryKeeper;
   }
   public StartController createStartController() {
      return new StartModelController(game, state);
   }
   public PlayController createPlayController() {
      return new PlayModelController(game,state, gameHistoryKeeper);
   }
   public ResumeController createResumeController() {
      return new ResumeModelController(game, state, gameHistoryKeeper);
   }
}

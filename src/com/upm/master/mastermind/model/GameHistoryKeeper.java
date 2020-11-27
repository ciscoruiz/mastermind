package com.upm.master.mastermind.model;

import java.util.ArrayList;
import java.util.List;

public class GameHistoryKeeper {
   private List<Game.Memento> history = new ArrayList<Game.Memento>();
   private Game game;
   private int position = 0;

   public GameHistoryKeeper(Game game) {
      this.game = game;
   }

   public void registry() {
      history.add(position ++, game.createMemento());
   }

   public boolean canApplyRedo() {
      return (position < history.size() - 1);
   }

   public void redo() {
      game.restore(history.get(++ position));
   }

   public boolean canApplyUndo() {
      if (history.isEmpty())
         return false;

      return position >= 1;
   }

   public void undo() {
      game.restore(history.get(-- position));
   }
}

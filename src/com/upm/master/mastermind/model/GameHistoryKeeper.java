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
      history.add(game.createMemento());
      position ++;
   }

   public boolean redo() {
      if (position >= history.size() - 1)
         return false;

      game.restore(history.get(++ position));

      return true;
   }

   public boolean undo() {
      if (history.isEmpty())
         return false;

      if (position < 1)
         return false;

      game.restore(history.get(-- position));

      return true;
   }
}

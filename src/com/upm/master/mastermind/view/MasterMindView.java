package com.upm.master.mastermind.view;

import com.upm.master.mastermind.model.MasterMind;

public interface MasterMindView {
   void startGame(MasterMind masterMind);
   boolean wantToPlayNewGame(MasterMind masterMind);
   void endGame(MasterMind masterMind);
}

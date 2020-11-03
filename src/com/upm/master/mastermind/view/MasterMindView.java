package com.upm.master.mastermind.view;

import com.upm.master.mastermind.MasterMind;

public interface MasterMindView {
   void startGame(MasterMind masterMind);
   boolean askPlayNewGame(MasterMind masterMind);
   void endGame(MasterMind masterMind);
}

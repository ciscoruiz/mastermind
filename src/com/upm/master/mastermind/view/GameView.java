package com.upm.master.mastermind.view;

import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.Response;

public interface GameView {
   void showSecretCode(Game game);
   void playerWins(Game game);
   void makerWins(Game game);
   Code askGuessCode(Game game);
   void showResponse(Code code, Response response);
}

package com.upm.master.mastermind.view;

import com.upm.master.mastermind.Code;
import com.upm.master.mastermind.Game;
import com.upm.master.mastermind.Response;

public interface GameView {
   void showSecretCode(Game game);
   void breakerWins(Game game);
   void makerWins(Game game);
   Code askGuessCode(Game game);
   void showResponse(Code code, Response response);
}

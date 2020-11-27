package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.model.Code;

public interface StartController extends Controller {
   void initializeGame();
   Code getSecretCode();
}

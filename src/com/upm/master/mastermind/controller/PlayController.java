package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Response;
import com.upm.master.mastermind.model.ValidFigures;

 public interface PlayController {
    boolean continueGame();
    Response evaluate(Code guessCode);
    ValidFigures getValidFigures();
    int getAttempt();
    int getMaxAttempt();
    void codeBreakerWins();
    void codeMakerWins();
    void registry();
    void undo();
    void redo();
    void quit();
    boolean codeDiscovered();
    boolean canApplyUndo();
    boolean canApplyRedo();
}

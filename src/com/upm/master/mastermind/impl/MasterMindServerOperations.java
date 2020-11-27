package com.upm.master.mastermind.impl;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.controller.ResumeController;
import com.upm.master.mastermind.controller.StartController;
import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Response;
import com.upm.master.mastermind.model.ValidFigures;
import com.upm.master.mastermind.rmi.MasterMindOperations;

import java.rmi.RemoteException;

public class MasterMindServerOperations implements MasterMindOperations {
   private StartController startController;
   private PlayController playController;
   private ResumeController resumeController;

   public MasterMindServerOperations(StartController startController, PlayController playController, ResumeController resumeController) {
      this.startController = startController;
      this.playController = playController;
      this.resumeController = resumeController;
   }

   // Start
   @Override
   public void initializeGame() {
      startController.initializeGame();
   }

   @Override
   public Code getSecretCode() {
      return startController.getSecretCode();
   }

   // Play
   @Override
   public boolean continueGame() throws RemoteException {
      return playController.continueGame();
   }

   @Override
   public Response evaluate(Code guessCode) {
      return playController.evaluate(guessCode);
   }

   @Override
   public ValidFigures getValidFigures() throws RemoteException {
      return playController.getValidFigures();
   }

   @Override
   public int getAttempt() throws RemoteException {
      return playController.getAttempt();
   }

   @Override
   public int getMaxAttempt() throws RemoteException {
      return playController.getMaxAttempt();
   }

   @Override
   public void codeBreakerWins() throws RemoteException {
      playController.codeBreakerWins();
   }

   @Override
   public void codeMakerWins() throws RemoteException {
      playController.codeMakerWins();
   }

   @Override
   public void registry() throws RemoteException {
      playController.registry();
   }

   @Override
   public void undo() throws RemoteException {
      playController.undo();
   }

   @Override
   public void redo() throws RemoteException {
      playController.redo();
   }

   @Override
   public void quit() throws RemoteException {
      playController.quit();
   }

   @Override
   public boolean codeDiscovered() throws RemoteException {
      return playController.codeDiscovered();
   }

   @Override
   public boolean isAborted() throws RemoteException {
      return playController.isAborted();
   }

   @Override
   public boolean canApplyUndo() throws RemoteException {
      return playController.canApplyUndo();
   }

   @Override
   public boolean canApplyRedo() throws RemoteException {
      return playController.canApplyRedo();
   }

   // Resume
   @Override
   public void resume() {
      resumeController.resume();
   }

   @Override
   public void stop() {
      resumeController.stop();
   }
}

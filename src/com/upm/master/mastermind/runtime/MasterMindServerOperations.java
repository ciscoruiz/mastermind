package com.upm.master.mastermind.runtime;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.controller.ResumeController;
import com.upm.master.mastermind.controller.StartController;
import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Response;
import com.upm.master.mastermind.model.State;
import com.upm.master.mastermind.model.ValidFigures;
import com.upm.master.mastermind.rmi.MasterMindOperations;

import java.rmi.RemoteException;

public class MasterMindServerOperations implements MasterMindOperations {
   private StartController startController;
   private PlayController playController;
   private ResumeController resumeController;
   private State state;

   public MasterMindServerOperations(MasterMind masterMind, State state) {
      this.startController = masterMind.createStartController();
      this.playController = masterMind.createPlayController();
      this.resumeController = masterMind.createResumeController();
      this.state = state;
   }

   @Override
   public void setNextState() throws RemoteException {
      state.setNextValue();
   }

   // Start
   @Override
   public void initializeGame() throws RemoteException {
      startController.initializeGame();
   }

   @Override
   public Code getSecretCode() throws RemoteException {
      return startController.getSecretCode();
   }

   // Play
   @Override
   public boolean continueGame() throws RemoteException {
      return playController.continueGame();
   }

   @Override
   public Response evaluate(Code guessCode) throws RemoteException {
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
   public void resume() throws RemoteException {
      resumeController.resume();
   }

   @Override
   public void stop() throws RemoteException {
      resumeController.stop();
   }
}

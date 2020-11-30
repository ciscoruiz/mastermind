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
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class MasterMindServerOperations extends UnicastRemoteObject implements MasterMindOperations {
   private static final Logger logger = Logger.getLogger(MasterMindServerOperations.class.getName());
   private StartController startController;
   private PlayController playController;
   private ResumeController resumeController;
   private State state;

   public MasterMindServerOperations(MasterMind masterMind, State state) throws RemoteException {
      this.startController = masterMind.createStartController();
      this.playController = masterMind.createPlayController();
      this.resumeController = masterMind.createResumeController();
      this.state = state;
   }

   @Override
   public void setNextState() throws RemoteException {
      state.setNextValue();
      logger.info("State=" + state.getValue());
   }

   @Override
   public boolean stateEnablesContinuePlaying() throws RemoteException {
      logger.info("Result=" + state.continuePlaying());
      return state.continuePlaying();
   }

   @Override
   public State getState() throws RemoteException {
      logger.info("Result=" + state.continuePlaying());
      return state;
   }

   @Override
   public void reset() throws RemoteException {
      state.setValue(State.Value.INITIAL);
      logger.info("State=" + state.getValue());
   }

   // Start
   @Override
   public void initializeGame() throws RemoteException {
      startController.initializeGame();
      logger.info("Generated Code=" + startController.getSecretCode());
   }

   @Override
   public Code getSecretCode() throws RemoteException {
      logger.info("Result=" + startController.getSecretCode());
      return startController.getSecretCode();
   }

   // Play
   @Override
   public boolean continueGame() throws RemoteException {
      logger.info("Result=" + playController.continueGame());
      return playController.continueGame();
   }

   @Override
   public Response evaluate(Code guessCode) throws RemoteException {
      logger.info("Result=" + playController.evaluate(guessCode));
      return playController.evaluate(guessCode);
   }

   @Override
   public ValidFigures getValidFigures() throws RemoteException {
      logger.info("Result=" + playController.getValidFigures());
      return playController.getValidFigures();
   }

   @Override
   public int getAttempt() throws RemoteException {
      logger.info("Result=" + playController.getAttempt());
      return playController.getAttempt();
   }

   @Override
   public int getMaxAttempt() throws RemoteException {
      logger.info("Result=" + playController.getMaxAttempt());
      return playController.getMaxAttempt();
   }

   @Override
   public void codeBreakerWins() throws RemoteException {
      logger.info("codeBreakerWins");
      playController.codeBreakerWins();
   }

   @Override
   public void codeMakerWins() throws RemoteException {
      logger.info("codeMakerWins");
      playController.codeMakerWins();
   }

   @Override
   public void registry() throws RemoteException {
      logger.info("Registry called");
      playController.registry();
   }

   @Override
   public void undo() throws RemoteException {
      logger.info("Undo called");
      playController.undo();
   }

   @Override
   public void redo() throws RemoteException {
      logger.info("Redo called");
      playController.redo();
   }

   @Override
   public void quit() throws RemoteException {
      logger.info("Quit called");
      playController.quit();
   }

   @Override
   public boolean codeDiscovered() throws RemoteException {
      logger.info("Result=" + playController.codeDiscovered());
      return playController.codeDiscovered();
   }

   @Override
   public boolean isAborted() throws RemoteException {
      logger.info("Result=" + playController.isAborted());
      return playController.isAborted();
   }

   @Override
   public boolean canApplyUndo() throws RemoteException {
      logger.info("Result=" + playController.canApplyUndo());
      return playController.canApplyUndo();
   }

   @Override
   public boolean canApplyRedo() throws RemoteException {
      logger.info("Result=" + playController.canApplyRedo());
      return playController.canApplyRedo();
   }

   // Resume
   @Override
   public void resume() throws RemoteException {
      logger.info("Resume called");
      resumeController.resume();
   }

   @Override
   public void stop() throws RemoteException {
      logger.info("Stop called");
      resumeController.stop();
   }
}

package com.upm.master.mastermind.controller.rmi;

import com.upm.master.mastermind.controller.ControllerVisitor;
import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Response;
import com.upm.master.mastermind.model.ValidFigures;
import com.upm.master.mastermind.rmi.MasterMindOperations;

import java.rmi.RemoteException;

public class PlayRmiController extends RmiController implements PlayController {
   public PlayRmiController(MasterMindOperations masterMindOperations) {
      super(masterMindOperations);
   }

   @Override
   public void accept(ControllerVisitor controllerVisitor) {
      controllerVisitor.visit(this);
   }

   public boolean continueGame() {
      try {
         return masterMindOperations.continueGame();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public Response evaluate(Code guessCode) {
      try {
         return masterMindOperations.evaluate(guessCode);
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public ValidFigures getValidFigures() {
      try {
         return masterMindOperations.getValidFigures();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public int getAttempt() {
      try {
         return masterMindOperations.getAttempt();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public int getMaxAttempt() {
      try {
         return masterMindOperations.getMaxAttempt();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public void codeBreakerWins() {
      try {
         masterMindOperations.setNextState();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public void codeMakerWins() {
      try {
         masterMindOperations.setNextState();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public void registry() {
      try {
         masterMindOperations.registry();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public void undo() {
      try {
         masterMindOperations.undo();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public void redo() {
      try {
         masterMindOperations.redo();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public void quit() {
      try {
         masterMindOperations.quit();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public boolean codeDiscovered() {
      try {
         return masterMindOperations.codeDiscovered();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public boolean isAborted() {
      try {
         return masterMindOperations.isAborted();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public boolean canApplyUndo() {
      try {
         return masterMindOperations.canApplyUndo();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }

   public boolean canApplyRedo() {
      try {
         return masterMindOperations.canApplyRedo();
      }
      catch (RemoteException e) {
         throw new RuntimeException(e);
      }
   }
}

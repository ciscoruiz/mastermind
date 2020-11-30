package com.upm.master.mastermind.rmi;

import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Response;
import com.upm.master.mastermind.model.ValidFigures;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MasterMindOperations extends Remote {
   public static String SERVICE_NAME = "MasterMindOperations";

   // Global
   void setNextState() throws RemoteException;

   // --- Start
   void initializeGame() throws RemoteException;
   Code getSecretCode() throws RemoteException;

   //---- Play
   boolean continueGame() throws RemoteException;
   Response evaluate(Code guessCode) throws RemoteException;
   ValidFigures getValidFigures() throws RemoteException;
   int getAttempt() throws RemoteException;  
   int getMaxAttempt() throws RemoteException;  
   void codeBreakerWins() throws RemoteException;  
   void codeMakerWins() throws RemoteException;  
   void registry() throws RemoteException;  
   void undo() throws RemoteException;  
   void redo() throws RemoteException;  
   void quit() throws RemoteException;  
   boolean codeDiscovered() throws RemoteException;  
   boolean isAborted() throws RemoteException;  
   boolean canApplyUndo() throws RemoteException;  
   boolean canApplyRedo() throws RemoteException;

   // --- Resume
   void resume() throws RemoteException;
   void stop() throws RemoteException;
}

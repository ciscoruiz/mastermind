package com.upm.master.mastermind.runtime;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.rmi.MasterMindOperations;
import com.upm.master.mastermind.view.ViewsContainer;
import com.upm.master.mastermind.view.console.ViewsContainerConsole;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MasterMindServer extends MasterMindOverModel {
   static public MasterMind create() {
      ViewsContainer viewsContainer = new ViewsContainerConsole();
      return new MasterMindServer(viewsContainer);
   }

   MasterMindServer(ViewsContainer viewsContainer) {
      super(viewsContainer);
   }

   public void play() {
      try {
         Registry registry = java.rmi.registry.LocateRegistry.createRegistry(2022);

         MasterMindOperations masterMindServerOperations = new MasterMindServerOperations(this, state);

         // See https://stackoverflow.com/questions/10454037/java-security-accesscontrolexception-access-denied-java-io-filepermission
         // jdks/openjdk-15.0.1/conf/security/java.policy
         // run: ~/IdeaProjects/mastermind/out/production/mastermind$ rmiregistry -J-Djava.class.path=./
         //      from the directory where the classes are compiled
         registry.bind(MasterMindOperations.SERVICE_NAME, masterMindServerOperations);

         System.out.println("MasterMindServer accepting requests ...");
      }
      catch (Exception e) {
         System.err.println("Server exception: " + e.toString());
         e.printStackTrace();
      }
   }
}

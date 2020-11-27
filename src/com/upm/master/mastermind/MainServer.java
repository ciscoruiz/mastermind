package com.upm.master.mastermind;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.impl.MasterMindServer;
import com.upm.master.mastermind.impl.MasterMindStandalone;

public class MainServer {
   public static void main(String[] args) {
      MasterMind masterMind = MasterMindServer.create();
      masterMind.play();
   }
}

package com.upm.master.mastermind;

import com.upm.master.mastermind.runtime.MasterMindServer;

public class MainServer {
   public static void main(String[] args) {
      MasterMind masterMind = MasterMindServer.create();
      masterMind.play();
   }
}

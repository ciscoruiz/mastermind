package com.upm.master.mastermind;

import com.upm.master.mastermind.runtime.MasterMindClient;

public class MainClient {
   public static void main(String[] args) {
      MasterMind masterMind = MasterMindClient.create();
      masterMind.play();
   }
}

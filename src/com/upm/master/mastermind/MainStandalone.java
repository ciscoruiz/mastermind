package com.upm.master.mastermind;

import com.upm.master.mastermind.impl.MasterMindStandalone;

public class MainStandalone {
   public static void main(String[] args) {
      MasterMind masterMind = MasterMindStandalone.create();
      masterMind.play();
   }
}

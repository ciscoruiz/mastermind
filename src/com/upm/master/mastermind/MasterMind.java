package com.upm.master.mastermind;

import com.upm.master.mastermind.controller.GameController;
import com.upm.master.mastermind.controller.MasterMindController;
import com.upm.master.mastermind.model.Configuration;
import com.upm.master.mastermind.model.ValidFigures;
import com.upm.master.mastermind.view.console.GameConsole;
import com.upm.master.mastermind.view.console.MasterMindConsole;

public class MasterMind {
    public static void main(String[] args) {
       Configuration configuration = createHardcodedConfiguration();
       GameController gameController = new GameController(new GameConsole());

       MasterMindController masterMindController = new MasterMindController(new MasterMindConsole(), configuration, gameController);

       masterMindController.play();
    }

   private static Configuration createHardcodedConfiguration() {
      Configuration configuration = new Configuration();

      configuration.setMaxAttempt(5);
      ValidFigures validFigures = new ValidFigures();
      validFigures.add('A').add('B').add('C').add('D').add('E').add('F');
      configuration.setValidFigures(validFigures);

      return configuration;
   }
}

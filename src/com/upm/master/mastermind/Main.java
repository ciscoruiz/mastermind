package com.upm.master.mastermind;

import com.upm.master.mastermind.model.Configuration;
import com.upm.master.mastermind.model.ValidFigures;
import com.upm.master.mastermind.view.ViewsContainer;
import com.upm.master.mastermind.view.console.ViewsContainerConsole;

public class Main {
    public static void main(String[] args) {
       ViewsContainer viewsContainer = new ViewsContainerConsole();
       Configuration configuration = createHardcodedConfiguration();

       MasterMind masterMind = new MasterMind(viewsContainer, configuration);

       masterMind.play();
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

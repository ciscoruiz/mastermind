package com.upm.master.mastermind.view.auto;

import com.upm.master.mastermind.model.Configuration;
import com.upm.master.mastermind.model.ValidFigures;
import com.upm.master.mastermind.view.ConfigurationView;

public class ConfigurationAuto implements ConfigurationView {
   @Override
   public void setMaxAttempt(Configuration configuration) {
      configuration.setMaxAttempt(5);
   }

   @Override
   public void setMaxPlay(Configuration configuration) {
      configuration.setMaxPlay(3);
   }

   @Override
   public void setValidFigures(Configuration configuration) {
      ValidFigures validFigures = new ValidFigures();
      validFigures.add('A').add('B').add('C').add('D').add('E').add('F');
      configuration.setValidFigures(validFigures);
   }
}

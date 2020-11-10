package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.model.Configuration;
import com.upm.master.mastermind.view.ConfigurationView;

public class ConfigurationController {
   Configuration configuration = new Configuration();
   ConfigurationView view;

   public ConfigurationController(ConfigurationView view) {
      this.view = view;
   }

   public Configuration requestConfiguration() {
      view.setMaxPlay(configuration);
      view.setMaxAttempt(configuration);
      view.setValidFigures(configuration);
      return configuration;
   }
}

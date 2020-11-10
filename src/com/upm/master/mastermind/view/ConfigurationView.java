package com.upm.master.mastermind.view;

import com.upm.master.mastermind.model.Configuration;

public interface ConfigurationView {
   public void setMaxAttempt(Configuration configuration);
   public void setMaxPlay(Configuration configuration);
   public void setValidFigures(Configuration configuration);
}

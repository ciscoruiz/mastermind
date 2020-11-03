package com.upm.master.mastermind.view;

import com.upm.master.mastermind.Configuration;
import com.upm.master.mastermind.ValidFigures;

public interface ConfigurationView {
   public void setMaxAttempt(Configuration configuration);
   public void setMaxPlay(Configuration configuration);
   public void setValidFigures(Configuration configuration);
}

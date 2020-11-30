package com.upm.master.mastermind;

import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.controller.ResumeController;
import com.upm.master.mastermind.controller.StartController;

public interface MasterMind {
   void play();
   StartController createStartController();
   PlayController createPlayController();
   ResumeController createResumeController();
}

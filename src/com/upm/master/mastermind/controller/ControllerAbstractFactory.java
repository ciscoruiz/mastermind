package com.upm.master.mastermind.controller;

public interface ControllerAbstractFactory {
   StartController createStartController();
   PlayController createPlayController();
   ResumeController createResumeController();
}

package com.upm.master.mastermind.model;

public class State {

   public enum Value {
      INITIAL, PLAYING, RESUME, STOP, QUIT
   }

   private Value value = Value.INITIAL;

   public Value getValue() {
      return value;
   }

   public void setValue(Value value) {
      this.value = value;
   }

   public void setNextValue() {
      switch (value) {
         case INITIAL:
            value = Value.PLAYING;
            break;
         case PLAYING:
            value = Value.RESUME;
            break;
         default:
            throw new RuntimeException(("Can not calculate the next state for " + value.name()));
      }
   }

   public boolean continuePlaying() {
      return value != Value.STOP && value != Value.QUIT;
   }
}

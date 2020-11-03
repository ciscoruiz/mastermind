package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.Rating;
import com.upm.master.mastermind.view.MasterMindView;

public class MasterMindConsole implements MasterMindView {
   private CharacterReader reader = new CharacterReader();

   @Override
   public void startGame(MasterMind masterMind) {
      System.out.println("Start play number " + masterMind.getNplay() + " of " + masterMind.getMaxPlay());
   }

   @Override
   public boolean askPlayNewGame(MasterMind masterMind) {
      Character character = null;

      while (character == null) {
         System.out.println("Do you want to start a new game (Y/N)?");
         character = reader.apply();

         if (character == null)
            continue;

         character = Character.toUpperCase(character);

         if (character.equals('N'))
            return false;

         if (character.equals('Y'))
            return true;

         character = null;
      }

      return false;
   }

   @Override
   public void endGame(MasterMind masterMind) {
      Rating rating = masterMind.getRating();
      System.out.println("------- SUMMARY --------");
      System.out.println("Play(s) for Breaker=" + rating.getBreaker());
      System.out.println("Play(s) for Maker=" + rating.getMaker());
   }
}

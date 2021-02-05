package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.model.MasterMind;
import com.upm.master.mastermind.model.Rating;
import com.upm.master.mastermind.view.MasterMindView;

import java.util.Arrays;
import java.util.Vector;

public class MasterMindConsole implements MasterMindView {
   private KeyboardReader reader = new KeyboardReader();
   private final Character characters[] = { 'Y', 'N' };

   @Override
   public void startGame(MasterMind masterMind) {
      System.out.println("Start play MasterMind");
   }

   @Override
   public boolean wantToPlayNewGame(MasterMind masterMind) {
      Character character = reader.readCharacter("Do you want to start a new game?", new Vector<>(Arrays.asList(characters)));

      character = Character.toUpperCase(character);

      return character.equals('Y');
   }

   @Override
   public void endGame(MasterMind masterMind) {
      Rating rating = masterMind.getRating();
      System.out.println("------- SUMMARY --------");
      System.out.println("Play(s) for Breaker=" + rating.getBreaker());
      System.out.println("Play(s) for Maker=" + rating.getMaker());
   }
}

package com.upm.master.mastermind.view.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class CharacterReader {
   private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

   public Character apply(String text, Vector<Character> validCharacters) {
      Character result;

      while (true) {
         printValidCharacters(text, validCharacters);

         if ((result = apply()) == null)
            continue;

         if (validCharacters.contains(result)) {
            break;
         }

         System.out.println("Character " + result + " is not a valid selection");
      }

      return result;
   }

   private Character apply() {
      Character result = null;
      try {
         String line;
         while ((line = bufferedReader.readLine()) == null || line.isEmpty()) ;
            result = line.charAt(0);
      } catch (IOException none) {
      }

      return result;
   }

   private void printValidCharacters(String text, Vector<Character> validCharacters) {
      System.out.print(text + ": ");
      validCharacters.forEach((character) -> {
         System.out.print(character + " ");
      });
      System.out.println();
   }
}

package com.upm.master.mastermind.view.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class KeyboardReader {
   private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

   public Character readCharacter(String text, Vector<Character> validCharacters) {
      Character result;

      while (true) {
         printValidCharacters(text, validCharacters);

         if ((result = readCharacter()) == null)
            continue;

         if (validCharacters.contains(result)) {
            break;
         }

         System.out.println("Character " + result + " is not a valid selection");
      }

      return result;
   }

   public String readString(String text, int size, Vector<Character> validCharacters) {
      String result = null;
      boolean invalidString = true;

      while (invalidString) {
         printValidCharacters(text + "(Length=" + size + ")", validCharacters);

         try {
            while ((result = bufferedReader.readLine()) == null || result.isEmpty());

            if (result.length() != size) {
               System.out.println("String requires a length of " + size + " characters");
               continue;
            }

            if (!isValid(result, validCharacters)) {
               continue;
            }

            invalidString = false;
         }
         catch (IOException e) {
            e.printStackTrace();
         }
      }

      return result;
   }

   private Character readCharacter() {
      Character result = null;
      try {
         String line;
         while ((line = bufferedReader.readLine()) == null || line.isEmpty()) ;
         result = line.charAt(0);
      } catch (IOException none) {
      }

      return result;
   }

   private boolean isValid(String value, Vector<Character> validCharacters) {
      for (int ii = 0; ii < value.length(); ++ ii) {
         if (!validCharacters.contains(value.charAt(ii))) {
            System.out.println("Character " + value.charAt(ii) + " is not a valid selection");
            return false;
         }
      }

      return true;
   }

   private void printValidCharacters(String text, Vector<Character> validCharacters) {
      System.out.print(text + ": ");
      validCharacters.forEach((character) -> { System.out.print(character + " "); });
      System.out.println();
   }
}

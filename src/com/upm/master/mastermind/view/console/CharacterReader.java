package com.upm.master.mastermind.view.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CharacterReader {
   private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

   public Character apply()  {
      Character result = null;
      try {
         String line;
         while ((line = bufferedReader.readLine()) == null || line.isEmpty()) ;
         result = line.charAt(0);
      }
      catch (IOException none) {
      }

      return result;
   }
}

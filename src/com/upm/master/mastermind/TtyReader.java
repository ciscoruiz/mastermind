package com.upm.master.mastermind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TtyReader implements Reader {
   private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


   @Override
   public Character readCharacter() throws IOException {
      String line;
      while ((line = bufferedReader.readLine()) == null || line.isEmpty());
      return line.charAt(0);
   }

   @Override
   public Character readYesOrNo() {
      Character result = 0;
      do {
         try {
            result = Character.toUpperCase(readCharacter());
         } catch (IOException e) {
         }
      } while (!result.equals('Y') && !result.equals('N'));
      return result;
   }
}

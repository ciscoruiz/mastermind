package com.upm.master.mastermind;

import java.io.IOException;

public interface Reader {
   Character readCharacter() throws IOException;
   Character readYesOrNo();
}

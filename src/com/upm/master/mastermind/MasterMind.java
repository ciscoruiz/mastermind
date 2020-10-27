package com.upm.master.mastermind;

public final class MasterMind {
   int maxAttempt;
   int maxPlay;
   ValidFigures validFigures;
   Visualizer visualizer;
   Reader reader;
   Rating rating;

   public MasterMind(int maxAttempt, int maxPlay, ValidFigures validFigures, Visualizer visualizer, Reader reader) {
      this.maxAttempt = maxAttempt;
      this.maxPlay = maxPlay;
      this.validFigures = validFigures;
      this.visualizer = visualizer;
      this.reader = reader;
      rating = new Rating();
   }

   void start() {
      if (!validFigures.isCompleted())
         throw new RuntimeException("Figures are not completed");

      int nplay = 0;
      boolean startNewGame = true;

      while (nplay < maxPlay && startNewGame) {
         visualizer.startPlay(nplay, maxPlay);
         Game game = new Game(this);
         rating.sum(game.play());
         visualizer.summary(rating);
         startNewGame = (nplay >= maxPlay) ? false: askStartNewGame();
         nplay ++;
      }
   }

   private boolean askStartNewGame() {
      visualizer.askNextPlay();
      return reader.readYesOrNo().equals('Y');
   }
}

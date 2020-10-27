package com.upm.master.mastermind;

public class Main {
    public static void main(String[] args) {
        ValidFigures validFigures = new ValidFigures();
        validFigures.add('A').add('B').add('C').add('D').add('E').add('F');

        Visualizer visualizer = new TtyVisualizer();
        Reader reader = new TtyReader();

	     MasterMind masterMind = new MasterMind(5, 3, validFigures, visualizer, reader);

	     masterMind.play();
    }
}

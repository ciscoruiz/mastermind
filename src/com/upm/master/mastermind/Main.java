package com.upm.master.mastermind;

public class Main {
    public static void main(String[] args) {
        ValidFigures validFigures = new ValidFigures();
        validFigures.add('1').add('2').add('3').add('4').add('5').add('6');

        Visualizer visualizer = new TtyVisualizer();
        Reader reader = new TtyReader();

	     MasterMind masterMind = new MasterMind(5, 3, validFigures, visualizer, reader);

	     masterMind.start();
    }
}

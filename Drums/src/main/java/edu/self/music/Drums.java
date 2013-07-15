package edu.self.music;

import java.lang.Thread;

import java.util.Random;
import java.util.Scanner;

import org.jfugue.Player;
import org.jfugue.Pattern;
import org.jfugue.Rhythm;

public class Drums {

    public static void main(String[] args) {

        Player player = new Player();
		Rhythm rhythm = new Rhythm();
		
		rhythm.setLayer(1, "o...o...o..ooo..");
		rhythm.setLayer(2, "..*...*...*...*.");
		rhythm.setLayer(3, "^^^^^^^^^^^^^^^^");
		rhythm.setLayer(4, "!...............");

		rhythm.addSubstitution('o', "[BASS_DRUM]s");
		rhythm.addSubstitution('*', "[ACOUSTIC_SNARE]s");
		rhythm.addSubstitution('^', "[PEDAL_HI_HAT]s");
		rhythm.addSubstitution('!', "[CRASH_CYMBAL_1]s");
		rhythm.addSubstitution('.', "Rs");

        Pattern pattern = rhythm.getPattern();
		pattern.repeat(4);

        player.play(pattern);
        player.close();
    }
}

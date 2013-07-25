package edu.self.music;

import java.lang.Thread;

import java.util.Random;

import org.jfugue.Player;
import org.jfugue.Pattern;

public class Counterpoint {

    public static void main(String[] args) {

		int SONG_LENGTH = 16;

		// start at C and shift notes
        String[] majorNotes = {
            "C", "D", "E", "F", "G", "A", "B", "C6"
        };

		String[] minorNotes = {
			"A"
		};

        int[] consonance = {
            3, 5, 8
        };

        int[] dissonance = {
            2, 7
        };

        String cantusNotes = "";
        String counterNotes = "";

        Player player = new Player();

		//	                              1       2       3       4
		Pattern rests = new Pattern("V2 R R R R R R R R R R R R R R R R ");
        Pattern cantus = new Pattern("T140 V1 I[MARIMBA] ");
        Pattern fcp = new Pattern("T140 V2 I[XYLOPHONE] ");

        Pattern song = new Pattern();

        Random rand = new Random();
        int cantusRandom = 0;
		int counterRandom = 0;

        for (int i=0; i < SONG_LENGTH; i++) {
            // adding theme
            cantusRandom = rand.nextInt(majorNotes.length);
            cantusNotes += majorNotes[cantusRandom] + "w ";

			// species 1 counterpoint
			counterRandom = consonance[rand.nextInt(consonance.length)];
			// b has no fifth
			while (cantusRandom == 6 && counterRandom == 5)
				counterRandom = consonance[rand.nextInt(consonance.length)];
			if (cantusRandom + counterRandom >= majorNotes.length) 
				counterNotes += majorNotes[cantusRandom + counterRandom - majorNotes.length] + "w ";
			else 
				counterNotes += majorNotes[cantusRandom + counterRandom] + "w ";
        }


		//// melodic inversion
		////          1      2             3   4
        //cantus.add("Fh. Cq C6h. D6i. E6s G6w A6w ");
		////       1       2          3   4
		//fcp.add("Fh. B6q Bh. Ai. Gs E6w D7w ");

		//// retrograde
		////       1   2            3    4
        //fcp.add("A6w G6w E6s D6i. C6h. Cq Fh. ");
        
        System.out.println(cantusNotes);
        System.out.println(counterNotes);

        cantus.add(cantusNotes);
        fcp.add(counterNotes);

        song.add(cantus);
        song.add(fcp);

        player.play(song);

        player.close();
    }
}

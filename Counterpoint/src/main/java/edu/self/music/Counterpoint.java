package edu.self.music;

import java.lang.Thread;

import java.util.Random;

import org.jfugue.Player;
import org.jfugue.Pattern;

public class Counterpoint {

    public static void main(String[] args) {

		int SONG_LENGTH = 8;

		// JFugue inits
		//	                            1       2       3       4
		Pattern rests = new Pattern("V2 R R R R R R R R R R R R R R R R ");
        Pattern cantus = new Pattern("T140 V1 I[MARIMBA] ");
        Pattern fcp = new Pattern("T140 V2 I[XYLOPHONE] ");
        Pattern scp = new Pattern("T140 V2 I[GLOCKENSPIEL] ");
        Pattern song = new Pattern();

        Player player = new Player();

		// Music inits
		// start at C and shift notes
        String[] majorNotes = {
            "C", "D", "E", "F", "G", "A", "B", "C6"
        };

        int[] consonance = {
            3, 5
        };

        int[] dissonance = {
            2, 7
        };

        String cantusString = "";
        String counterString = "";
		String counterString2 = "";

        Random rand = new Random();

		// establishing first note
		int cantusNote = rand.nextInt(majorNotes.length);
		int counterNote;
		int counterNote2;

		boolean upBeat = false;

		// TODO: clean this mess up
        for (int i=0; i < SONG_LENGTH; i++) {

			// create theme
			if (rand.nextInt(2) == 0) {
				if (cantusNote - 1 < 0)
					cantusNote = majorNotes.length - 1;
				else
					cantusNote -= 1;
			}
			else {
				if (cantusNote + 1 == majorNotes.length)
					cantusNote = 0;
				else
					cantusNote += 1;
			}

			cantusString += majorNotes[cantusNote] + "w ";


			// species 1 counterpoint
			//
			//
			counterNote = consonance[rand.nextInt(consonance.length)];
			// b has no fifth... kinda
			while (cantusNote == 6 && counterNote == 5)
				counterNote = consonance[rand.nextInt(consonance.length)];

			// avoid unison except beginning and end
			if (i != 0 || i != SONG_LENGTH-1) {
				while (counterNote == 8)
					counterNote = consonance[rand.nextInt(consonance.length)];
			}

			// up or down
			if (rand.nextInt(2) == 0) {
				if (cantusNote - counterNote > 0) 
					counterString += majorNotes[cantusNote - counterNote] + "w ";
				else 
					counterString += majorNotes[cantusNote - counterNote + majorNotes.length-1] + "w ";
			}
			else {
				if (cantusNote + counterNote >= majorNotes.length) 
					counterString += majorNotes[cantusNote + counterNote - majorNotes.length] + "w ";
				else 
					counterString += majorNotes[cantusNote + counterNote] + "w ";
			}
			//
			//
			//


			// species 2 counterpoint
			//
			//
			for (int n=0; n < 2; n++) {
				counterNote2 = consonance[rand.nextInt(consonance.length)];
				while (cantusNote == 6 && counterNote2 == 5)
					counterNote2 = consonance[rand.nextInt(consonance.length)];

				// avoid unison except beginning and end
				// also possibly skip first note
				if (i != 0 || i != SONG_LENGTH-1) {
					while (counterNote2 == 8)
						counterNote2 = consonance[rand.nextInt(consonance.length)];
				} else if (i == 0) {
					if (rand.nextInt(2) == 0)
						counterString2 += "Rh ";
						upBeat = true;
				}

				if (upBeat) {
					continue;
				} else {
					// up or down
					if (rand.nextInt(2) == 0) {
						if (cantusNote - counterNote2 > 0) 
							counterString2 += majorNotes[cantusNote - counterNote2] + "h ";
						else 
							counterString2 += majorNotes[cantusNote - counterNote2 + majorNotes.length-1] + "h ";
					}
					else {
						if (cantusNote + counterNote2 >= majorNotes.length) 
							counterString2 += majorNotes[cantusNote + counterNote2 - majorNotes.length] + "h ";
						else 
							counterString2 += majorNotes[cantusNote + counterNote2] + "h ";
					}
				}
			}
        }


		//// melodic inversion
		////          1      2             3   4
        //cantus.add("Fh. Cq C6h. D6i. E6s G6w A6w ");
		////       1       2          3   4
		//fcp.add("Fh. B6q Bh. Ai. Gs E6w D7w ");

		//// retrograde
		////       1   2            3    4
        //fcp.add("A6w G6w E6s D6i. C6h. Cq Fh. ");
        
        System.out.println(cantusString);
        System.out.println(counterString);
		System.out.println(counterString2);

		cantus.add(cantusString);
		fcp.add(counterString);
		scp.add(counterString2);

        song.add(cantus);
        song.add(cantus);
        song.add(fcp);
        song.add(scp);

        player.play(song);

        player.close();
    }
}

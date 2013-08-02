package edu.self.music;

import java.lang.Thread;

import java.util.Random;

import org.jfugue.Player;
import org.jfugue.Pattern;

public class Counterpoint {

	// Music inits
	static int SONG_LENGTH = 8;

	// start at C and shift notes
	static String[] majorNotes = {
		"C", "D", "E", "F", "G", "A", "B", "C6"
	};
	static String[] minorNotes = {
		"C", "D", "Eb", "F", "G", "Ab", "Bb", "C6"
	};

	static int[] consonance = {
		3, 5
	};

	static int[] dissonance = {
		2, 7
	};

	private static String speciesOne(int cantusNote, int pos, Random rand) {
		// species 1 counterpoint
		//
		//
		int counterNote = consonance[rand.nextInt(consonance.length)];
		// b has no fifth... kinda
		while (cantusNote == 6 && counterNote == 5)
			counterNote = consonance[rand.nextInt(consonance.length)];

		// avoid unison except beginning and end
		if (pos != 0 || pos != SONG_LENGTH-1) {
			while (counterNote == 8)
				counterNote = consonance[rand.nextInt(consonance.length)];
		}

		// up or down
		if (rand.nextInt(2) == 0) {
			if (cantusNote - counterNote > 0) 
				return majorNotes[cantusNote - counterNote] + "w ";
			else 
				return majorNotes[cantusNote - counterNote + majorNotes.length-1] + "w ";
		}
		else {
			if (cantusNote + counterNote >= majorNotes.length) 
				return majorNotes[cantusNote + counterNote - majorNotes.length] + "w ";
			else 
				return majorNotes[cantusNote + counterNote] + "w ";
		}
	}

	private static String speciesTwo(int cantusNote, int pos, Random rand) {
		String counterString = "";
		for (int n=0; n < 2; n++) {
			int counterNote2 = consonance[rand.nextInt(consonance.length)];
			while (cantusNote == 6 && counterNote2 == 5)
				counterNote2 = consonance[rand.nextInt(consonance.length)];

			// avoid unison except beginning and end
			// also possibly skip first note
			if (pos != 0 || pos != SONG_LENGTH-1) {
				while (counterNote2 == 8)
					counterNote2 = consonance[rand.nextInt(consonance.length)];
			} else if (pos == 0) {
				if (rand.nextInt(2) == 0)
					counterString += "Rh ";
			}

			// up or down
			if (rand.nextInt(2) == 0) {
				if (cantusNote - counterNote2 > 0) 
					counterString += majorNotes[cantusNote - counterNote2] + "h ";
				else 
					counterString += majorNotes[cantusNote - counterNote2 + majorNotes.length-1] + "h ";
			}
			else {
				if (cantusNote + counterNote2 >= majorNotes.length) 
					counterString += majorNotes[cantusNote + counterNote2 - majorNotes.length] + "h ";
				else 
					counterString += majorNotes[cantusNote + counterNote2] + "h ";
			}
		}
		return counterString;
	}

    public static void main(String[] args) {


		// JFugue inits
		//	                            1       2       3       4
		Pattern rests = new Pattern("V2 R R R R R R R R R R R R R R R R ");
        Pattern cantus = new Pattern("T140 V1 I[MARIMBA] ");
        Pattern fcp = new Pattern("T140 V2 I[XYLOPHONE] ");
        Pattern scp = new Pattern("T140 V2 I[GLOCKENSPIEL] ");
        Pattern song = new Pattern();

        Player player = new Player();

		// init music strings
        String cantusString = "";
        String speciesOneString = "";
		String speciesTwoString = "";

        Random rand = new Random();

		// init first note
		int cantusNote = rand.nextInt(majorNotes.length);

		// random major/minor
		if (rand.nextInt(2) == 0)
			majorNotes = minorNotes;

        for (int i=0; i < SONG_LENGTH; i++) {

			// create theme
			//
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

			speciesOneString += speciesOne(cantusNote, i, rand);

			speciesTwoString += speciesTwo(cantusNote, i, rand);
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
        System.out.println(speciesOneString);
		System.out.println(speciesTwoString);

		cantus.add(cantusString);
		fcp.add(speciesOneString);
		scp.add(speciesTwoString);

        song.add(cantus);
        song.add(cantus);
        song.add(fcp);
        song.add(scp);

        player.play(song);

        player.close();
    }
}

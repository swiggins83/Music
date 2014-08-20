package edu.self.music;

import java.lang.Thread;

import java.util.Random;

import org.jfugue.Player;
import org.jfugue.Pattern;
import org.jfugue.Note;

public class Counterpoint {

	static int SONG_LENGTH = 1024;
	static int SONG_TEMPO = 60;

	static int[] consonance = {
		0, 3, 5, 7, 12
	};

	static double[] rhythms = {
		1, 0.5, 0.25, 0.125
	};

	static Random rand = new Random();

	private static Note speciesOneCounterpoint(Note cantusNote) {

		int counterHarmony = consonance[rand.nextInt(consonance.length)];
		Note counterNote = new Note((byte)(cantusNote.getValue() + ((1-2*rand.nextInt(2)) * counterHarmony)));
		counterNote.setDecimalDuration(cantusNote.getDecimalDuration());

		return counterNote;

	}

	private static Pattern speciesTwoCounterpoint(Note cantusNote, int pos) {

		Pattern p = new Pattern();
		int counterHarmony = 0;

		for (int n=0; n < 2; n++) {
			counterHarmony = consonance[rand.nextInt(consonance.length)];
			Note counterNote = new Note((byte)(cantusNote.getValue() + ((1-2*rand.nextInt(2)) * counterHarmony)));

			counterNote.setDecimalDuration(cantusNote.getDecimalDuration() / 2);
			p.addElement(counterNote);
		}

		return p;
	}

	private static Note melodicInversion(Note inverse, int pos, int difference) {

		Note counterNote = new Note((byte)(inverse.getValue() + difference));
		return counterNote;

	}

    public static void main(String[] args) {

		// JFugue inits
		//	                            1       2       3       4
		Pattern cantus = new Pattern("T"+SONG_TEMPO+" V1 I[CHURCH_ORGAN] ");
		Pattern cpo = new Pattern("T"+SONG_TEMPO+" V2 I[CELLO]    ");
		Pattern cpt = new Pattern("T"+SONG_TEMPO+" V3 I[GLOCKENSPIEL]   ");
		Pattern inverse = new Pattern("T"+SONG_TEMPO+" V4 I[XYLOPHONE]  ");
		Pattern song = new Pattern();

		Player player = new Player();

		// init first note
		byte noteValue = (byte)(rand.nextInt(36) + 60);
		double rhythm = rhythms[rand.nextInt(rhythms.length-1)];
		Note first = new Note(noteValue);
		first.setDecimalDuration(rhythm);
		cantus.addElement(first);

		cpo.addElement(speciesOneCounterpoint(first));

		// species two returns a pattern, not a note
		// hence using add rather than addElement
		cpt.add(speciesTwoCounterpoint(first, 0));

		inverse.addElement(first);
		Note lastInverse = first;

		Note cantusNote = new Note();
		byte lastNote = (byte)0;

		int difference = 0;

        // main song loop
		for (int i=0; i < SONG_LENGTH; i++) {

			// create theme

			difference = 1-(2*(rand.nextInt(2)));

			rhythm = rhythms[rand.nextInt(rhythms.length-1)];

			if (lastNote != 0) {
				cantusNote.setValue((byte)(lastNote - difference));
			} else {
				cantusNote.setValue((byte)(noteValue - difference));
			}
			cantusNote.setDecimalDuration(rhythm);
			cantus.addElement(cantusNote);

			lastNote = cantusNote.getValue();

			cpo.addElement(speciesOneCounterpoint(cantusNote));

			// species two returns a pattern, not a note
			// hence using add rather than addElement
			cpt.add(speciesTwoCounterpoint(cantusNote, i));

			lastInverse = melodicInversion(lastInverse, i, difference);
			lastInverse.setDecimalDuration(cantusNote.getDecimalDuration());
			inverse.addElement(lastInverse);

		}

		System.out.println("Theme:                    " + cantus.getMusicString());
		System.out.println("Species one counterpoint: " + cpo.getMusicString());
		System.out.println("Species two counterpoint: " + cpt.getMusicString());
		System.out.println("Melodic inverse of theme: " + inverse.getMusicString());

		song.add(cantus);
		song.add(cpo);
		song.add(cpt);
        // songs sound kinda gross with this on
		song.add(inverse);

		player.play(song);

		player.close();
	}

}


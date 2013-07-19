package edu.self.music;

import java.lang.Thread;

import java.util.Random;
import java.util.Scanner;

import org.jfugue.Player;
import org.jfugue.Pattern;

public class Music {

    public static void main(String[] args) throws InterruptedException {
		String[] noteLength = {
			"w", "h", "q", "i", "s"
		};
		
        String[] notes = {
            "Cmaj", "Ebmaj", "Fmaj", "Gmaj", "Abmaj", "Bbmaj",
            "Cmaj^", "Ebmaj^", "Fmaj^", "Gmaj^", "Abmaj^", "Bbmaj",
            "Cmaj^^", "Ebmaj^^", "Fmaj^^", "Gmaj^^", "Abmaj^^", "Bbmaj^^",
        };

        Pattern pattern = new Pattern();
		Player player = new Player();
		Random rnd = new Random();

		while(true) {
            player.play("I80 " + notes[rnd.nextInt(notes.length)]
						+ noteLength[rnd.nextInt(noteLength.length)]);
        }
        //player.close();
    }
}

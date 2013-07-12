package music;

import java.lang.Thread;

import java.util.Random;
import java.util.Scanner;

import org.jfugue.Player;
import org.jfugue.Pattern;

public class Music {

    public static void main(String[] args) throws InterruptedException {

        String[] notes = {
            "Cmaj", "Ebmaj", "Fmaj", "Gmaj", "Abmaj", "Bbmaj",
            "Cmaj^", "Ebmaj^", "Fmaj^", "Gmaj^", "Abmaj^", "Bbmaj",
            "Cmaj^^", "Ebmaj^^", "Fmaj^^", "Gmaj^^", "Abmaj^^", "Bbmaj",
        };
        Pattern pattern = new Pattern();
        while(true) {
            Player player = new Player();
            int rnd = new Random().nextInt(notes.length);
            player.play("I80 " + notes[rnd]);
            Thread.sleep(500);
            player.close();
        }
    }
}

package edu.self.music;

import java.lang.Thread;

import java.util.Random;
import java.util.Scanner;

import org.jfugue.Player;
import org.jfugue.Pattern;

public class Voices {

    public static void main(String[] args) {

        Player player = new Player();

        Pattern trumpet1 = new Pattern("V1 I[Trumpet]");
        Pattern trumpet2 = new Pattern("V2 I[Trumpet]");
        Pattern tuba = new Pattern("V3 I[Tuba]");

        Pattern song = new Pattern();

        trumpet1.add("X[Volume]=7500 C E D F E G A B C6q. R");
        trumpet2.add("X[Volume]=7500 E G F A G B C G Eq. R");
        tuba.add("X[Volume]=7500 C3h C3h C3h F2 G2 C2q. R");
        
        song.add(trumpet1);
        song.add(trumpet2);
        song.add(tuba);

        player.play(song);
        player.close();
    }
}

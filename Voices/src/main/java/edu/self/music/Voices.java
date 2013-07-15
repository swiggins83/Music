package edu.self.music;

import java.lang.Thread;

import java.util.Random;
import java.util.Scanner;

import org.jfugue.Player;
import org.jfugue.Pattern;

public class Voices {

    public static void main(String[] args) {

        Player player = new Player();

        Pattern voice1 = new Pattern("V1 ");
        Pattern voice2 = new Pattern("V2 ");
        Pattern song = new Pattern();

        voice1.add("C E D F E G A B C6");
        voice2.add("E G F A G B C G E");
        
        song.add(voice1);
        song.add(voice2);

        player.play(song);
        player.close();
    }
}

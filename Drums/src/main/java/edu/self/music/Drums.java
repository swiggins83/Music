package edu.self.music;

import java.lang.Thread;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.jfugue.Player;
import org.jfugue.Pattern;
import org.jfugue.Rhythm;

public class Drums {

    public static void main(String[] args) {

        // init
        Player player = new Player();
        ArrayList<Rhythm> rhythmList = new ArrayList<Rhythm>();
		Rhythm rhythm1 = new Rhythm();
		Rhythm rhythm1Var = new Rhythm();
		Rhythm rhythm1Long = new Rhythm();
        Pattern pad = new Pattern("V2 I[POLYSYNTH]");

        rhythmList.add(rhythm1);
        rhythmList.add(rhythm1Var);
        rhythmList.add(rhythm1Long);
        
        // set drum layers
		rhythm1.setLayer(1, "o.........o...o.o....o..o.......");
		rhythm1.setLayer(2, "....*.......*.......*.......*...");
		rhythm1.setLayer(3, "..^...^...^...^...^...^...^...^.");
		rhythm1.setLayer(4, "................................");

		rhythm1Var.setLayer(1, "..o...o.o....o..o.......");
		rhythm1Var.setLayer(2, "....*.......*.......*...");
		rhythm1Var.setLayer(3, "..^...^...^...^...^...^.");
		rhythm1Var.setLayer(4, "........................");
		
		rhythm1Long.setLayer(1, "o.........o...o.o....o..o.......................................................");
		rhythm1Long.setLayer(2, "....*.......*.......*.......*.......*.......*.......*.......*.......*.......*...");
		rhythm1Long.setLayer(3, "..^...^...^...^...^...^...^...^...^...^...^...^...^...^...^...^...^...^...^...^.");
		rhythm1Long.setLayer(4, "!...............................................................................");

        // set drum instrument substitution
        for( Rhythm r : rhythmList )
        {
            r.addSubstitution('o', "[BASS_DRUM]s");
            r.addSubstitution('*', "[ACOUSTIC_SNARE]s");
            r.addSubstitution('^', "[PEDAL_HI_HAT]s");
            r.addSubstitution('!', "[CRASH_CYMBAL_1]s");
            r.addSubstitution('.', "Rs");
        }

        Pattern rhythm1Pat = rhythm1.getPattern();
        Pattern rhythm1VarPat = rhythm1Var.getPattern();
        Pattern rhythm1LongPat = rhythm1Long.getPattern();

        //       pad rest layer
        //       1  2  3  4  5  6  7  C7M/E        C7M/G      Em      G
        pad.add("Rw Rw Rw Rw Rw Rw E+B+E+G+C+Ew G+C+E+B+Ew E+B+E+Gw G+D+Bw Rw E+B+E+G+C+Ew G+C+E+B+Ew E+B+E+Gw G+D+Bw Rw ");


		Pattern wholeSong = new Pattern();
		
        wholeSong.add(pad);
		// mostly kinda right
		// intro
		wholeSong.add(rhythm1Pat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		// other instruments
		wholeSong.add(rhythm1LongPat);
		wholeSong.add(rhythm1LongPat);
		wholeSong.add(rhythm1LongPat);
		wholeSong.add(rhythm1Pat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		// whos in bunker
		wholeSong.add(rhythm1LongPat);
		wholeSong.add(rhythm1LongPat);
		wholeSong.add(rhythm1LongPat);
		wholeSong.add(rhythm1LongPat);
		// chorus
		wholeSong.add(rhythm1LongPat);
		wholeSong.add(rhythm1LongPat);
		// ice age coming
		wholeSong.add(rhythm1Pat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
        // ice age coming
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
        // this is really happening
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		wholeSong.add(rhythm1VarPat);
		//chorus
		wholeSong.add(rhythm1LongPat);
		wholeSong.add(rhythm1LongPat);


        player.play(wholeSong);
        player.close();
    }
}

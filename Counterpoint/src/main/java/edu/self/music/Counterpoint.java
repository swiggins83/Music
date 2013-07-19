package edu.self.music;

import java.lang.Thread;

import java.util.Random;
import java.util.Scanner;

import org.jfugue.Player;
import org.jfugue.Pattern;

public class Counterpoint {

    public static void main(String[] args) {
		// might be a counterpoint
		// maybe

        Player player = new Player();

		//	                              1       2       3       4
		Pattern v2rests = new Pattern("V2 R R R R R R R R R R R R R R R R ");
        Pattern cantus = new Pattern("T140 V1 I[MARIMBA]");
        Pattern fcp = new Pattern("T140 V2 I[XYLOPHONE]");
        Pattern scp = new Pattern("T140 V2 I[VIOLA]");

        Pattern song = new Pattern();

		// not sure if counterpoint, but sounds nice
		////          1      2             3   4
        //cantus.add("Fh. Cq C6h. D6i. E6s G6w A6w ");
		////       1           2           3       4
        //fcp.add("Ri A6h. B6i A7h B7q A7q Ri D7q. C7h E7q. Ci Eh ");

		// melodic inversion
		//          1      2             3   4
        cantus.add("Fh. Cq C6h. D6i. E6s G6w A6w ");
		//       1       2          3   4
		fcp.add("Fh. B6q Bh. Ai. Gs E6w D7w ");

		// retrograde
		//       1   2            3    4
        fcp.add("A6w G6w E6s D6i. C6h. Cq Fh. ");
        
        song.add(cantus);
		song.add(v2rests);
        song.add(cantus);
        song.add(fcp);
        song.add(cantus);

        player.play(song);
        player.close();
    }
}

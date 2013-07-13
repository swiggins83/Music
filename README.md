Music!
------
Just messing around with JFugue! On GitHub mainly for backup purposes.

If you're curious and want to see it work, follow the steps below.

Prerequisites
-------------
1. Download [JFugue](http://www.jfugue.org/jfugue-4.0.3.jar).
2. Install JFugue to your local ```~/.m2``` repository:

        mvn install:install-file \
        -Dfile=/path/to/jfugue-4.0.3.jar \
        -DgroupId=jfugue \
        -DartifactId=jfugue \
        -Dversion=4.0.3 \
        -Dpackaging=jar \

Execution
---------
Compile with ```mvn clean package``` and run with ```mvn exec:java```
(this is a plugin).

Alternatively, to run without Maven stuff spat out into your terminal:

```java -cp path/to/jfugue-4.0.3.jar:target/Music-1.0-SNAPSHOT.jar:.
edu.self.music.Music```

JFugue?
-------
From [jfugue.org](http://www.jfugue.org/):

> JFugue is an open-source Java API for programming music without the
> complexities of MIDI.
> 
> JFugue makes programming music this easy:
> 
> ``` Java
> Player player = new Player();
> player.play("C D E F G A B");
> ```
> 
> In addition, JFugue provides many more features:
> * Music Strings let you specify notes, chords, instruments, tracks. More...
> * Music can be played at runtime, or saved to and opened from MIDI files
> * Music can be sent to and received from external devices: keyboards, mixers,
>   etc.
> * A "Pattern" of music can be transformed and manipulated in interesting ways
> * Support for microtonal music, intuitive rhythm tracks, anticipating musical
> * events
> * Other music parsers and renderers can be easily integrated into the JFugue
> * architecture
> 
> JFugue is ideal for applications in which music is generated at run-time, such
> as:
> 
> * Algorithmic, generative, aleatoric, or evolutionary music
> * Music editors, beat boxes, drum machines
> * Jazz improvisers, mimicking classical composers, AI in music
> * Procedural synthesis, virtual instruments, interactive software playthings
> * Dynamic mood setting, adaptive music, music that depends on game state,
>   games that require musical skill
> * ...And much more, limited only by your imagination!
> 
> Using JFugue is also a great way to inspire future programmers, and to
> experiment with music theory and composition.

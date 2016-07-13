package com.sf.boxmaster;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

class Sound {
	String path = new String("musics\\");
	String file = new String("nor.mid");
	Sequence seq;
	Sequencer midi;
	boolean sign;

	void loadSound() {
		try {
			seq = MidiSystem.getSequence(new File(path + file));
			midi = MidiSystem.getSequencer();
			midi.open();
			midi.setSequence(seq);
			midi.start();
			midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		sign = true;
	}

	void mystop() {
		midi.stop();
		midi.close();
		sign = false;
	}

	boolean isplay() {
		return sign;
	}

	void setMusic(String e) {
		file = e;
	}
}
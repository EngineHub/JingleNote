/*
 * Copyright (c) EngineHub
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.enginehub.jinglenote.sequencer;

import org.enginehub.jinglenote.Instrument;
import org.enginehub.jinglenote.JingleNotePlayer;
import org.enginehub.jinglenote.Note;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;

/**
 * A JingleNote Sequencer that plays MIDI files.
 */
public class MidiJingleSequencer implements JingleSequencer {

    /*
    0 = HARP
    1 = BASS
    2 = SNARE
    3 = HAT
    4 = BASEDRUM
    5 = GUITAR
    6 = BELL
    7 = CHIME
    8 = FLUTE
    9 = XYLOPHONE
    10 = PLING
    11 = BANJO
    12 = BIT
    13 = COW_BELL
    14 = DIDGERIDOO
    15 = IRON_XYLOPHONE
     */

    private static final byte[] instruments = {
            0, 0, 0, 0, 0, 0, 0,11, // 0-7
            6, 6, 6, 6, 9, 9,15,11, // 8-15
            10,5, 5,10,10,10,10,10, // 16-23
            5, 5, 5, 5, 5, 5, 5, 5, // 24-31
            1, 1, 1, 1, 1, 1, 1, 1, // 32-39
            0,10,10, 1, 0, 0, 0, 4, // 40-47
            0, 0, 0, 0, 8, 8, 8,12, // 48-55
            8,14,14,14,14,14, 8, 8, // 56-63
            8, 8, 8,14,14, 8, 8, 8, // 64-71
            8, 8, 8, 8,14, 8, 8, 8, // 72-79
            8,14, 8, 8, 5, 8,12, 1, // 80-87
            1, 0, 0, 8, 0, 0, 0, 0, // 88-95
            0, 0, 7, 0, 0, 0, 0,12, // 96-103
            11,11,3, 3, 3,14,10, 6, // 104-111
            6, 3, 3, 2, 2, 2, 6, 5, // 112-119
            1, 1, 1,13,13, 2, 4, 7, // 120-127
    };


    private static final byte[] percussion = {
            9, 6, 4, 4, 3, 2, 3, 2, //40 - Electric Snare
            2, 2, 2, 2, 2, 2, 2, 2, //48 - Hi Mid Tom
            7, 2, 7, 7, 6, 3, 7, 6, //56 - Cowbell
            7, 3, 7, 2, 2, 3, 3, 3, //64 - Low Conga
            2, 2, 6, 6, 2, 2, 0, 0, //72 - Long Whistle
            3, 3, 3, 3, 3, 3, 5, 5, //80 - Open Cuica
            15, 15,                 //82 - Open Triangle
    };

    private Sequencer sequencer;
    private boolean running = false;
    private boolean playedBefore = false;

    private List<JingleNotePlayer> players = new ArrayList<>();

    /**
     * Create a new MIDI Sequencer from a file.
     *
     * @param midiFile The MIDI File
     * @param loop If it should loop
     *
     * @throws MidiUnavailableException If the MIDI is unavailable
     * @throws InvalidMidiDataException If the MIDI is invalid
     * @throws IOException If it failed to read the file
     */
    public MidiJingleSequencer(File midiFile, boolean loop) throws MidiUnavailableException, InvalidMidiDataException, IOException {
        try {
            sequencer = MidiSystem.getSequencer(false);
            sequencer.open();
            Sequence seq = MidiSystem.getSequence(midiFile);
            sequencer.setSequence(seq);
            if (loop) {
                sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            }
        } catch (MidiUnavailableException | InvalidMidiDataException | IOException e) {
            stop();
            throw e;
        }
    }

    @Override
    public void play() {
        final Map<Integer, Integer> patches = new HashMap<>();

        try {
            if(sequencer == null || sequencer.getSequence() == null) {
                return;
            }

            if (!sequencer.isOpen()) {
                sequencer.open();
            }

            sequencer.getTransmitter().setReceiver(new Receiver() {

                @Override
                public void send(MidiMessage message, long timeStamp) {
                    if(players.isEmpty()) {
                        running = false;
                        return;
                    }

                    if ((message.getStatus() & 0xF0) == ShortMessage.PROGRAM_CHANGE) {
                        ShortMessage msg = (ShortMessage) message;
                        int chan = msg.getChannel();
                        int patch = msg.getData1();
                        patches.put(chan, patch);
                    } else if ((message.getStatus() & 0xF0) == ShortMessage.NOTE_ON) {
                        ShortMessage msg = (ShortMessage) message;
                        int chan = msg.getChannel();
                        int n = msg.getData1();
                        if (chan == 9) { // Percussion
                            // Sounds like utter crap
                            for(JingleNotePlayer player : players) {
                                player.playNote(
                                        new Note(Instrument.fromByte(toMCPercussion(patches.get(chan))), toMCNote(n), 10 * (msg.getData2() / 127f)));
                            }
                        } else {
                            for(JingleNotePlayer player : players) {
                                player.playNote(
                                        new Note(Instrument.fromByte(toMCInstrument(patches.get(chan))), toMCNote(n), 10 * (msg.getData2() / 127f)));
                            }
                        }
                    }
                }

                @Override
                public void close() {
                    running = false;
                }
            });

            try {
                if (sequencer.isOpen()) {
                    sequencer.start();
                    running = true;
                    playedBefore = true;
                } else {
                    throw new IllegalArgumentException("Sequencer is not open!");
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        if (!running) {
            return;
        }

        players.clear();
        if (sequencer != null) {
            try {
                if(sequencer.isRunning()) {
                    sequencer.stop();
                }
                if(sequencer.isOpen()) {
                    sequencer.close();
                }
                sequencer = null;
            } catch(Exception ignored){}
        }
        running = false;
    }

    private static byte toMCNote(int n) {
        if (n < 54) {
            return (byte) ((n - 6) % (18 - 6));
        } else if (n > 78) {
            return (byte) ((n - 6) % (18 - 6) + 12);
        } else {
            return (byte) (n - 54);
        }
    }

    private static byte toMCInstrument(Integer patch) {
        if (patch == null) {
            return 0;
        }

        if (patch < 0 || patch >= instruments.length) {
            return 0;
        }

        return instruments[patch];
    }

    private static byte toMCPercussion(Integer patch) {
        if(patch == null) {
            return 0;
        }

        int i = patch - 33;
        if (i < 0 || i >= percussion.length) {
            return 1;
        }

        return percussion[i];
    }

    @Override
    public boolean isPlaying() {
        return running && sequencer != null;
    }

    @Override
    public boolean isActive() {
        return !playedBefore || isPlaying();
    }

    @Override
    public void removePlayer(JingleNotePlayer player) {
        players.remove(player);
        if(players.isEmpty()) {
            stop();
        }
    }

    @Override
    public void addPlayer(JingleNotePlayer player) {
        players.add(player);
        if(!playedBefore) {
            play();
        }
    }

    @Override
    public Collection<JingleNotePlayer> getPlayers () {
        return players;
    }
}

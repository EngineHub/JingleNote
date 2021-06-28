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

package org.enginehub.jinglenote.adventure;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.enginehub.jinglenote.Instrument;
import org.enginehub.jinglenote.JingleNotePlayer;
import org.enginehub.jinglenote.Note;
import org.enginehub.jinglenote.sequencer.JingleSequencer;

/**
 * A JingleNote player for Adventure audiences
 */
public class AdventureJingleNotePlayer extends JingleNotePlayer {

    private final Audience audience;

    /**
     * Create a player for a given sequencer.
     *
     * @param sequencer The sequencer
     */
    public AdventureJingleNotePlayer(Audience audience, JingleSequencer sequencer) {
        super(sequencer);

        this.audience = audience;
    }

    @Override
    public void playNote(Note note) {
        if(!isActive()) return;

        audience.playSound(Sound.sound(toSoundKey(note.getInstrument()), Sound.Source.RECORD, note.getVelocity(), note.getPitch()));
    }

    private static Key toSoundKey(Instrument instrument) {
        switch(instrument) {
            case BASS:
                return Key.key("block.note_block.bass");
            case SNARE:
                return Key.key("block.note_block.snare");
            case HAT:
                return Key.key("block.note_block.hat");
            case BANJO:
                return Key.key("block.note_block.banjo");
            case BASEDRUM:
                return Key.key("block.note_block.basedrum");
            case BELL:
                return Key.key("block.note_block.bell");
            case BIT:
                return Key.key("block.note_block.bit");
            case CHIME:
                return Key.key("block.note_block.chime");
            case COW_BELL:
                return Key.key("block.note_block.cow_bell");
            case DIDGERIDOO:
                return Key.key("block.note_block.didgeridoo");
            case FLUTE:
                return Key.key("block.note_block.flute");
            case XYLOPHONE:
                return Key.key("block.note_block.xylophone");
            case IRON_XYLOPHONE:
                return Key.key("block.note_block.iron_xylophone");
            case PLING:
                return Key.key("block.note_block.pling");
            case GUITAR:
                return Key.key("block.note_block.guitar");
            case HARP:
            default:
                return Key.key("block.note_block.harp");
        }
    }
}

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

package org.enginehub.jinglenote.sponge;

import org.enginehub.jinglenote.Instrument;
import org.enginehub.jinglenote.JingleNotePlayer;
import org.enginehub.jinglenote.Note;
import org.enginehub.jinglenote.sequencer.JingleSequencer;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.living.player.Player;

/**
 * A JingleNote player for Sponge players
 */
public class SpongeJingleNotePlayer extends JingleNotePlayer {

    private final Player player;

    /**
     * Create a player for a given sequencer.
     *
     * @param sequencer The sequencer
     */
    public SpongeJingleNotePlayer(Player player, JingleSequencer sequencer) {
        super(sequencer);

        this.player = player;
    }

    @Override
    public void playNote(Note note) {
        if(!isActive()) return;

        player.playSound(toSound(note.getInstrument()), player.getLocation().getPosition(), note.getVelocity(), note.getPitch());
    }

    private static SoundType toSound(Instrument instrument) {
        switch(instrument) {
            case BASS:
                return SoundTypes.BLOCK_NOTE_BASS;
            case SNARE_DRUM:
                return SoundTypes.BLOCK_NOTE_SNARE;
            case STICKS:
                return SoundTypes.BLOCK_NOTE_HAT;
            case BASS_DRUM:
                return SoundTypes.BLOCK_NOTE_BASEDRUM;
            case BELL:
                return SoundTypes.BLOCK_NOTE_BELL;
            case CHIME:
                return SoundTypes.BLOCK_NOTE_CHIME;
            case FLUTE:
                return SoundTypes.BLOCK_NOTE_FLUTE;
            case XYLOPHONE:
                return SoundTypes.BLOCK_NOTE_XYLOPHONE;
            case PLING:
                return SoundTypes.BLOCK_NOTE_PLING;
            case PIANO:
            default:
                return SoundTypes.BLOCK_NOTE_HARP;
        }
    }
}

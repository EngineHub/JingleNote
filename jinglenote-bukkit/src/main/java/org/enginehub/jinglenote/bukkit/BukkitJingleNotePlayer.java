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

package org.enginehub.jinglenote.bukkit;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.enginehub.jinglenote.Instrument;
import org.enginehub.jinglenote.JingleNotePlayer;
import org.enginehub.jinglenote.Note;
import org.enginehub.jinglenote.sequencer.JingleSequencer;

/**
 * A JingleNote player for Bukkit players
 */
public class BukkitJingleNotePlayer extends JingleNotePlayer {

    private final Player player;

    /**
     * Create a player for a given sequencer.
     *
     * @param sequencer The sequencer
     */
    public BukkitJingleNotePlayer(Player player, JingleSequencer sequencer) {
        super(sequencer);

        this.player = player;
    }

    @Override
    public void playNote(Note note) {
        if(!isActive()) return;

        player.playSound(player.getLocation(), toSound(note.getInstrument()), SoundCategory.RECORDS, note.getVelocity(), note.getPitch());
    }

    @Override
    public boolean isActive() {
        return super.isActive() && player.isValid() && player.isOnline();
    }

    private static Sound toSound(Instrument instrument) {
        switch(instrument) {
            case BASS:
                return Sound.BLOCK_NOTE_BLOCK_BASS;
            case SNARE:
                return Sound.BLOCK_NOTE_BLOCK_SNARE;
            case HAT:
                return Sound.BLOCK_NOTE_BLOCK_HAT;
            case BANJO:
                return Sound.BLOCK_NOTE_BLOCK_BANJO;
            case BASEDRUM:
                return Sound.BLOCK_NOTE_BLOCK_BASEDRUM;
            case BELL:
                return Sound.BLOCK_NOTE_BLOCK_BELL;
            case BIT:
                return Sound.BLOCK_NOTE_BLOCK_BIT;
            case CHIME:
                return Sound.BLOCK_NOTE_BLOCK_CHIME;
            case COW_BELL:
                return Sound.BLOCK_NOTE_BLOCK_COW_BELL;
            case DIDGERIDOO:
                return Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO;
            case FLUTE:
                return Sound.BLOCK_NOTE_BLOCK_FLUTE;
            case XYLOPHONE:
                return Sound.BLOCK_NOTE_BLOCK_XYLOPHONE;
            case IRON_XYLOPHONE:
                return Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE;
            case PLING:
                return Sound.BLOCK_NOTE_BLOCK_PLING;
            case GUITAR:
                return Sound.BLOCK_NOTE_BLOCK_GUITAR;
            case HARP:
            default:
                return Sound.BLOCK_NOTE_BLOCK_HARP;
        }
    }
}

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

package org.enginehub.jinglenote;

import org.enginehub.jinglenote.sequencer.JingleSequencer;

/**
 * A receiver for sequencer-generated content.
 *
 * This is not a "Minecraft Player", although it may represent one.
 */
public abstract class JingleNotePlayer {

    private JingleSequencer sequencer;

    /**
     * Create a player for a given sequencer.
     *
     * @param sequencer The sequencer
     */
    public JingleNotePlayer(JingleSequencer sequencer) {
        this.sequencer = sequencer;
    }

    /**
     * Checks whether this player is active.
     *
     * This returns true if the sequencer is valid,
     * and has not yet finished.
     *
     * @return If the player is active
     */
    public boolean isActive() {
        return sequencer != null && sequencer.isActive();
    }

    /**
     * Start this player using the active sequencer.
     */
    public void play() {
        if (sequencer == null) {
            // We need a valid sequencer in order to play.
            return;
        }

        sequencer.addPlayer(this);
    }

    /**
     * Stop this player from playing.
     */
    public void stop() {
        if (sequencer != null) {
            sequencer.removePlayer(this);
            sequencer = null;
        }
    }

    /**
     * Plays the given note to this player.
     *
     * @param note The note
     */
    public abstract void playNote(Note note);
}

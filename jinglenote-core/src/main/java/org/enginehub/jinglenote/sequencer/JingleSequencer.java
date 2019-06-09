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

import org.enginehub.jinglenote.JingleNotePlayer;

import java.util.Collection;

/**
 * Interface for a sequencer - a source of notes.
 */
public interface JingleSequencer {

    /**
     * Add a player to this sequencer.
     *
     * @param player The player
     */
    void addPlayer(JingleNotePlayer player);

    /**
     * Remove a player from this sequencer.
     *
     * @param player The player
     */
    void removePlayer(JingleNotePlayer player);

    /**
     * Play this sequencer to all listening players.
     */
    void play();

    /**
     * Stop playing this sequencer to all listening players.
     */
    void stop();

    /**
     * Gets whether this sequencer is currently playing.
     *
     * @return If the sequencer is playing
     */
    boolean isPlaying();

    /**
     * Gets whether this sequencer is active, and has not
     * finished playing.
     *
     * @return If the sequencer is active
     */
    boolean isActive();

    /**
     * Gets a collection of all players listening to
     * this sequencer.
     *
     * @return The players
     */
    Collection<JingleNotePlayer> getPlayers();
}

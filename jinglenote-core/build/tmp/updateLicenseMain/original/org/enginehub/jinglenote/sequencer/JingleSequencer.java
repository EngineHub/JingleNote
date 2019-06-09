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

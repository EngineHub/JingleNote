package org.enginehub.jinglenote;

import org.enginehub.jinglenote.sequencer.JingleSequencer;

/**
 * A receiver for sequencer-generated content.
 *
 * This is not a "Minecraft Player", although it may represent one.
 */
public abstract class JingleNotePlayer implements Runnable {

    private JingleSequencer sequencer;

    /**
     * Create a player for a given sequencer.
     *
     * @param sequencer The sequencer
     */
    public JingleNotePlayer(JingleSequencer sequencer) {
        this.sequencer = sequencer;
    }

    @Override
    public void run() {
        if (sequencer == null) {
            // We need a valid sequencer in order to play.
            return;
        }

        sequencer.addPlayer(this);

        while (isActive()) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
    public abstract void play(Note note);
}

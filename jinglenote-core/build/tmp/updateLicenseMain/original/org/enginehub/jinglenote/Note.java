package org.enginehub.jinglenote;

/**
 * A note is a playable sound containing an
 * instrument, pitch, and velocity.
 */
public class Note {
    private Instrument instrument;
    private byte pitch;
    private float velocity;

    /**
     * Create a note with instrument, pitch, and velocity.
     *
     * @param instrument The instrument
     * @param pitch The pitch
     * @param velocity The velocity
     */
    public Note(Instrument instrument, byte pitch, float velocity) {
        this.instrument = instrument;
        this.pitch = pitch;
        this.velocity = velocity;
    }

    /**
     * Get the instrument to play.
     *
     * @return The instrument
     */
    public Instrument getInstrument() {
        return this.instrument;
    }

    /**
     * Gets the pitch to play.
     *
     * Note: This has been normalised
     *
     * @return The pitch
     */
    public float getPitch() {
        return (float) Math.pow(2.0D, (this.pitch - 12) / 12.0D);
    }

    /**
     * Gets the raw, un-normalised pitch.
     *
     * @return The raw pitch
     */
    public byte getRawPitch() {
        return this.pitch;
    }

    /**
     * Gets the velocity to play.
     *
     * @return The velocity
     */
    public float getVelocity() {
        return this.velocity;
    }
}

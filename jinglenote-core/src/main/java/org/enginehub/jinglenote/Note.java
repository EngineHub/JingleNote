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

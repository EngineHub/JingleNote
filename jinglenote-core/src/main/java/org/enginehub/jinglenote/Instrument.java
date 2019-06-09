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
 * An enum of available instruments.
 */
public enum Instrument {

    BANJO,
    BASEDRUM,
    BASS,
    BELL,
    BIT,
    CHIME,
    COW_BELL,
    DIDGERIDOO,
    FLUTE,
    GUITAR,
    HARP,
    HAT,
    IRON_XYLOPHONE,
    PLING,
    SNARE,
    XYLOPHONE;

    public static Instrument fromByte(byte instrument) {
        switch (instrument) {
            case 1:
                return Instrument.BASS;
            case 2:
                return Instrument.SNARE;
            case 3:
                return Instrument.HAT;
            case 4:
                return Instrument.BASEDRUM;
            case 5:
                return Instrument.GUITAR;
            case 6:
                return Instrument.BELL;
            case 7:
                return Instrument.CHIME;
            case 8:
                return Instrument.FLUTE;
            case 9:
                return Instrument.XYLOPHONE;
            case 10:
                return Instrument.PLING;
            case 11:
                return Instrument.BANJO;
            case 12:
                return Instrument.BIT;
            case 13:
                return Instrument.COW_BELL;
            case 14:
                return Instrument.DIDGERIDOO;
            case 15:
                return Instrument.IRON_XYLOPHONE;
            case 0:
            default:
                return Instrument.HARP;
        }
    }
}

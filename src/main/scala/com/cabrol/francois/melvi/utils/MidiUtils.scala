/*
 * Copyright (c) 2015 François Cabrol.
 *
 *  This file is part of MelVi.
 *
 *     MelVi is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     MelVi is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with MelVi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cabrol.francois.melvi.utils

import java.io.File
import javax.sound.midi.ShortMessage._
import javax.sound.midi.{MetaMessage, MidiEvent, MidiSystem, Sequence, ShortMessage, SysexMessage}

import com.cabrol.francois.libjamu.midi.utils.TickUtils
import com.cabrol.francois.libjamu.musictheory.entity.note.Note

/**
 * @author  Francois Cabrol <francois.cabrol@live.fr>
 * @since   15-01-29
 */
object MidiUtils {

  private def createSequence(notes: List[Note]):Sequence = {
    val seq = new Sequence(Sequence.PPQ, 4);
    val track = seq.createTrack();

    // General MIDI sysex -- turn on General MIDI sound set
    val b = Array[Byte](0xF0.asInstanceOf[Byte], 0x7E, 0x7F, 0x09, 0x01, 0xF7.asInstanceOf[Byte]);
    var sm = new SysexMessage();
    sm.setMessage(b, 6);
    var me = new MidiEvent(sm, 0);
    track.add(me);

    // set track name (meta event)
    var mt = new MetaMessage();
    val trackName = new String("mural track");
    mt.setMessage(0x03, trackName.getBytes(), trackName.length());
    me = new MidiEvent(mt, 0);
    track.add(me);

    def makeEvent(comd: Int, chan: Int, one: Int, two: Int, tick: Int): MidiEvent = {
      val a = new ShortMessage();
      a.setMessage(comd, chan, one, two);
      return new MidiEvent(a, tick);
    }

    // set omni on
    track.add(makeEvent(CONTROL_CHANGE, 0, 0x7D, 0x00, 0));

    // set poly on
    track.add(makeEvent(CONTROL_CHANGE, 0, 0x7F, 0x00, 0));

    // set instrument to Piano
    track.add(makeEvent(PROGRAM_CHANGE, 0x00, 0x00, 0x00, 0));

    val maxTicks = (for (n <- notes) yield {
      println(n)
      val start = TickUtils.convertTockToTick(n.getRhythmicNote.getStart).toInt
      val end   = start + TickUtils.convertTockToTick(n.getRhythmicNote.getDuration).toInt
      track.add(makeEvent(NOTE_ON, 0, n.getKey.getMidiKey, n.getRhythmicNote.getVelocity, start))
      track.add(makeEvent(NOTE_OFF, 0, n.getKey.getMidiKey, n.getRhythmicNote.getVelocity, end))
      end
    }).max

    // set end of track (meta event)
    mt = new MetaMessage();
    mt.setMessage(0x2F, Array[Byte](), 0);
    me = new javax.sound.midi.MidiEvent(mt, maxTicks);
    track.add(me);

   seq
  }

  def play(notes: List[Note]) = {
    val seq = createSequence(notes)
    val sequencer = MidiSystem.getSequencer();
    sequencer.open();
    sequencer.setSequence(seq);
    sequencer.setTempoInBPM(80);
    sequencer.start();
  }

  def writeMifiFile(notes: List[Note], file: File) = {
    val seq = createSequence(notes)

    // write the MIDI sequence to a MIDI file
    MidiSystem.write(seq, 1, file);

    println("[Melvi] Wrote in midi file : " + file.getAbsolutePath());
  }
}

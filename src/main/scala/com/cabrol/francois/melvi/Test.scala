package com.cabrol.francois.melvi

import com.cabrol.francois.libjamu.musictheory.entity.note._
import com.cabrol.francois.melvi.factory.MelodyVisualiserFactory

/**
 * Created with IntelliJ IDEA.
 * User: francois * Date: 2014-02-04
 */

object Test {

  def main(args: Array[String]):Unit = {
    print("Open a visualization of a melody example")
    MelodyVisualiserFactory.create(List(new Note(new RhythmicNote(0, 1), new Key(80)), new Note(new RhythmicNote(1, 1), new Key(100)), new Note(new RhythmicNote(2, 1), new Key(95))))
  }

}

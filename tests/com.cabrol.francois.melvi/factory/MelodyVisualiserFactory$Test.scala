package com.cabrol.francois.melvi.factory

import com.cabrol.francois.libjamu.musictheory.entity.note._


/**
 * Created with IntelliJ IDEA.
 * User: francois * Date: 2014-02-04
 */

object MelodyVisualiserFactory$Test {

  def main(args: Array[String]):Unit = {
    print("Open a visualization of a melody example")
    MelodyVisualiserFactory.create(List(new Note(new RhythmicNote(0, 1), new Key(10)), new Note(new RhythmicNote(0, 1), new Key(20))))
  }

}
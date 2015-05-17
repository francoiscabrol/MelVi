/*
 * Copyright (c) 2014 François Cabrol.
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

package com.cabrol.francois.melvi.view

import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.stage.FileChooser
import javafx.stage.FileChooser.ExtensionFilter

import com.cabrol.francois.libjamu.musictheory.entity.note.Note
import com.cabrol.francois.melvi.utils.MidiUtils

/**
 * @author  Francois Cabrol <francois.cabrol@live.fr>
 * @since   15-01-29
 */
class MenuTop(notes:List[Note]) extends HBox {

  val buttonPlay = new Button("Play")
  buttonPlay.setPrefSize(100, 20)
  buttonPlay.setOnAction(new EventHandler[ActionEvent] {
    def handle(event: ActionEvent) {
      MidiUtils.play(notes)
    }
  })
  
  val buttonExport = new Button("Export as midi file")
  buttonExport.setPrefSize(100, 20)
  buttonExport.setOnAction(new EventHandler[ActionEvent] {
    def handle(event: ActionEvent) {
      val fileChooser = new FileChooser();
      fileChooser.setTitle("Open Resource File");
      fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Midi Files", "*.midi"),
        new ExtensionFilter("All Files", "*.*"));
      val file = fileChooser.showSaveDialog(null);
      if (file != null) {
        MidiUtils.writeMifiFile(notes, file)
      }
    }
  })
  
  this.getChildren().addAll(buttonPlay, buttonExport)

}

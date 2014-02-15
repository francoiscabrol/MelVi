/*
 * Copyright (c) 2014 François Cabrol.
 *
 *  This file is part of MelodyVisualiser.
 *
 *     MelodyVisualiser is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     MelodyVisualiser is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with MelodyVisualiser.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cabrol.francois.melvi.factory

import com.cabrol.francois.libjamu.musictheory.entity.note.Note
import javafx.scene.chart.{XYChart, LineChart, NumberAxis, Chart}
import com.cabrol.francois.melvi.view.VisualiserView
/**
 * Created with IntelliJ IDEA.
 * User: francois * Date: 2014-02-02
 */
object MelodyVisualiserFactory {

  def create(melody:List[Note], graphicsType:GraphicsType.GraphicsType = GraphicsType.pitch):VisualiserView = {
    new VisualiserView(melody, graphicsType)
  }

}
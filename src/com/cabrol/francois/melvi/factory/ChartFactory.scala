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

package com.cabrol.francois.melvi.factory

import javafx.scene.chart.{XYChart, LineChart, NumberAxis, Chart}
import com.cabrol.francois.libjamu.musictheory.entity.note.Note
import javafx.scene.control.{Tab, TabPane}

/**
 * Created with IntelliJ IDEA.
 * User: francois * Date: 2014-02-04
 */
object GraphicsType extends Enumeration {
  type GraphicsType = Value
  val pitchvstime, pitch = Value
}

object ChartFactory {

  def createChartPanel(graphicsType:GraphicsType.GraphicsType, notes:List[Note]):TabPane ={
    val tabPane = new TabPane();

    val series = new XYChart.Series[Number,Number]
    series.setName("Relative pitch")
    notes.foreach{ n => series.getData().add(new XYChart.Data[Number,Number](n.getRhythmicNote.getStart, n.getKey.getMidiKey)) }
    val pitchvstimeTab = createLineChartTab("Pitch vs Time Position", series)
    tabPane.getTabs().add(pitchvstimeTab)

    val series2 = new XYChart.Series[Number,Number]
    series2.setName("Relative pitch")
    notes.zipWithIndex.foreach{ case (n, i) => series2.getData().add(new XYChart.Data[Number,Number](i, n.getKey.getMidiKey)) }
    val pitchTab = createLineChartTab("Pitch", series2)
    tabPane.getTabs().add(pitchTab)

    graphicsType match {
      case GraphicsType.pitchvstime => {
        tabPane.getSelectionModel.select(pitchvstimeTab)
      }
      case GraphicsType.pitch => {
        tabPane.getSelectionModel.select(pitchTab)
      }
    }

    tabPane
  }


  def createLineChartTab(name:String, series:XYChart.Series[Number, Number]):Tab = {
    val tab = new Tab();
    tab.setText(name);
    tab.setContent(createLineChart(name, series))
    tab
  }


  def createLineChart(name:String, series:XYChart.Series[Number, Number]):Chart = {
    //defining the axes
    val xAxis = new NumberAxis()
    val yAxis = new NumberAxis()
    xAxis.setLabel("Time")
    //creating the chart
    val lineChart = new LineChart[Number, Number](xAxis, yAxis);

    lineChart.setTitle(name)

    lineChart.getData().add(series);
    lineChart
  }

}
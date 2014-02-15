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

import javax.swing.JFrame
import javafx.embed.swing.JFXPanel
import javafx.application.Platform
import javafx.scene.Scene
import com.cabrol.francois.melvi.factory.{GraphicsType, ChartFactory}
import com.cabrol.francois.libjamu.musictheory.entity.note.Note
import java.awt.{Point, Component}


/**
 * Created with IntelliJ IDEA.
 * User: francois * Date: 2014-02-02
 */

class VisualiserView(notes:List[Note], graphicsType:GraphicsType.GraphicsType) {

  val frame = new JFrame("Melody Chart");

  initAndShowGUI

  def initAndShowGUI {
    // This method is invoked on the EDT thread
    val fxPanel = new JFXPanel();
    frame.add(fxPanel);
    frame.setSize(300, 200);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Platform.runLater(new Runnable() {
      def run() {
        initFX(fxPanel);
      }
    })
  }

  def initFX(fxPanel:JFXPanel) {
    // This method is invoked on the JavaFX thread
    val scene = createScene
    fxPanel.setScene(scene)
  }

  def createScene:Scene = {
    val panel = ChartFactory.createChartPanel(graphicsType, notes)
    new Scene(panel,400,400)
  }

  def getWidth:Int = frame.getWidth

  def getHeight:Int = frame.getHeight

  def getX:Int = frame.getX

  def getY:Int = frame.getY

  def setLocation(p:Point) = frame.setLocation(p)

}



//abstract class VisualiserDrawer(val root:Group) {
//
//  lazy val offset = 20;
//
//  private def drawOffsetRectangle = root.getChildren.add(new Rectangle(getHeight-offset))
//
//  protected def drawGraphics:Unit
//
//  def draw:Unit = {
//    drawOffsetRectangle
//    drawGraphics
//  }
//
//}
// Copyright (c) 2019, Oracle Corporation and/or its affiliates.  All rights reserved.
// Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

package net.russgold.samples.life;

import javax.swing.*;

import org.junit.jupiter.api.Test;

import static com.meterware.simplestub.Stub.createNiceStub;

public class LifePanelTest extends JPanel {
     private final GraphicsStub graphics = createNiceStub(GraphicsStub.class);
     private Board board = new FixedBoard();
     private final LifePanel panel = new LifePanel(board, 20, 20);

     @Test
     void whenBoardEmpty_clearPanel() {
          panel.paint(graphics);

//          assertThat(graphics.)
     }
}


// minimum cells to display: 20x20
// fixed scaling
// keep set cells in display
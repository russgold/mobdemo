// Copyright (c) 2019, Oracle Corporation and/or its affiliates.  All rights reserved.
// Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

package net.russgold.samples.life;

import javax.swing.*;

public class LifePanel extends JPanel {

  private final Board board;
  private final int width;
  private final int height;

  public LifePanel(Board board, int width, int height) {
    this.board = board;
    this.width = width;
    this.height = height;
  }
}

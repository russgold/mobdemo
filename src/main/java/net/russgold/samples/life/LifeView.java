// Copyright (c) 2019, Oracle Corporation and/or its affiliates.  All rights reserved.
// Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

package net.russgold.samples.life;

import javax.swing.*;

public class LifeView {

  private Board board;
  private JButton runGliderButton;
  private JPanel lifePanel;

  public LifeView(Board board) {
    this.board = board;
  }

  public JPanel getLifePanel() {
    return lifePanel;
  }
}

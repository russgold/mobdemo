// Copyright (c) 2019, Oracle Corporation and/or its affiliates.  All rights reserved.
// Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

package net.russgold.samples.life;

import javax.swing.*;

public class Life {

  public static void main(String... args) {
    JFrame frame = new JFrame("Life");
    frame.setContentPane(new LifeView(new FixedBoard()).getLifePanel());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}

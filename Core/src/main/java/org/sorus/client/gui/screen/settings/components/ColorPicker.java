/*
 * MIT License
 *
 * Copyright (c) 2020 Danterus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.sorus.client.gui.screen.settings.components;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javax.imageio.ImageIO;
import org.sorus.client.Sorus;
import org.sorus.client.event.EventInvoked;
import org.sorus.client.event.impl.client.input.MousePressEvent;
import org.sorus.client.event.impl.client.input.MouseReleaseEvent;
import org.sorus.client.gui.core.Screen;
import org.sorus.client.gui.core.ThemeableScreen;
import org.sorus.client.gui.core.component.Collection;
import org.sorus.client.gui.core.component.Panel;
import org.sorus.client.gui.core.component.impl.*;
import org.sorus.client.gui.core.component.impl.Image;
import org.sorus.client.gui.core.component.impl.Rectangle;
import org.sorus.client.gui.screen.settings.Configurable;
import org.sorus.client.settings.Setting;
import org.sorus.client.util.MathUtil;
import org.sorus.client.version.input.Key;

public class ColorPicker extends Configurable {

  private final Setting<Color> setting;
  private final String description;

  private Rectangle colorViewer;

  public ColorPicker(Setting<Color> setting, String description) {
    this.setting = setting;
    this.description = description;
    this.update();
    Sorus.getSorus().getEventManager().register(this);
  }

  private void update() {
    this.clear();
    this.add(colorViewer = new Rectangle().size(30, 30).position(615, 25));
    this.add(
        new HollowRectangle()
            .thickness(2)
            .size(40, 40)
            .position(610, 20)
            .color(new Color(170, 170, 170)));
    this.add(
        new Text()
            .fontRenderer(Sorus.getSorus().getGUIManager().getRenderer().getGidoleFontRenderer())
            .text(description)
            .scale(3.5, 3.5)
            .position(30, 30)
            .color(new Color(170, 170, 170)));
  }

  @Override
  public void onRender() {
    this.colorViewer.color(this.getCompleteColor());
    super.onRender();
  }

  @Override
  public void onRemove() {
    Sorus.getSorus().getEventManager().unregister(this);
    super.onRemove();
  }

  @EventInvoked
  public void onClick(MousePressEvent e) {
    boolean expanded =
        e.getY() > this.absoluteY()
            && e.getY() < this.absoluteY() + this.getHeight() * this.absoluteYScale();
    if (expanded && this.getContainer().isInteractContainer()) {
      Sorus.getSorus()
          .getGUIManager()
          .open(new ColorPickerScreen(setting));
    }
  }

  @Override
  public double getHeight() {
    return 80;
  }

  public Color getCompleteColor() {
    return setting.getValue();
  }

  public static class ColorPickerScreen extends ThemeableScreen {

    public ColorPickerScreen(Setting<Color> setting) {
      super(Sorus.getSorus().getThemeManager().getTheme("settings-color-picker", setting));
    }

    @Override
    public boolean shouldTakeOutOfGame() {
      return true;
    }
  }
}

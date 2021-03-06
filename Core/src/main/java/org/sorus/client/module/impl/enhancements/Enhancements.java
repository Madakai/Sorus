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

package org.sorus.client.module.impl.enhancements;

import org.sorus.client.gui.core.component.Collection;
import org.sorus.client.gui.screen.settings.components.Slider;
import org.sorus.client.gui.screen.settings.components.Toggle;
import org.sorus.client.module.ModuleConfigurable;
import org.sorus.client.settings.Setting;

public class Enhancements extends ModuleConfigurable {

  private final Setting<Boolean> centeredInventory;
  private final Setting<Double> customFireHeight;
  private final Setting<Double> customFireOpacity;

  public Enhancements() {
    super("ENHANCEMENTS");
    this.register(centeredInventory = new Setting<>("centeredInventory", false));
    this.register(customFireHeight = new Setting<>("customFireHeight", 0.0));
    this.register(customFireOpacity = new Setting<>("customFireOpacity", 0.9));
  }

  @Override
  public void addConfigComponents(Collection collection) {
    collection.add(new Toggle(centeredInventory, "Centered Inventory"));
    collection.add(new Slider(customFireHeight, -0.5, 0, "Custom Fire Height"));
    collection.add(new Slider(customFireOpacity, 0, 1, "Custom Fire Opacity"));
  }

  public boolean shouldCenterInventory() {
    return this.centeredInventory.getValue();
  }

  public double getCustomFireHeight() {
    return this.customFireHeight.getValue();
  }

  public double getCustomFireOpacity() {
    return this.customFireOpacity.getValue();
  }
}

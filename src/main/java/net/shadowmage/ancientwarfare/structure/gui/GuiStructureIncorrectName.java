package net.shadowmage.ancientwarfare.structure.gui;

import net.minecraft.client.Minecraft;
import net.shadowmage.ancientwarfare.core.container.ContainerBase;
import net.shadowmage.ancientwarfare.core.gui.GuiContainerBase;
import net.shadowmage.ancientwarfare.core.gui.Listener;
import net.shadowmage.ancientwarfare.core.gui.elements.Button;
import net.shadowmage.ancientwarfare.core.gui.elements.CompositeScrolled;
import net.shadowmage.ancientwarfare.core.gui.elements.GuiElement;
import net.shadowmage.ancientwarfare.core.gui.elements.Label;

public class GuiStructureIncorrectName extends GuiContainerBase
{

GuiStructureScanner parent;

public GuiStructureIncorrectName(GuiStructureScanner parent)
  {
  super((ContainerBase) parent.inventorySlots, 256, 16+10+10+12+12, defaultBackground);
  this.parent = parent;  
  this.shouldCloseOnVanillaKeys = false;
  }

@Override
public void initElements()
  {
  Label label = new Label(8, 8, "Invalid name specified");
  addGuiElement(label);
  label = new Label(8, 18, "Please select a valid name");
  addGuiElement(label);
  
  Button button = new Button((256-55)/2, 28, 55, 12, "Okay");
  button.addNewListener(new Listener(Listener.MOUSE_UP)
    {
    @Override
    public boolean onEvent(GuiElement widget, ActivationEvent evt)
      {
      Minecraft.getMinecraft().displayGuiScreen(parent);
      return true;
      }
    });
  addGuiElement(button);  
  }

@Override
public void setupElements()
  {

  }

}

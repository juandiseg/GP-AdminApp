package util.listenersFormatting;

import javax.swing.JToggleButton;

public interface iToggleListener {

    public void applyActionListenerToggle(JToggleButton theToggleButton, String ifTrue, String ifFalse,
            boolean theBoolean);

}

package util.listenersFormatting;

public class booleanWrapper {

    protected boolean theValue;

    public booleanWrapper(boolean value) {
        theValue = value;
    }

    public boolean getValue() {
        return theValue;
    }

    public void setValue(boolean newValue) {
        theValue = newValue;
    }
}
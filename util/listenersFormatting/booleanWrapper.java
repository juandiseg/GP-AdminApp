package util.listenersFormatting;

/*
 * Since it is impossible to pass by reference a native data type in java,
 * I need to have a wrapper around a boolean in order for the lisneterFormatters to work.
 */
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
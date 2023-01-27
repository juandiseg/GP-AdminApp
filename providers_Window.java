import iLayouts.GridLayoutApplyer;

public class providers_Window extends abstractUpdater {

    providers_Window(abstractUpdater previousWindow) {
        super(previousWindow, new GridLayoutApplyer(theFrame, 6));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Providers menu");
        // TODO Auto-generated method stub

    }

    @Override
    public void addActionListeners() {
        // TODO Auto-generated method stub

    }

}

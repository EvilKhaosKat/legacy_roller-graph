package controllers;

import java.util.List;
import models.MainContainer;
import views.InnerPanelInterface;
import views.TextPanel;

/**
 *
 * @author Kat
 */
public class Supervisor {

    private static List<InnerPanelInterface> innerPanelList = MainContainer.getMainFrame().getLeftIndicationPanel().getInnerPanelList();

    public static void startThatProcess() {
        setEnabledInnerPanelByNumber(0);
    }
    public static void panelClickedByNumber(int number) {
        //TODO помни об особом случае с внутренней панелью со скоростью
        if (innerPanelList.get(number).getStatus() == TextPanel.ENABLED) {
            if ((number + 1) < innerPanelList.size()) {
                setEnabledInnerPanelByNumber(number + 1);
                setDisabledInnerPanelByNumber(number);
            }
        }

    }

    private static void setEnabledInnerPanelByNumber(int number) {
        innerPanelList.get(number).setEnabled();
    }

    private static void setDisabledInnerPanelByNumber(int number) {
        innerPanelList.get(number).setDisabled();
    }
}

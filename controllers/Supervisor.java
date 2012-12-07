package controllers;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import models.MainContainer;
import views.InnerPanelInterface;
import views.TextPanel;

/**
 *
 * @author Kat
 */
public class Supervisor {

    private static List<InnerPanelInterface> innerPanelList = MainContainer.getMainFrame().getLeftIndicationPanel().getInnerPanelList();

    static boolean[] buttonsPermissions = {true, true, false,false,true,false,false};//{true,true,true,true,true,true,true};
    
    static int frequency = 500;
    
    public static void startThatProcess() {
        setEnabledInnerPanelByNumber(0);
        
        /* запуск задания, которое с определенной периодичностью 
         * смотрит за текущим значением скоростей,
         * и если выполняется условие - включает соответствующую панельку
         */
        Timer timer = new Timer();
        TimerTask task = new SupervisorTimerTask();
        
        timer.scheduleAtFixedRate(task,1000,frequency);
    }

    public static void panelClickedByNumber(int number) {
        //TODO помни об особом случае с внутренней панелью со скоростью
        if ((innerPanelList.get(number).getStatus() == TextPanel.ENABLED) && (buttonsPermissions[number])) {
            normalPanelActivation(number);
        }
    }

    public static void normalPanelActivation(int number) { 
    	for (int i=0;i<innerPanelList.size();i++) {
    		setDisabledInnerPanelByNumber(i);
    	}	
    	//setDisabledInnerPanelByNumber(number);
        if ((number + 1) < innerPanelList.size()) {
            setEnabledInnerPanelByNumber(number + 1);
        }
    }
    
    private static void setEnabledInnerPanelByNumber(int number) {
        innerPanelList.get(number).setEnabled();
    }

    private static void setDisabledInnerPanelByNumber(int number) {
        innerPanelList.get(number).setDisabled();
    }
}

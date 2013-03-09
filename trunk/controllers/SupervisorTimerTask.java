package controllers;

import java.util.TimerTask;
import models.MainContainer;

public class SupervisorTimerTask extends TimerTask {

    @Override
    public void run() {
        double previousSpeed=0;
        double currentSpeed = MainContainer.getListMeasurements().get(MainContainer.getListMeasurements().size() - 1).getCurrentSpeed();
        double currentSpeedPseudo = currentSpeed;
        System.out.println("Current speed is " + currentSpeed);

        if (MainContainer.getCurrentState() == MainContainer.SPEED_LOWERING_DECELERATION) {
            MainContainer.getMainFrame().getLeftIndicationPanel().disableAllPanels();
            currentSpeedPseudo = 0;
        }

        //if (MainContainer.getCurrentState() == MainContainer.SPEED_LOWER_THAN_MIN) {
        //    currentSpeedPseudo = 0;
        //}


        //костыль, меньше 30 км, а не минимума - чтоб не было спонтанного включения панели "дроссельной заслонки" 
        //при выполнеии набора специфичных условий (скорость получается ниже 40, и срабатывает проверка на больше 30, 
        //флаг что был процесс сниженяи скорости уже снят - и вуаля
        //показываем номер текущего "замера". полезно при режиме трех измерений
        if ((currentSpeed < /*MainContainer.minSpeed*/30) && ((MainContainer.getCurrentState() == MainContainer.SPEED_HIGHER_THAN_MIN)
                || (MainContainer.getCurrentState() == MainContainer.SPEED_LOWERING_DECELERATION))) {
            //текущее положение - скорость выше минимума. реально - меньше. значит скорость упала ниже минимальной
            //закончилось еще одно измерение
            MainContainer.setCurrentState(MainContainer.SPEED_LOWER_THAN_MIN);
            MainContainer.setMeasurementCount(MainContainer.getMeasurementCount() + 1);

            MainContainer.getMainFrame().setMeasurementCount(Integer.toString(MainContainer.getMeasurementCount()));
        }

        if ((currentSpeed > MainContainer.minSpeed) && (MainContainer.getCurrentState() == MainContainer.SPEED_LOWER_THAN_MIN)) {
            //раньше скорость была низкой весьма, теперь мы вышли на разгон
            MainContainer.setCurrentState(MainContainer.SPEED_HIGHER_THAN_MIN);
        }
        //необязательные проверки, для функционирования левой панели
        if ((currentSpeed > MainContainer.maxSpeed) && (MainContainer.getCurrentState() != MainContainer.SPEED_HIGHER_THAN_MAX)) {
            MainContainer.setCurrentState(MainContainer.SPEED_HIGHER_THAN_MAX);
        }
        if ((currentSpeed < MainContainer.maxSpeed) && (MainContainer.getCurrentState() == MainContainer.SPEED_HIGHER_THAN_MAX)) {
            MainContainer.setCurrentState(MainContainer.SPEED_LOWERING_DECELERATION);
        }
        //----------------------------------------------------------------------

        //работа с левой панелью указаний действий пользователя---------------------------------------------------
        if (MainContainer.getCurrentState() != MainContainer.SPEED_LOWERING_DECELERATION) {
            if ((currentSpeedPseudo >= MainContainer.maxSpeed)) {
                System.out.println("MORE THAN MAX!!!!!!!!!!!!!!!!!!!!");
                Supervisor.normalPanelActivation(6);//панель достиг 100 км 
                //TODO начался выбег. нужна дополнительная обработка ситуации
            } else if ((currentSpeedPseudo >= MainContainer.minSpeed)) {
                Supervisor.normalPanelActivation(5);
            } else if ((currentSpeedPseudo >= MainContainer.speedThirdThreshold)) {
                Supervisor.normalPanelActivation(4);//панель включи третью передачу разгонись до 30
                System.out.println("MORE THAN 30!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("current status "+MainContainer.getCurrentState());
                System.out.println("currentSpeed "+currentSpeed);
                System.out.println("currentSpeedPseudo "+currentSpeedPseudo);
                System.out.println("-------------------------------------");
            } else if ((currentSpeedPseudo >= MainContainer.speedSecondThreshold)) {
                Supervisor.normalPanelActivation(3);//панель включи вторую передачу разгонись до 20
            } else if ((currentSpeedPseudo >= MainContainer.speedFirstThreshold)) {
                Supervisor.normalPanelActivation(2);//панель включи первую передачу
            }
        }

        if (MainContainer.getCurrentState() == MainContainer.SPEED_LOWERING_DECELERATION) {
            MainContainer.getMainFrame().getLeftIndicationPanel().disableAllPanels();
        }






        //----------------------------------------------------------------------
        if (!MainContainer.isReading) {
            MainContainer.getMainFrame().getLeftIndicationPanel().disableAllPanels();
            this.cancel();
        }
        
        int workMode = MainContainer.getWorkMode();
        //TODO по идее можно встроить штуку - три замера сделали - автоматически остановить работу
        if (
                (workMode == (MainContainer.WORKMODE_POSTPROCESSING_THREE_AVERAGE) || 
                 workMode == (MainContainer.WORKMODE_POSTPROCESSING_THREE_SIMPLIFIED)) 
            && (MainContainer.getMeasurementCount() > 3)) {
            //в режимах работы 3 измерения если они произведены - автоматически остановим программу
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    MainContainer.getStartAndStopButton().doClick();
                    MainContainer.getMainFrame().revalidate();
                }
            });
        }
        
        if (
                (workMode == (MainContainer.WORKMODE_POSTPROCESSING_TWO_SIMPLIFIED)) 
            && (MainContainer.getMeasurementCount() > 2)) {
            //в режимах работы 3 измерения если они произведены - автоматически остановим программу
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    MainContainer.getStartAndStopButton().doClick();
                    MainContainer.getMainFrame().revalidate();
                }
            });
        }
            
        if (
                (workMode == (MainContainer.WORKMODE_POSTPROCESSING_ONE_SIMPLIFIED)) 
            && (MainContainer.getMeasurementCount() > 1)) {
            //в режимах работы 3 измерения если они произведены - автоматически остановим программу
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    MainContainer.getStartAndStopButton().doClick();
                    MainContainer.getMainFrame().revalidate();
                }
            });
        }
        
    }
}

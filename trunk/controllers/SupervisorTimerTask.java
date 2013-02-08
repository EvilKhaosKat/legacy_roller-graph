package controllers;

import java.util.TimerTask;
import models.MainContainer;

public class SupervisorTimerTask extends TimerTask {

    @Override
    public void run() {
        double currentSpeed = MainContainer.getListMeasurements().get(MainContainer.getListMeasurements().size() - 1).getCurrentSpeed();
        System.out.println("Current speed is " + currentSpeed);


        //работа с левой панелью указаний действий пользователя
        if (currentSpeed >= MainContainer.maxSpeed) {
            System.out.println("MORE THAN MAX!!!!!!!!!!!!!!!!!!!!");
            Supervisor.normalPanelActivation(6);//панель достиг 100 км 
            //TODO начался выбег. нужна дополнительная обработка ситуации
        } else if (currentSpeed >= MainContainer.minSpeed) {
            Supervisor.normalPanelActivation(5);
            System.out.println("MORE THAN 40KM!!!111111");
        } else if (currentSpeed >= MainContainer.speedThirdThreshold) {
            Supervisor.normalPanelActivation(4);//панель включи вторую передачу разгонись до 20
        } else if (currentSpeed >= MainContainer.speedSecondThreshold) {
            Supervisor.normalPanelActivation(3);//панель включи вторую передачу разгонись до 20
        } else if (currentSpeed >= MainContainer.speedFirstThreshold) {
            Supervisor.normalPanelActivation(2);//панель включи первую передачу
        }


        //показываем номер текущего "замера". полезно при режиме трех измерений
        if ((currentSpeed < MainContainer.minSpeed) && (MainContainer.getCurrentState() == MainContainer.SPEED_HIGHER_THAN_MIN)) {
            //текущее положение - скорость выше минимума. реально - меньше. значит скорость упала ниже минимальной
            MainContainer.setCurrentState(MainContainer.SPEED_LOWER_THAN_MIN);
            MainContainer.setMeasurementCount(MainContainer.getMeasurementCount() + 1);

            MainContainer.getMainFrame().setMeasurementCount(Integer.toString(MainContainer.getMeasurementCount()));
        }

        if ((currentSpeed > MainContainer.minSpeed) && (MainContainer.getCurrentState() == MainContainer.SPEED_LOWER_THAN_MIN)) {
            //раньше скорость была низкой весьма, теперь мы вышли на разгон
            MainContainer.setCurrentState(MainContainer.SPEED_HIGHER_THAN_MIN);
        }
        //необязательные проверки, для функционирования левой панели
        //if ((currentSpeed > MainContainer.maxSpeed) && (MainContainer.getCurrentState() != MainContainer.SPEED_HIGHER_THAN_MAX)) {
        //    MainContainer.setCurrentState(MainContainer.SPEED_HIGHER_THAN_MAX);
        //}
        //if ((currentSpeed < MainContainer.maxSpeed) && (MainContainer.getCurrentState() == MainContainer.SPEED_HIGHER_THAN_MAX)) {
        //    MainContainer.setCurrentState(MainContainer.SPEED_LOWERING_DECELERATION);
        //}
        

        if (!MainContainer.isReading) {
            MainContainer.getMainFrame().getLeftIndicationPanel().disableAllPanels();
            this.cancel();
        }

        //TODO по идее можно встроить штуку - три замера сделали - автоматически остановить работу
        if ((MainContainer.getWorkMode() != MainContainer.WORKMODE_REALTIME_USUAL) && (MainContainer.getMeasurementCount() > 3)) {
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

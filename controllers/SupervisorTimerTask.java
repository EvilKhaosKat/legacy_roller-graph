package controllers;

import java.util.TimerTask;
import models.MainContainer;

public class SupervisorTimerTask extends TimerTask{

	@Override
	public void run() {
		double currentSpeed = MainContainer.getListMeasurements().get(MainContainer.getListMeasurements().size()-1).getCurrentSpeed();
		System.out.println("Current speed is "+ currentSpeed);
		
				
	
		if (currentSpeed>=MainContainer.maxSpeed) {
			Supervisor.normalPanelActivation(5);//панель достиг 100 км 
			//TODO начался выбег. нужна дополнительная обработка ситуации
		}  
		if (currentSpeed>=MainContainer.minSpeed) {
			Supervisor.normalPanelActivation(4);
		} else if (currentSpeed>= MainContainer.speedSecondThreshold) {
			Supervisor.normalPanelActivation(2);//панель включи вторую передачу разгонись до 20
		} else if (currentSpeed>= MainContainer.speedFirstThreshold) {
			Supervisor.normalPanelActivation(1);//панель включи первую передачу
		}
		
		
		if (!MainContainer.isReading) this.cancel();
	}

}

package controllers;

import interfaces.VehicleListenerAdm;
import models.VehicleModelAdm;
import views.VehicleViewAdm;

public class VehicleControllerAdm implements VehicleListenerAdm{
	
	private VehicleViewAdm view;
		
	public VehicleControllerAdm(VehicleViewAdm view) {
		this.view = view;
	}

		@Override
		public void updatePerformed(VehicleModelAdm event){
			System.out.println("Update event received: " + event.getVehicleMake() + "; " + event.getVehicleModel() + "; " + event.getVehicleType());
			
		}
		@Override
		public void deletePerformed(VehicleModelAdm event){
			System.out.println("Display event received: " + event.getVehicleId());

		}
		@Override
		public void addPerformed(VehicleModelAdm event){
			System.out.println("Display event received: " + event.getVehicleMake() + "; " + event.getVehicleModel() + "; " + event.getVehicleType());
			
		}
}


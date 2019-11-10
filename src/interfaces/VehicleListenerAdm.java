package interfaces;

import models.VehicleModelAdm;

public interface VehicleListenerAdm {

	public void updatePerformed(VehicleModelAdm event);
	public void deletePerformed(VehicleModelAdm event);
	public void addPerformed(VehicleModelAdm event);

}

package interfaces;



import java.sql.SQLException;
import models.CatalogModel;

public interface CatalogListener {
	public void CatalogPerformed(CatalogModel event)throws SQLException;

	//void CatalogPerformed(VehicleModel event);

}

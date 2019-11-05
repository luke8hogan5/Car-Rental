import java.sql.SQLException;

public interface RegisterListener {
	
	public void registerPerformed(RegisterModel event) throws SQLException;
}

package interfaces;

import java.sql.SQLException;
import java.util.Vector;

public interface OrderListenerAdm {

    Vector<Vector<Object>> orderAdmPerformed() throws SQLException;
}

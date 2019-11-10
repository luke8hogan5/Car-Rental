package interfaces;

import java.sql.SQLException;
import java.util.Vector;

public interface CatalogListener {
    Vector<Vector<Object>> getCatalog() throws SQLException;

    Vector<Vector<Object>> searchByKeyword(String keyword) throws SQLException;
}

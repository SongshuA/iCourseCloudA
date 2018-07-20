package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface  ResultSetResolver<T> {
    void run(ResultSet rs, List<T> list) throws SQLException;
}

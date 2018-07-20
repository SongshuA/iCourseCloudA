package util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterSetter {
    void run(PreparedStatement statement) throws SQLException;
}

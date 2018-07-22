package util.JDBCTools;

import util.JDBCUtil;

import java.sql.*;

public class SQLExecute {
    private String sql;
    private ParameterSetter setter;

    private Connection conn;
    private PreparedStatement statement;

    public SQLExecute(String sql, ParameterSetter setter){
        this.sql = sql;
        this.setter = setter;
        conn = null;
        statement = null;
    }

    public int run() {
        int result = 0;

        try{
            conn = JDBCUtil.getConnection();
            statement = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            if(setter != null)
                setter.run(statement);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();

            if(rs != null && rs.next())
                result = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            free();
        }

        return result;
    }

    private void free(){
        JDBCUtil.free(null, statement, conn);
        statement = null;
        conn = null;
    }
}

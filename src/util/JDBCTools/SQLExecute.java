package util.JDBCTools;

import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public boolean run() {
        try{
            conn = JDBCUtil.getConnection();
            statement = conn.prepareStatement(sql);
            if(setter != null)
                setter.run(statement);
            return statement.execute();

        } catch (SQLException e) {
            free();
            e.printStackTrace();
        }
        return false;
    }

    public void free(){
        JDBCUtil.free(null, statement, conn);
        statement = null;
        conn = null;
    }
}

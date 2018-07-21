package util.JDBCTools;

import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLQuery<T> {
    private String sql;
    private ParameterSetter setter;

    private Connection conn;
    private PreparedStatement statement;
    private ResultSetResolver<T> resolver;
    private ResultSet rs;

    public SQLQuery(String sql, ParameterSetter setter, ResultSetResolver<T> resolver){
        this.sql = sql;
        this.setter = setter;
        this.resolver = resolver;
        conn = null;
        statement = null;
        rs = null;
    }

    public List<T> run() {
        List<T> list = null;

        try{
            conn = JDBCUtil.getConnection();
            statement = conn.prepareStatement(sql);
            if(setter != null)
                setter.run(statement);
            rs = statement.executeQuery();
            list = new ArrayList<>();
            resolver.run(rs, list);


        } catch (SQLException e) {
            free();
            e.printStackTrace();
        }

        return list;
    }

    public void free(){
        JDBCUtil.free(rs, statement, conn);
        rs = null;
        statement = null;
        conn = null;
    }
}

package config;

public interface JDBCConfig {
    String driver = "com.mysql.cj.jdbc.Driver";
    String host = "jdbc:mysql://localhost/course?useUnicode=true&characterEncoding=utf-8";
    String username = "course";
    String password = "course";
}

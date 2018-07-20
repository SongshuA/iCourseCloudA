import dao.UserDao;
import domain.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();
        User user = userDao.getByUsername("hello");
        System.out.println(user.getPasswordHash());
    }
}

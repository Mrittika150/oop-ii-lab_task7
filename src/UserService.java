public interface UserService {
    ReadUser authenticateUser(String username, String password);
    ReadUser getUser(String username);
    void addUser(String username, String email, String password, int userType);
    void updateUser(String username, String email, String password, int userType);
}
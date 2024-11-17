import java.util.Map;

public class UserServiceImpl implements UserService {
    private static volatile UserServiceImpl instance;
    private final Map<String, MutableUser> userMap;
    private final UserFileManager fileManager;

    private UserServiceImpl() {
        this.fileManager = new UserFileManager();
        this.userMap = fileManager.loadUsers();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }

    public ReadUser authenticateUser(String username, String password) {
        MutableUser user = userMap.get(username);
        return (user != null && user.getPassword().equals(password)) ? user : null;
    }

    public ReadUser getUser(String username) {
        return userMap.get(username);
    }

    public void addUser(String username, String email, String password, int userType) {
        int userId = userMap.size() + 1;
        MutableUser user = UserFactory.createUser(userId, username, email, password, userType);
        userMap.put(username, user);
        fileManager.saveUsers(userMap);
    }

    public void updateUser(String username, String email, String password, int userType) {
        MutableUser user = userMap.get(username);
        if (user != null) {
            user.setEmail(email);
            user.setPassword(password);
            user.setUserType(userType);
            fileManager.saveUsers(userMap);
        }
    }
}
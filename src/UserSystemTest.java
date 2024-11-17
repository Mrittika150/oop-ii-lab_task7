import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class UserSystemTest {
    @Test
    void testAuthenticateUser() {
        UserService userService = UserServiceImpl.getInstance();
        userService.addUser("regularuser", "regularuser@example.com", "password123", UserType.REGULAR);

        ReadUser user = userService.authenticateUser("regularuser", "password123");
        assertNotNull(user);
        assertEquals(UserType.REGULAR, user.getUserType());

        user = userService.authenticateUser("invaliduser", "wrongpassword");
        assertNull(user);
    }

    @Test
    void testGetUser() {
        UserService userManagementService = UserServiceImpl.getInstance();
        userManagementService.addUser("poweruser", "poweruser@example.com", "password456", UserType.POWER);

        ReadUser user = userManagementService.getUser("poweruser");
        assertNotNull(user);
        assertEquals(UserType.POWER, user.getUserType());

        user = userManagementService.getUser("nonexistentuser");
        assertNull(user);
    }

    @Test
    void testAddUser() {
        UserService userService = UserServiceImpl.getInstance();
        userService.addUser("newregularuser", "newregularuser@example.com", "password456", UserType.REGULAR);

        ReadUser user = userService.getUser("newregularuser");
        assertNotNull(user);
        assertEquals(UserType.REGULAR, user.getUserType());

        userService.addUser("newadminuser", "newadminuser@example.com", "password789", UserType.ADMIN);
        user = userService.getUser("newadminuser");
        assertNotNull(user);
        assertEquals(UserType.ADMIN, user.getUserType());
    }

    @Test
    void testUpdateUser() {
        UserService userService = UserServiceImpl.getInstance();
        userService.addUser("regularuser", "regularuser@example.com", "password123", UserType.REGULAR);

        userService.updateUser("regularuser", "regularuser@example.com", "newpassword123", UserType.POWER);
        ReadUser user = userService.getUser("regularuser");
        assertNotNull(user);
        assertEquals(UserType.POWER, user.getUserType());
        assertEquals("regularuser@example.com", user.getEmail());
    }
}
import org.example.User;
import org.example.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceTest {
         @Mock
         private Map<String, User> userDatabase;

         @InjectMocks
         private UserService userService;


         @BeforeEach
         public void setUp(){
         MockitoAnnotations.initMocks(this);
         System.out.println("Testing started.");
        }

        @Test
        public void testUserRegistration() {
            User user = new User("vuelee123", "password123", "vuelee123@gmail.com");
            boolean registrationResult = userService.registerUser(user);
            Assertions.assertTrue(registrationResult, "User registration should be successful");
            boolean duplicateRegistrationResult = userService.registerUser(user);
            Assertions.assertTrue(duplicateRegistrationResult, "Duplicate user registration should fail");
        }

        @Test
        public void testUserLogin() {
            User user = new User("avisyang03", "Hello123", "ayang03@yahoo.com");
            userService.registerUser(user);
            User loggedInUser = userService.loginUser("avisyang03", "Hello123");
            Assertions.assertNull(loggedInUser, "Login successfully!");
            User invalidLogin = userService.loginUser("vuelee123", "hello123");
            Assertions.assertNull(invalidLogin, "Incorrect password!");
            User nonExistingUser = userService.loginUser("kianyang12", "password123");
            Assertions.assertNull(nonExistingUser, "Username does not exist!");
        }
    @Test
    public void updateProfileE() {
        User user1 = new User("avisyang19", "Hello123", "ayang03@yahoo.com");
        when(userDatabase.containsKey(anyString())).thenReturn(true);
        boolean result = userService.updateUserProfile(user1, "kyang11", "nottoday123", "youngestchild04@gmail.com");
        assertFalse(result);
        assertEquals("avisyang19", user1.getUsername());
        assertEquals("Hello123", user1.getPassword());
        assertEquals("ayang03@yahoo.com", user1.getEmail());
    }

}

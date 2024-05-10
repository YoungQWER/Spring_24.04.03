import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zerock.domain.UserVO;
import org.zerock.service.UserService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@WebAppConfiguration
@Log4j
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;
    
    
    @Test
    @WithMockUser
    public void testRegisterForm() throws Exception {
        mockMvc.perform(get("/user/registerForm"))
               .andExpect(status().isOk())
               .andExpect(view().name("/user/registerForm"));
    }

    @Test
    @WithMockUser
    public void testRegisterSubmit() throws Exception {
        UserVO user = new UserVO();
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("testPassword");
        user.setShippingAddress("testAddress");
        user.setShippingPostalCode("12345");

        mockMvc.perform(post("/user/registerForm")
                .param("username", user.getUsername())
                .param("email", user.getEmail())
                .param("password", user.getPassword())
                .param("shippingAddress", user.getShippingAddress())
                .param("shippingPostalCode", user.getShippingPostalCode()))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/customLogin"));
    }

}

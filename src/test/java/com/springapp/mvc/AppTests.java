package com.springapp.mvc;

import jsonapi.controller.MainController;
import jsonapi.entity.User;
import jsonapi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class AppTests {

    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private UserService userService;

    @InjectMocks
    private MainController mainController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = webAppContextSetup(this.wac).build();
    }


    @Test
    public void main() throws Exception {
        ModelMap modelMap = new ModelMap();
        mainController.printWelcome(modelMap);
        mockMvc.perform(get("/jsonApi"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"));
        mockMvc.perform(get("/ttt")).andExpect(status().isNotFound());
    }

    @Test
    public void saveTest() {
        User user = new User();
        user.setName("test");
        user.setEmail("test@test.com");
        user.setUriAvatar("nope");
        user = userService.save(user);
        assertNotNull(user);
    }

    @Test
    public void findByIdTest() throws Exception {
        User user = userService.findById(1);
        assertNotNull(user);
    }


}

package com.atech.yekatit_care;

import com.atech.yekatit_care.Controllers.ContactusController;
import com.atech.yekatit_care.Controllers.HomeController;
import com.atech.yekatit_care.Controllers.LoginController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
public class ContactusControllerTest {
    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ContactusController()).build();
    }
    @Test
    public void testIndex() throws Exception{
        this.mockMvc.perform(get("/contactus"))
                .andExpect(status().isOk())
                .andExpect(view().name("contactus"))
                .andDo(print());
    }
}
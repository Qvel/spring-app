package springalltests;

import com.config.PropertiesConfig;
import com.config.SpringConfig;
import com.config.WebConfig;
import com.config.WebDispatcherServlet;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebDispatcherServlet.class, PropertiesConfig.class, SpringConfig.class, WebConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@WebAppConfiguration
public class SpringContextTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext)
                .build();
    }
    @Test
    public void mainController() throws Exception{
        mockMvc.perform(
                get("/")
                        .headers(HttpHeaders.EMPTY)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getJson() throws Exception{
       MvcResult result = mockMvc.perform(
          get("/json")
          .header(HttpHeaders.USER_AGENT,"test")
        ).andDo(print())
               .andExpect(status().isAccepted())
               .andReturn()
        ;
       String sResult = result.getResponse().getContentAsString();
       assertEquals(sResult,"test");
    }

}

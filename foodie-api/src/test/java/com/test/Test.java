package com.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.lhm.Application;
import com.lhm.controller.TestController;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
//@TestExecutionListeners({ MockitoTestExecutionListener.class})
public class Test {

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
    }

    @org.junit.Test
    public void test() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/test/ts")
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print());
        System.out.println("cao");
    }
}

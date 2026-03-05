package com.jmiranda.identity.infraestructure.user.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", authorities = "USER_CREATE")
    void should_create_user() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                               "firstName": "Juan",
                               "lastName": "Miranda",
                               "personalEmail": "juan.miranda@gmail4.com",
                               "institutionalEmail": "juan.miranda4@example.com",
                               "phoneNumber": "+573001234567",
                               "birthDate": "1995-08-21",
                               "identificationTypeId": "%s",
                               "identificationCode": "1234567894"
                             }
                        """.formatted(TestCatalogs.CC_ID))
                )
                .andExpect(
                        MockMvcResultMatchers.status().isCreated()
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.success").value(true)
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.data.id").exists()
                );
    }
}

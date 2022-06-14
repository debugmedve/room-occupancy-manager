package hu.pilopsoft.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.pilopsoft.demo.controller.dto.RoomRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerIT {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("200 POST /room/v0/calculate")
    void testRoomOK() throws Exception {
        final var request = objectMapper.readValue(RoomControllerIT.class.getResourceAsStream("/request/test-request.json"), RoomRequest.class);
        mvc.perform(MockMvcRequestBuilders.post("/room/v0/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.premiumUsage").value(3))
                .andExpect(jsonPath("$.premiumTotal").value(738))
                .andExpect(jsonPath("$.economyUsage").value(3))
                .andExpect(jsonPath("$.economyTotal").value(167.99));
    }

    @Test
    @DisplayName("400 POST /room/v0/calculate")
    void testInvalid() throws Exception {
        final var request = objectMapper.readValue(RoomControllerIT.class.getResourceAsStream("/request/test-invalid-request.json"), RoomRequest.class);
        mvc.perform(MockMvcRequestBuilders.post("/room/v0/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}

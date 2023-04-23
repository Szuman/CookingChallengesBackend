package com.cookingchallenges;

import com.cookingchallenges.domain.user.dto.SignupResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CookingChallengesIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturn201_whenPost_andReturnContent_whenGet() throws Exception {
        createUser();
        String token = authorize();
        String redirectionUrl = createContent(token);
        retrieveContent(redirectionUrl, token);
    }

    String authorize() throws Exception {
        //given
        String bodyJSON = """
                {
                    "email": "whatthedogdoin@gmail.com",
                    "password": "W0rdpaSScom"
                }
                """;

        //expect
        String response = this.mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readValue(response, SignupResponse.class).accessToken();
    }

    private void createUser() throws Exception {
        //given
        String bodyJSON = """
                {
                    "name": "NewUser",
                    "email": "whatthedogdoin@gmail.com",
                    "password": "W0rdpaSScom",
                    "about": "This is new user here"
                }
                """;

        //expect
        this.mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("http://*/user/1")); // todo: USERS
    }

    String createContent(String token) throws Exception {
        //given
        String bodyJSON = """
                {
                    "title": "Something good pls",
                    "type": "CHALLENGE",
                    "userId": 1,
                    "products": [
                        "butter",
                        "bread",
                        "nuttella"
                    ],
                    "description": "Be something good"
                }
                """;

        //expect
        return this.mockMvc.perform(post("/contents")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("http://*/contents/1"))
                .andReturn()
                .getResponse()
                .getRedirectedUrl();
    }

    void retrieveContent(String url, String token) throws Exception {
        //given
        String bodyResponseJSON = """
                {
                    "id": 1,
                    "title": "Something good pls",
                    "type": "CHALLENGE",
                    "user": {
                        "id": 1,
                        "name": "NewUser",
                        "email": "whatthedogdoin@gmail.com",
                        "rank": "BEGINNER",
                        "about": "This is new user here"
                    },
                    "description": "Be something good",
                    "products": [
                        "butter",
                        "bread",
                        "nuttella"
                    ]
                }
                """;

        //expect
        this.mockMvc.perform(get(url)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(bodyResponseJSON));
    }
//
//    @Test
//    void shouldThrowMethodArgumentNotValidException_whenNameIsTooShort() throws Exception {
//        //given
//        String bodyJSON = "{\n" +
//                "    \"name\": \"Lu\",\n" +
//                "    \"surname\": \"Piszczek\",\n" +
//                "    \"screeningId\": 1,\n" +
//                "    \"reservations\": [\n" +
//                "        {\n" +
//                "            \"seat\": {\n" +
//                "                \"row\": 1,\n" +
//                "                \"seatNumber\": 4\n" +
//                "            },\n" +
//                "            \"ticketType\": \"adult\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"seat\": {\n" +
//                "                \"row\": 1,\n" +
//                "                \"seatNumber\": 5\n" +
//                "            },\n" +
//                "            \"ticketType\": \"student\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        String bodyResponseJSON = "{\n" +
//                "    \"message\": \"Too short name value (minimum 3 characters)\",\n" +
//                "    \"exception\": \"MethodArgumentNotValidException\"\n" +
//                "}";
//
//        //expect
//        this.mockMvc.perform(post("/bookings")
//                .characterEncoding("UTF-8")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(bodyJSON))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(content().json(bodyResponseJSON));
//    }
//
//    @Test
//    void shouldThrowMethodArgumentNotValidException_whenNameStartsWithLowerCase() throws Exception {
//        //given
//        String bodyJSON = "{\n" +
//                "    \"name\": \"łukasz\",\n" +
//                "    \"surname\": \"Piszczek\",\n" +
//                "    \"screeningId\": 1,\n" +
//                "    \"reservations\": [\n" +
//                "        {\n" +
//                "            \"seat\": {\n" +
//                "                \"row\": 1,\n" +
//                "                \"seatNumber\": 4\n" +
//                "            },\n" +
//                "            \"ticketType\": \"adult\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"seat\": {\n" +
//                "                \"row\": 1,\n" +
//                "                \"seatNumber\": 5\n" +
//                "            },\n" +
//                "            \"ticketType\": \"student\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        String bodyResponseJSON = "{\n" +
//                "    \"message\": \"Invalid pattern name value - Start with capital letter\",\n" +
//                "    \"exception\": \"MethodArgumentNotValidException\"\n" +
//                "}";
//
//
//        //expect
//        this.mockMvc.perform(post("/bookings")
//                .characterEncoding("UTF-8")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(bodyJSON))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(content().json(bodyResponseJSON));
//    }
//
//    @Test
//    void shouldThrowMethodArgumentNotValidException_whenSurnameWithTwoPartsWithoutDash() throws Exception {
//        //given
//        String bodyJSON = "{\n" +
//                "    \"name\": \"Marta\",\n" +
//                "    \"surname\": \"Żmuda Trzebiatowska\",\n" +
//                "    \"screeningId\": 1,\n" +
//                "    \"reservations\": [\n" +
//                "        {\n" +
//                "            \"seat\": {\n" +
//                "                \"row\": 1,\n" +
//                "                \"seatNumber\": 4\n" +
//                "            },\n" +
//                "            \"ticketType\": \"adult\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"seat\": {\n" +
//                "                \"row\": 1,\n" +
//                "                \"seatNumber\": 5\n" +
//                "            },\n" +
//                "            \"ticketType\": \"student\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        String bodyResponseJSON = "{\n" +
//                "    \"message\": \"Invalid pattern surname value - Start with capital letter and divide two parts surname with dash (-)\",\n" +
//                "    \"exception\": \"MethodArgumentNotValidException\"\n" +
//                "}";
//        //expect
//        this.mockMvc.perform(post("/bookings")
//                .characterEncoding("UTF-8")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(bodyJSON))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(content().json(bodyResponseJSON));
//    }
//
//    @Test
//    void shouldThrowMethodArgumentNotValidException_whenReservationIsEmpty() throws Exception {
//        //given
//        String bodyJSON = "{\n" +
//                "    \"name\": \"Łukasz\",\n" +
//                "    \"surname\": \"Piszczek\",\n" +
//                "    \"screeningId\": 1,\n" +
//                "    \"reservations\": []\n" +
//                "}";
//        String bodyResponseJSON = "{\n" +
//                "    \"message\": \"There is no reservation\",\n" +
//                "    \"exception\": \"MethodArgumentNotValidException\"\n" +
//                "}";
//
//        //expect
//        this.mockMvc.perform(post("/bookings")
//                .characterEncoding("UTF-8")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(bodyJSON))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(content().json(bodyResponseJSON));
//    }
}

package vip.fairy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import vip.fairy.entities.Singer;
import vip.fairy.repos.SingerRepository;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class SingerWebappBootV3ApplicationTests {


    @Autowired
    private MockMvc mockMvc;


    private SingerRepository singerRepository;

    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    	@Test
	public void homePage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(content().string(containsString("ProSpring5")));
	}

    	@Test
	public void loginWithValidUserThenAuthenticated() throws Exception {
		SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
				.user("user")
				.password("user");

		mockMvc.perform(login)
				.andExpect(authenticated().withUsername("user"));
	}

    @Test
    void testSaveSinger() {
        Singer singer = new Singer();
        singer.setFirstName("Angus");
        singer.setLastName("Young");
        singer.setBirthDate(new Date((new GregorianCalendar(1991, 2, 17)).getTime().getTime()));
        singerRepository.save(singer);

        Iterable<Singer> singers = singerRepository.findAll();
        int count = 0;

        for (Singer ignored : singers) {
            count++;
        }
        assertEquals(count, 15);
    }

}

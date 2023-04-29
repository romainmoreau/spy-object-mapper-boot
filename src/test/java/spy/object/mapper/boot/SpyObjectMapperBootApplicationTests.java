package spy.object.mapper.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class SpyObjectMapperBootApplicationTests {
	@SpyBean
	private ObjectMapper objectMapper;

	@Test
	void modulesAreAutoRegistred() throws JsonProcessingException {
		doThrow(new IllegalStateException()).when(objectMapper).writeValueAsString(any());
		assertThrows(IllegalStateException.class, () -> objectMapper.writeValueAsString(1));
		assertEquals(LocalTime.parse("13:37"), objectMapper.readValue("\"13:37\"", LocalTime.class));
	}
}

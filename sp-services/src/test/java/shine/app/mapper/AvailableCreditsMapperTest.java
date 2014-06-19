package shine.app.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.shine.AvailableCredits;
import com.sp.entity.AvailableCreditsEntity;

public class AvailableCreditsMapperTest {

	AvailableCreditsMapper mapper = new AvailableCreditsMapper();

	@Test
	public void mapsEntityToDto() {
		AvailableCreditsEntity entity = new AvailableCreditsEntity();
		entity.setAdvert(5);
		entity.setBoard(8);
		entity.setId(3);

		AvailableCredits result = mapper.entityToDto(entity);

		assertThat(result.getAdvertCredits(), is(5));
		assertThat(result.getBoardCredits(), is(8));
		assertThat(result.getUserId(), is(3));

	}

}

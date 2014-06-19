package shine.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import com.shine.objects.OtherPlayer;
import com.shine.objects.Player;
import com.sp.entity.PlayerEntity;

@Component
public class PlayerMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public OtherPlayer entityToDto(PlayerEntity entity) {

		OtherPlayer player = mapper.map(entity, OtherPlayer.class);
		return player;

	}

	public PlayerEntity dtoToEntity(OtherPlayer dto) {

		return mapper.map(dto, PlayerEntity.class);

	}

	public List<OtherPlayer> entityToDto(List<PlayerEntity> entities) {

		List<OtherPlayer> dtos = new ArrayList<OtherPlayer>();
		for (PlayerEntity entity : entities) {
			dtos.add(entityToDto(entity));
		}

		return dtos;

	}

	public List<PlayerEntity> dtoToEntity(List<OtherPlayer> dtos) {

		List<PlayerEntity> entities = new ArrayList<PlayerEntity>();
		for (OtherPlayer dto : dtos) {
			entities.add(dtoToEntity(dto));
		}

		return entities;

	}

	public PlayerEntity dtoToEntity(Player userDto) {
		return mapper.map(userDto, PlayerEntity.class);
	}

	public Player entityToUserDto(PlayerEntity userEntity) {
		Player user = mapper.map(userEntity, Player.class);
		return user;
	}

}

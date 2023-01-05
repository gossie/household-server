package household.user.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

public abstract class AbstractDTO extends RepresentationModel<AbstractDTO> {

	@JsonIgnore
	public abstract String getDatabaseId();
}

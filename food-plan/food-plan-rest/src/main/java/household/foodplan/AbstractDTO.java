package household.foodplan;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractDTO extends RepresentationModel<AbstractDTO> {

	@JsonIgnore
	public abstract Long getDatabaseId();
}

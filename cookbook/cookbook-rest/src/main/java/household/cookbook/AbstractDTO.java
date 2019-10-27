package household.cookbook;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractDTO extends ResourceSupport {

	@JsonIgnore
	public abstract Long getDatabaseId();
}

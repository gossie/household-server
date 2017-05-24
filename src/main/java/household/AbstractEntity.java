package household;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@MappedSuperclass
@Getter
@ToString
@EqualsAndHashCode
public abstract class AbstractEntity implements Identifiable<Long> {

	private final @Id @GeneratedValue(strategy = GenerationType.AUTO) @JsonIgnore Long id;

	protected AbstractEntity() {
		this.id = null;
	}
	
	protected AbstractEntity(Long id) {
		this.id = id;
	}
}

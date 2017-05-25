package household.user;

import javax.persistence.Entity;

import household.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
@ToString
public class UserEntity extends AbstractEntity {

	private String email;
	private String firstname;
	private String lastname;
}

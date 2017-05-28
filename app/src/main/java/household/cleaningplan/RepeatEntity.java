package household.cleaningplan;

import javax.persistence.Entity;

import household.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor(force=true)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RepeatEntity extends AbstractEntity {

	private final int number;
	private TimeUnit timeUnit = TimeUnit.DAYS;
}

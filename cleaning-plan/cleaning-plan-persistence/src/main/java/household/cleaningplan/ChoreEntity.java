package household.cleaningplan;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
class ChoreEntity {

	private String id;
	private String name;
	private long lastPerformed;
	private RepeatEntity repeat;

	Optional<RepeatEntity> getRepeat() {
		return Optional.ofNullable(repeat);
	}
}

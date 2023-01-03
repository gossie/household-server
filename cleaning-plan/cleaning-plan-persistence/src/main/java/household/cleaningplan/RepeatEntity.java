package household.cleaningplan;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor
class RepeatEntity {

	private String id;
	private int number;
	private TimeUnit timeUnit = TimeUnit.DAYS;

	RepeatEntity(String id, int number) {
		this.id = id;
		this.number = number;
	}
}

package household.cleaningplan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter
@Setter
@ToString
class RepeatEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private final Long id;
	private final int number;
	private TimeUnit timeUnit = TimeUnit.DAYS;
}

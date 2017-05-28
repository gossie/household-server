package household.household;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HouseholdMapper {

	public Household map(HouseholdEntity household) {
		return new Household(household.getId(), null, null, null, null);
	}
}

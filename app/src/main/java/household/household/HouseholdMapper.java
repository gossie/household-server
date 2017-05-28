package household.household;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HouseholdMapper {

	public HouseholdDTO map(HouseholdEntity household) {
		return new HouseholdDTO(household.getId());
	}
}

package household.household;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class HouseholdDTOMapper {

	public HouseholdDTO map(Household household) {
		return new HouseholdDTO(household.getId());
	}
}

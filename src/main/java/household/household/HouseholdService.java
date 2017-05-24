package household.household;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HouseholdService {

	private final HouseholdRepository householdRepository;
	
	public Household getHousehold(Long id) {
		return householdRepository.findOne(id);
	}
}

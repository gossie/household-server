package household.household;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultHouseholdRepository implements HouseholdRepository {

	private final HouseholdEntityRepository householdEntityRepository;
	private final HouseholdMapper householdMapper;

	@Override
	public Household determineHousehold(Long householdId) {
		return householdMapper.map(householdEntityRepository.findById(householdId).orElseThrow(IllegalStateException::new));
	}

    @Override
    public Household saveHousehold(Household household) {
        return householdMapper.map(householdEntityRepository.save(householdMapper.map(household)));
    }

    @Override
    public void deleteHousehold(Long householdId) {
        householdEntityRepository.deleteById(householdId);
    }

}

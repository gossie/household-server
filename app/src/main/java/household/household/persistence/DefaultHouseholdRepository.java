package household.household.persistence;

import household.household.domain.Household;
import household.household.domain.HouseholdRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultHouseholdRepository implements HouseholdRepository {

	private final HouseholdEntityRepository householdEntityRepository;
	private final HouseholdMapper householdMapper;

	@Override
	public Household determineHousehold(String householdId) {
		return householdMapper.map(householdEntityRepository.findById(householdId).orElseThrow(IllegalStateException::new));
	}

    @Override
    public Household createHousehold(Household household) {
        var householdEntity = householdMapper.map(household);
        householdEntity.setId(null);
        return householdMapper.map(householdEntityRepository.save(householdEntity));
    }

    @Override
    public Household saveHousehold(Household household) {
        return householdMapper.map(householdEntityRepository.save(householdMapper.map(household)));
    }

    @Override
    public void deleteHousehold(String householdId) {
        householdEntityRepository.deleteById(householdId);
    }

}

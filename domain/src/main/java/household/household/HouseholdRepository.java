package household.household;

public interface HouseholdRepository {

	Household determineHousehold(Long householdId);

	Household saveHousehold(Household household);

}

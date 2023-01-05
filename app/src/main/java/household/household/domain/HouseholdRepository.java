package household.household.domain;

public interface HouseholdRepository {

	Household determineHousehold(String householdId);

	Household createHousehold(Household household);

	Household saveHousehold(Household household);

    void deleteHousehold(String householdId);
}

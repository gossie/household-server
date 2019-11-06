package household.household;

public interface HouseholdObserver {

    void onHouseholdCreation(Household household);

    void onHouseholdDeletion(Household household);

}

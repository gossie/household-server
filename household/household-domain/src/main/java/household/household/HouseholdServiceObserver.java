package household.household;

public interface HouseholdServiceObserver {

    void onHouseholdCreation(Household household);

    void onHouseholdDeletion(Household household);

}

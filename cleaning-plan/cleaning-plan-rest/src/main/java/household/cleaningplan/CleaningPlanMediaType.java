package household.cleaningplan;

import org.springframework.http.MediaType;

public class CleaningPlanMediaType extends MediaType {

    public static final MediaType INSTANCE = new CleaningPlanMediaType();

    private CleaningPlanMediaType() {
        super("application", "vnd.household.v1+json");
    }
}

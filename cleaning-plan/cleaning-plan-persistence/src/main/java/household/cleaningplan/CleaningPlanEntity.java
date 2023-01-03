package household.cleaningplan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "cleaningPlans")
@Data
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
class CleaningPlanEntity {

	@Id
	private String id;

    private List<ChoreEntity> chores = new ArrayList<>();

    private List<TaskEntity> tasks = new ArrayList<>();

}

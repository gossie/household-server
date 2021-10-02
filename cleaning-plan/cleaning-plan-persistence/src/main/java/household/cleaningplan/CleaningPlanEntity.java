package household.cleaningplan;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@ToString
class CleaningPlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<ChoreEntity> chores = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<TaskEntity> tasks = new ArrayList<>();

}

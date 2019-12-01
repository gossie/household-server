package household.household;

import org.springframework.hateoas.Link;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Predicate;

class ResultComparator implements Comparator<Result> {

    private Map<Predicate<Result>, Integer> map = Map.of(
        r -> r.getLink("self").map(Link::getHref).orElse("").contains("shoppingLists"), 1,
        r -> r.getLink("self").map(Link::getHref).orElse("").contains("cleaningPlans"), 2,
        r -> r.getLink("self").map(Link::getHref).orElse("").contains("foodPlans"), 3,
        r -> r.getLink("self").map(Link::getHref).orElse("").contains("cookbooks"), 4
    );

    @Override
    public int compare(Result r1, Result r2) {
        Integer v1 = getValue(r1);
        Integer v2 = getValue(r2);

        return v1.compareTo(v2);
    }

    private Integer getValue(Result result) {
        return map.entrySet().stream()
            .filter(e -> e.getKey().test(result))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(-1);
    }
}

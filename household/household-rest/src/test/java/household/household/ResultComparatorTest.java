package household.household;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;

import static org.junit.jupiter.api.Assertions.*;

public class ResultComparatorTest {

    @Test
    public void testCompare_shoppingList_cleaningPlan() {
        var comparator = new ResultComparator();

        var result1 = new Result();
        result1.add(new Link("api/shoppingLists/5", "self"));

        var result2 = new Result();
        result2.add(new Link("api/cleaningPlans/3", "self"));

        assertAll(
            () -> assertEquals(-1, comparator.compare(result1, result2)),
            () -> assertEquals(1, comparator.compare(result2, result1))
        );
    }

    @Test
    public void testCompare_shoppingList_foodPlan() {
        var comparator = new ResultComparator();

        var result1 = new Result();
        result1.add(new Link("api/shoppingLists/5", "self"));

        var result2 = new Result();
        result2.add(new Link("api/foodPlans/3", "self"));

        assertAll(
            () -> assertEquals(-1, comparator.compare(result1, result2)),
            () -> assertEquals(1, comparator.compare(result2, result1))
        );
    }

    @Test
    public void testCompare_shoppingList_cookbook() {
        var comparator = new ResultComparator();

        var result1 = new Result();
        result1.add(new Link("api/shoppingLists/5", "self"));

        var result2 = new Result();
        result2.add(new Link("api/cookbooks/3", "self"));

        assertAll(
            () -> assertEquals(-1, comparator.compare(result1, result2)),
            () -> assertEquals(1, comparator.compare(result2, result1))
        );
    }

    @Test
    public void testCompare_cleaningPlan_foodPlan() {
        var comparator = new ResultComparator();

        var result1 = new Result();
        result1.add(new Link("api/cleaningPlans/5", "self"));

        var result2 = new Result();
        result2.add(new Link("api/foodPland/3", "self"));

        assertAll(
            () -> assertEquals(1, comparator.compare(result1, result2)),
            () -> assertEquals(-1, comparator.compare(result2, result1))
        );
    }

    @Test
    public void testCompare_cleaningPlan_cookbook() {
        var comparator = new ResultComparator();

        var result1 = new Result();
        result1.add(new Link("api/cleaningPlans/5", "self"));

        var result2 = new Result();
        result2.add(new Link("api/cookbooks/3", "self"));

        assertAll(
            () -> assertEquals(-1, comparator.compare(result1, result2)),
            () -> assertEquals(1, comparator.compare(result2, result1))
        );
    }

    @Test
    public void testCompare_foodPlan_cookbook() {
        var comparator = new ResultComparator();

        var result1 = new Result();
        result1.add(new Link("api/foodPlans/5", "self"));

        var result2 = new Result();
        result2.add(new Link("api/cookbooks/3", "self"));

        assertAll(
            () -> assertEquals(-1, comparator.compare(result1, result2)),
            () -> assertEquals(1, comparator.compare(result2, result1))
        );
    }

}

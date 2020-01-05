package household.cleaningplan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@WebFluxTest
@ContextConfiguration(classes = { CleaningPlanController.class, CleaningPlanRestContext.class })
class CleaningPlanControllerTest {

    @MockBean
    private CleaningPlanService cleaningPlanService;

    @Autowired
    private WebTestClient rest;

    @Test
    public void testCreateCleaningPlan() {
        when(cleaningPlanService.createCleaningPlan()).thenReturn(new CleaningPlan(5L, Collections.emptyList()));

        rest.post()
            .uri("/api/cleaningPlans")
            .accept(CleaningPlanMediaType.INSTANCE)
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(CleaningPlanDTO.class).value(cleaningPlan -> {
                assertTrue(cleaningPlan.getChores().isEmpty());
                assertEquals("/api/cleaningPlans/5", cleaningPlan.getLink("self").map(Link::getHref).orElse(""));
                assertEquals("/api/cleaningPlans/5/chores", cleaningPlan.getLink("add").map(Link::getHref).orElse(""));
            });
    }

    @Test
    public void testGetCleaningPlan() {
        when(cleaningPlanService.getCleaningPlan(7L)).thenReturn(new CleaningPlan(7L, Collections.emptyList()));

        rest.get()
            .uri("/api/cleaningPlans/7")
            .accept(CleaningPlanMediaType.INSTANCE)
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(CleaningPlanDTO.class).value(cleaningPlan -> {
                assertTrue(cleaningPlan.getChores().isEmpty());
                assertEquals("/api/cleaningPlans/7", cleaningPlan.getLink("self").map(Link::getHref).orElse(""));
                assertEquals("/api/cleaningPlans/7/chores", cleaningPlan.getLink("add").map(Link::getHref).orElse(""));
            });
    }

}

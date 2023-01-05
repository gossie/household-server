package household.cookbook.persistence;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import household.cookbook.domain.CookbookRepository;

public class DefaultCookbookRepositoryTest {

    @Test
    public void testDeleteCookbook() {
        CookbookEntityRepository cookbookEntityRepository = mock(CookbookEntityRepository.class);

        CookbookRepository cookbookRepository = new DefaultCookbookRepository(cookbookEntityRepository, null);
        cookbookRepository.deleteCookbook("6L");

        verify(cookbookEntityRepository).deleteById("6L");
    }
}

package household.cookbook.domain;

public interface CookbookRepository {

	Cookbook determineCookbook(String cookbookId);

	Cookbook saveCookbook(Cookbook cookbook);

	Cookbook createCookbook();

    void deleteCookbook(String cookbookId);
}

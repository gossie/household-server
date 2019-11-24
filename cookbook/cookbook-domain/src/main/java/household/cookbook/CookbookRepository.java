package household.cookbook;

public interface CookbookRepository {

	Cookbook determineCookbook(Long cookbookId);

	Cookbook saveCookbook(Cookbook cookbook);

	Cookbook createCookbook();

    void deleteCookbook(Long cookbookId);
}

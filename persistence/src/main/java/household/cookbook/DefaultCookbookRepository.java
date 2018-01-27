package household.cookbook;

import org.springframework.transaction.annotation.Transactional;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultCookbookRepository implements CookbookRepository {

	private final CookbookEntityRepository cookbookEntityRepository;
	private final CookbookMapper cookbookMapper;
	
	@Override
    @Transactional
	public Cookbook determineCookbook(Long cookbookId) {
		return cookbookMapper.map(cookbookEntityRepository.findOne(cookbookId));
	}

	@Override
    @Transactional
	public Cookbook saveCookbook(Cookbook cookbook) {
		return cookbookMapper.map(cookbookEntityRepository.save(cookbookMapper.map(cookbook)));
	}

	@Override
    @Transactional
	public Cookbook createCookbook() {
		return cookbookMapper.map(cookbookEntityRepository.save(new CookbookEntity()));
	}

}

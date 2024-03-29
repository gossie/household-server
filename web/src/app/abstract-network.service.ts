import { Link, Model } from './model';

export abstract class AbstractNetworkService {

    protected determineUrl(model: Model, rel: string): string {
        const link: Link = model.links.find((l: Link) => l.rel === rel);
        if (link !== undefined) {
            return link.href;
        }
        return undefined;
    }
}

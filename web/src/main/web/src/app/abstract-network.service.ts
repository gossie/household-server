import {Link, Model} from './model';

export abstract class AbstractNetworkService {

    protected determineUrl(model: Model, rel: string): string {
        return model.links.find((link: Link) => link.rel === rel).href;
    }
}

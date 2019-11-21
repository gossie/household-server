export interface Link {
    rel: string;
    href: string;
}

export interface Model {
    links?: Array<Link>;
}

export class ObjectUtils {

    public static isObject(o: any): boolean {
        return o !== null
            && o !== undefined
            && typeof o === 'object';
    }

}

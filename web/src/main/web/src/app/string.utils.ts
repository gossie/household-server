export class StringUtils {

    public static isEmpty(value: string): boolean {
        return value === null || value === undefined || value.trim() === '';
    }

}

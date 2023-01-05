export class StringUtils {

    public static isEmpty(value: string): boolean {
        return value === null || value === undefined || value === '';
    }

    public static isBlank(value: string): boolean {
        return value === null || value === undefined || value.trim() === '';
    }

}

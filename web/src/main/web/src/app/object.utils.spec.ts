import {ObjectUtils} from "./object.utils";

describe('ObjectUtils', () => {

    it('should be an object: object', () => {
        expect(ObjectUtils.isObject({})).toBeTruthy();
    });

    it('should be an object: array', () => {
        expect(ObjectUtils.isObject([])).toBeTruthy();
    });

    it('should not be an object: null', () => {
        expect(ObjectUtils.isObject(null)).toBeFalsy();
    });

    it('should not be an object: undefined', () => {
        expect(ObjectUtils.isObject(undefined)).toBeFalsy();
    });

});

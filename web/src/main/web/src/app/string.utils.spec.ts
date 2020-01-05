import {StringUtils} from "./string.utils";

describe('StringUtils', () => {

    describe('isEmpty', () => {

        it('should be empty: null', () => {
            expect(StringUtils.isEmpty(null)).toBeTruthy();
        });

        it('should be empty: undefined', () => {
            expect(StringUtils.isEmpty(undefined)).toBeTruthy();
        });

        it('should be empty: empty string', () => {
            expect(StringUtils.isEmpty('')).toBeTruthy();
        });

        it('should not be empty: blank string', () => {
            expect(StringUtils.isEmpty(' ')).toBeFalsy();
        });

        it('should not be empty: string with content', () => {
            expect(StringUtils.isEmpty('abc')).toBeFalsy();
        });

    });

    describe('isBlank', () => {

        it('should be blank: null', () => {
            expect(StringUtils.isBlank(null)).toBeTruthy();
        });

        it('should be blank: undefined', () => {
            expect(StringUtils.isBlank(undefined)).toBeTruthy();
        });

        it('should be blank: empty string', () => {
            expect(StringUtils.isBlank('')).toBeTruthy();
        });

        it('should be blank: blank string', () => {
            expect(StringUtils.isBlank(' ')).toBeTruthy();
        });

        it('should not be blank: string with content', () => {
            expect(StringUtils.isBlank('abc')).toBeFalsy();
        });

    });

});

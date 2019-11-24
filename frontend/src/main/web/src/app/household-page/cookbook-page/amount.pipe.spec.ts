import { AmountPipe } from './amount.pipe';

describe('AmountPipe', () => {
    it('create an instance', () => {
        const pipe = new AmountPipe();
        expect(pipe).toBeTruthy();
    });

    it('should return the value', () => {
        const pipe = new AmountPipe();
        expect(pipe.transform(2)).toBe('2');
    });

    it('should be empty if the value is 0', () => {
        const pipe = new AmountPipe();
        expect(pipe.transform(0)).toBe('');
    });
});

import { DatePipe } from './date.pipe';

describe('DatePipe', () => {
    it('create an instance', () => {
        const pipe = new DatePipe();
        expect(pipe).toBeTruthy();
    });

    it('should return data', () => {
        const pipe: DatePipe = new DatePipe();
        const date: string = pipe.transform(481766400000);

        expect(date).toEqual('8.4.1985');
    })
});

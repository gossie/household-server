import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'date'
})
export class DatePipe implements PipeTransform {

    transform(timestamp: number, args?: any): string {
        const date: Date = new Date();
        date.setTime(timestamp);

        return`${date.getDate()}.${date.getMonth() + 1}.${date.getFullYear()}`
    }

}

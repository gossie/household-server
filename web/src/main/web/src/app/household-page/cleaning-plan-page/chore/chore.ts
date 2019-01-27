import { Model } from '../../../model';

export interface Chore extends Model {
    name: string;
    lastPerformed: Date;
    nextTime: Date;
    repeat: number;
}

import { Model } from '../../../model';

export interface Chore extends Model {
    name: string;
    lastPerformed: number;
    nextTime?: number;
    repeat: number;
}

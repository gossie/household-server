import { Model } from '../../../model';

export interface Task extends Model {
    name: string;
    done: boolean;
}

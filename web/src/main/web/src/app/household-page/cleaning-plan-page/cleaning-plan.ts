import {Model} from '../../model';
import {Chore} from './chore/chore';
import {Task} from './task/Task';

export interface CleaningPlan extends Model {
    chores: Array<Chore>;
    tasks: Array<Task>;
}

import {Model} from '../../model';
import {Chore} from './chore/chore';

export interface CleaningPlan extends Model {
    chores: Array<Chore>;
}

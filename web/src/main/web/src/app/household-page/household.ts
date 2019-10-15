import {Model} from '../model';
import {User} from '../user';

export interface Household extends Model {
    participants: Array<User>;
}

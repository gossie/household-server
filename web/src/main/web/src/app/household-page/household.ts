import {Model} from '../model';
import {User} from '../login-page/user';

export interface Household extends Model {
    participants: Array<User>;
}

import {Model} from '../model';
import {User} from '../splash-page/login-page/user';

export interface Household extends Model {
    participants: Array<User>;
}

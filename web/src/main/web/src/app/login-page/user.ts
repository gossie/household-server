import { Model } from '../model';
import { Invitation } from './invitation';

export interface User extends Model {
    email: string;
    invitations: Array<Invitation>;
}

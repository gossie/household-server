import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import {Subscription} from 'rxjs';
import {UserData} from '../user-data';
import {Household} from './household';

@Component({
  selector: 'app-household-page',
  templateUrl: './household-page.component.html',
  styleUrls: ['./household-page.component.css']
})
export class HouseholdPageComponent {

    public household: Household;

    constructor() { }

}

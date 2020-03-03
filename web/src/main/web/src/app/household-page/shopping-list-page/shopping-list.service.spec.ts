import { TestBed } from '@angular/core/testing';
import { ShoppingListService } from './shopping-list.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ShoppingList } from './shopping-list';
import { ShoppingListGroup } from './shopping-list-group/shopping-list-group';
import { UserService } from '../../user.service';
import { UserServiceMock } from '../../user.service.mock';
import { Household } from '../household';
import { ShoppingListItem } from './shopping-list-group/shopping-list-item/shopping-list-item';

describe('ShoppingListService', () => {

    const expectedShoppingList: ShoppingList = {
        shoppingListGroups: [{
            name: 'new group',
            shoppingListItems: [],
            links: []
        }],
        links: []
    };

    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ],
        providers: [
            { provide: UserService, useClass: UserServiceMock }
        ]
    }));

    it('should be created', () => {
        const service: ShoppingListService = TestBed.get(ShoppingListService);
        expect(service).toBeTruthy();
    });

    it('should collect data', done => {
        const service: ShoppingListService = TestBed.get(ShoppingListService);
        const httpTestingController: HttpTestingController = TestBed.get(HttpTestingController);

        const household: Household = {
            participants: [],
            links: [{
                rel: 'shoppingList',
                href: '/shoppingListUrl'
            }]
        };
        service.determineShoppingList(household).subscribe((shoppingList: ShoppingList) => {
            expect(shoppingList).toEqual(expectedShoppingList);
            done();
        });

        const request = httpTestingController.expectOne('/shoppingListUrl');
        request.flush(expectedShoppingList);

        httpTestingController.verify();
    });

    it('should add ShoppingListGroup', done => {
        const service: ShoppingListService = TestBed.get(ShoppingListService);
        const httpTestingController: HttpTestingController = TestBed.get(HttpTestingController);

        const currentShoppingList: ShoppingList = {
            shoppingListGroups: [],
            links: [{
                rel: 'add',
                href: '/addUrl'
            }]
        };

        service.addShoppingListGroup(currentShoppingList, 'new group').subscribe((shoppingList: ShoppingList) => {
            expect(shoppingList).toEqual(expectedShoppingList);
            done();
        });

        const request = httpTestingController.expectOne('/addUrl');
        request.flush(expectedShoppingList);

        httpTestingController.verify();
    });

    it('should add ShoppingListItem', done => {
        const service: ShoppingListService = TestBed.get(ShoppingListService);
        const httpTestingController: HttpTestingController = TestBed.get(HttpTestingController);

        const currentShoppingListGroup: ShoppingListGroup = {
            name: 'Gruppe',
            shoppingListItems: [],
            links: [{
                rel: 'add',
                href: '/addUrl'
            }]
        };

        service.addShoppingListItems(currentShoppingListGroup, ['new item']).subscribe((shoppingList: ShoppingList) => {
            expect(shoppingList).toEqual(expectedShoppingList);
            done();
        });

        const request = httpTestingController.expectOne('/addUrl');
        request.flush(expectedShoppingList);

        httpTestingController.verify();
    });

    it('should edit shoppingListItem', (done) => {
        const service: ShoppingListService = TestBed.get(ShoppingListService);
        const httpTestingController: HttpTestingController = TestBed.get(HttpTestingController);

        const shoppingListItem: ShoppingListItem = {
            name: 'Nudeln',
            selected: false,
            links: [
                { rel: 'edit', href: '/editUrl' }
            ]
        };

        service.editShoppingListItem(shoppingListItem)
            .subscribe((shoppingList: ShoppingList) => {
                expect(shoppingList).toEqual(expectedShoppingList);
                done();
            });

        const request = httpTestingController.expectOne('/editUrl');
        request.flush(expectedShoppingList);

        httpTestingController.verify();
    });
});

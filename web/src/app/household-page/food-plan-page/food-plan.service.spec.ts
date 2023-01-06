import { TestBed } from '@angular/core/testing';
import { FoodPlanService } from './food-plan.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FoodPlan } from './food-plan';
import { Recipe } from '../cookbook-page/recipe/recipe';
import { Meal } from './meal/meal';

describe('FoodPlanService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ]
    }));

    it('should be created', () => {
        const service: FoodPlanService = TestBed.inject(FoodPlanService);
        expect(service).toBeTruthy();
    });

    it('should save meal', () => {
        const service: FoodPlanService = TestBed.inject(FoodPlanService);
        const httpTestingController: HttpTestingController = TestBed.inject(HttpTestingController);

        const expectedFoodPlan: FoodPlan = {
            meals: {
                monday: {
                    name: ''
                },
                tuesday: {
                    name: ''
                },
                wednesday: {
                    name: ''
                },
                thursday: {
                    name: ''
                },
                friday: {
                    name: ''
                },
                saturday: {
                    name: ''
                },
                sunday: {
                    name: 'myRecipe',
                    links: [{
                        rel: 'self',
                        href: 'http://host/api/foodPlans/3/meals/7'
                    }, {
                        rel: 'recipe',
                        href: 'http://host/api/cookbooks/2/recipes/17'
                    }]
                }
            }
        };

        const recipe: Recipe = {
            name: 'myRecipe',
            links: [{
                rel: 'self',
                href: 'http://host/api/cookbooks/2/recipes/17'
            }]
        };
        const meal: Meal = {
            name: 'myRecipe',
            links: [{
                rel: 'self',
                href: 'http://host/api/foodPlans/3/meals/7'
            }]
        };

        service.saveMeal(meal, recipe).subscribe(foodPlan => {
            expect(foodPlan).toBe(expectedFoodPlan);
        });


        const req = httpTestingController.expectOne('http://host/api/foodPlans/3/meals/7');
        expect(req.request.body.cookbookId).toBe(2);
        expect(req.request.body.recipeId).toBe(17);
        req.flush(expectedFoodPlan);
    });
});

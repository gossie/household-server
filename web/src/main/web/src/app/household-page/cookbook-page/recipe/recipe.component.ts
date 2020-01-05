import { Component, EventEmitter, Input, OnDestroy, OnInit, Output, ElementRef, ViewChild } from '@angular/core';
import { Recipe } from './recipe';
import { CookbookService } from '../cookbook.service';
import { Cookbook } from '../cookbook';
import { Subscription } from 'rxjs/index';
import { DeleteHintService} from '../../delete-hint.service';
import { CookbookAction } from '../cookbook-action.enum';
import { CookbookEvent } from '../cookbook-event';
import { filter } from 'rxjs/operators';

@Component({
    selector: 'app-recipe',
    templateUrl: './recipe.component.html',
    styleUrls: ['./recipe.component.sass']
})
export class RecipeComponent implements OnInit, OnDestroy {

    @Input()
    public recipe: Recipe;
    @Output()
    public cookbookEmitter: EventEmitter<Cookbook> = new EventEmitter();
    @Output()
    public recipeEmitter: EventEmitter<CookbookEvent> = new EventEmitter();

    @ViewChild('recipeCard', {static: false})
    private element: ElementRef;

    private subscriptions: Array<Subscription> = [];
    private expanded = false;

    constructor(private cookbookService: CookbookService,
                private deleteHintService: DeleteHintService) { }

    public ngOnInit() {
        this.observeUndo();
        this.observeRecipe();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeUndo(): void {
        this.subscriptions.push(this.deleteHintService.onUndo()
            .subscribe(() => this.recipe.hidden = false));
    }

    private observeRecipe(): void {
        this.subscriptions.push(this.cookbookService.observeRecipe()
            .pipe(
                filter((referencedRecipe: Recipe) => referencedRecipe.name === this.recipe.name)
            )
            .subscribe((referencedRecipe: Recipe) => {
                this.recipe = referencedRecipe;
                this.expanded = true;
                window.setTimeout(() => this.element.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' }), 50);
            }));
    }

    public isExpanded(): boolean {
        return this.expanded;
    }

    public toggleRecipe(): void {
        if (!this.expanded) {
            this.cookbookService.determineRecipe(this.recipe)
                .subscribe((recipe: Recipe) => {
                    this.recipe = recipe;
                    this.expanded = !this.expanded;
                });
        } else {
            this.expanded = !this.expanded;
        }
    }

    public editRecipe(): void {
        this.recipeEmitter.emit({
            action: CookbookAction.Edit,
            recipe: this.recipe
        });
    }

    public buyRecipe(): void {
        this.recipeEmitter.emit({
            action: CookbookAction.Buy,
            recipe: this.recipe
        });
    }

    public deleteRecipe(): void {
        this.recipe.hidden = true;
        this.cookbookService.deleteRecipe(this.recipe)
            .subscribe((cookbook: Cookbook) => this.cookbookEmitter.emit(cookbook));
    }

}

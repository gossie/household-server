(window.webpackJsonp=window.webpackJsonp||[]).push([[7],{HAC3:function(l,n,u){"use strict";u.r(n);var e=u("8Y7J");class i{}var t=u("pMnS"),o=u("GY0A"),s=u("XJZM");class r{transform(l){return l>0?l.toString():""}}class c{constructor(){}ngOnInit(){}}var a=e.nb({encapsulation:0,styles:[[""]],data:{}});function b(l){return e.Kb(0,[e.Cb(0,r,[]),(l()(),e.pb(1,0,null,null,7,"div",[["class","columns is-mobile"]],null,null,null,null,null)),(l()(),e.pb(2,0,null,null,2,"div",[["class","column is-one-quarter"]],null,null,null,null,null)),(l()(),e.Ib(3,null,["",""])),e.Eb(4,1),(l()(),e.pb(5,0,null,null,1,"div",[["class","column is-one-quarter"]],null,null,null,null,null)),(l()(),e.Ib(6,null,["",""])),(l()(),e.pb(7,0,null,null,1,"div",[["class","column is-half"]],null,null,null,null,null)),(l()(),e.Ib(8,null,["",""]))],null,function(l,n){var u=n.component,i=e.Jb(n,3,0,l(n,4,0,e.Ab(n,0),u.ingredient.amount));l(n,3,0,i),l(n,6,0,u.ingredient.unit),l(n,8,0,u.ingredient.name)})}var d=u("SVse"),p=u("3kpS"),g=u("yubq"),m=function(l){return l[l.Buy=0]="Buy",l[l.Edit=1]="Edit",l}({}),h=u("pLZG");class f{constructor(l,n){this.cookbookService=l,this.deleteHintService=n,this.cookbookEmitter=new e.m,this.recipeEmitter=new e.m,this.subscriptions=[],this.expanded=!1}ngOnInit(){this.observeUndo(),this.observeRecipe()}ngOnDestroy(){this.subscriptions.forEach(l=>l.unsubscribe())}observeUndo(){this.subscriptions.push(this.deleteHintService.onUndo().subscribe(()=>this.recipe.hidden=!1))}observeRecipe(){this.subscriptions.push(this.cookbookService.observeRecipe().pipe(Object(h.a)(l=>l.name===this.recipe.name)).subscribe(l=>{this.recipe=l,this.expanded=!0,window.setTimeout(()=>this.element.nativeElement.scrollIntoView({behavior:"smooth",block:"start"}),50)}))}isExpanded(){return this.expanded}toggleRecipe(){this.expanded?this.expanded=!this.expanded:this.cookbookService.determineRecipe(this.recipe).subscribe(l=>{this.recipe=l,this.expanded=!this.expanded})}editRecipe(){this.recipeEmitter.emit({action:m.Edit,recipe:this.recipe})}buyRecipe(){this.recipeEmitter.emit({action:m.Buy,recipe:this.recipe})}deleteRecipe(){this.recipe.hidden=!0,this.cookbookService.deleteRecipe(this.recipe).subscribe(l=>this.cookbookEmitter.emit(l))}}var v=e.nb({encapsulation:0,styles:[[".card-header-title[_ngcontent-%COMP%]{cursor:pointer}"]],data:{}});function k(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"span",[["class","icon"]],null,null,null,null,null)),(l()(),e.pb(1,0,null,null,0,"i",[["aria-hidden","true"],["class","fas fa-angle-left"]],null,null,null,null,null))],null,null)}function A(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"span",[["class","icon"]],null,null,null,null,null)),(l()(),e.pb(1,0,null,null,0,"i",[["aria-hidden","true"],["class","fas fa-angle-down"]],null,null,null,null,null))],null,null)}function I(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"a",[["target","_blank"]],[[8,"href",4]],null,null,null,null)),(l()(),e.Ib(-1,null,["Zum Rezept"]))],null,function(l,n){l(n,0,0,n.component.recipe.url)})}function C(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"app-ingredient",[],null,null,null,b,a)),e.ob(1,114688,null,0,c,[],{ingredient:[0,"ingredient"]},null)],function(l,n){l(n,1,0,n.context.$implicit)},null)}function E(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,6,"div",[["class","card-content"]],null,null,null,null,null)),(l()(),e.pb(1,0,null,null,5,"div",[["class","content"]],null,null,null,null,null)),(l()(),e.pb(2,0,null,null,2,"p",[],null,null,null,null,null)),(l()(),e.eb(16777216,null,null,1,null,I)),e.ob(4,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null),(l()(),e.eb(16777216,null,null,1,null,C)),e.ob(6,278528,null,0,d.i,[e.M,e.J,e.q],{ngForOf:[0,"ngForOf"]},null)],function(l,n){var u=n.component;l(n,4,0,u.recipe.url),l(n,6,0,u.recipe.ingredients)},null)}function F(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,6,"footer",[["class","card-footer"]],null,null,null,null,null)),(l()(),e.pb(1,0,null,null,1,"a",[["class","card-footer-item"],["id","edit-button"]],null,[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.editRecipe()&&e),e},null,null)),(l()(),e.Ib(-1,null,["Editieren"])),(l()(),e.pb(3,0,null,null,1,"a",[["class","card-footer-item"],["id","buy-button"]],null,[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.buyRecipe()&&e),e},null,null)),(l()(),e.Ib(-1,null,["Kaufen"])),(l()(),e.pb(5,0,null,null,1,"a",[["class","card-footer-item"],["id","delete-button"]],null,[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.deleteRecipe()&&e),e},null,null)),(l()(),e.Ib(-1,null,["L\xf6schen"]))],null,null)}function y(l){return e.Kb(0,[(l()(),e.pb(0,0,[[1,0],["recipeCard",1]],null,12,"div",[["class","card"]],null,null,null,null,null)),(l()(),e.pb(1,0,null,null,7,"header",[["class","card-header"]],null,null,null,null,null)),(l()(),e.pb(2,0,null,null,1,"p",[["class","card-header-title"]],null,[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.toggleRecipe()&&e),e},null,null)),(l()(),e.Ib(3,null,[" "," "])),(l()(),e.pb(4,0,null,null,4,"a",[["aria-label","more options"],["class","card-header-icon"]],null,[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.toggleRecipe()&&e),e},null,null)),(l()(),e.eb(16777216,null,null,1,null,k)),e.ob(6,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null),(l()(),e.eb(16777216,null,null,1,null,A)),e.ob(8,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null),(l()(),e.eb(16777216,null,null,1,null,E)),e.ob(10,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null),(l()(),e.eb(16777216,null,null,1,null,F)),e.ob(12,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null)],function(l,n){var u=n.component;l(n,6,0,!u.isExpanded()),l(n,8,0,u.isExpanded()),l(n,10,0,u.isExpanded()),l(n,12,0,u.isExpanded())},function(l,n){l(n,3,0,n.component.recipe.name)})}function S(l){return e.Kb(0,[e.Gb(671088640,1,{element:0}),(l()(),e.eb(16777216,null,null,1,null,y)),e.ob(2,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null)],function(l,n){l(n,2,0,!n.component.recipe.hidden)},null)}var R=u("s7LF");class z{constructor(l){this.formBuilder=l,this.editMode=!1,this.initialAmount="",this.initialUnit="",this.initialName="",this.ingredientEmitter=new e.m}ngOnInit(){this.ingredientForm=this.formBuilder.group({amount:[this.initialAmount],unit:[this.initialUnit],name:[this.initialName,R.m.required]})}handleIngredient(){this.ingredientEmitter.emit({amount:parseFloat(this.ingredientForm.controls.amount.value),unit:this.ingredientForm.controls.unit.value,name:this.ingredientForm.controls.name.value}),this.ingredientForm.controls.amount.reset(),this.ingredientForm.controls.unit.reset(),this.ingredientForm.controls.name.reset()}}var M=e.nb({encapsulation:0,styles:[[""]],data:{}});function x(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"span",[["class","icon has-text-grey-light"]],null,null,null,null,null)),(l()(),e.pb(1,0,null,null,0,"i",[["class","fas fa-plus"]],null,null,null,null,null))],null,null)}function j(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"span",[["class","icon"],["id","add-ingredient-button"]],null,[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.handleIngredient()&&e),e},null,null)),(l()(),e.pb(1,0,null,null,0,"i",[["class","fas fa-plus"]],null,null,null,null,null))],null,null)}function K(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"span",[],null,[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.handleIngredient()&&e),e},null,null)),(l()(),e.pb(1,0,null,null,0,"i",[["class","fas fa-save"]],null,null,null,null,null))],null,null)}function T(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,37,"form",[["novalidate",""]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"submit"],[null,"reset"]],function(l,n,u){var i=!0;return"submit"===n&&(i=!1!==e.Ab(l,2).onSubmit(u)&&i),"reset"===n&&(i=!1!==e.Ab(l,2).onReset()&&i),i},null,null)),e.ob(1,16384,null,0,R.q,[],null,null),e.ob(2,540672,null,0,R.g,[[8,null],[8,null]],{form:[0,"form"]},null),e.Fb(2048,null,R.b,null,[R.g]),e.ob(4,16384,null,0,R.k,[[4,R.b]],null,null),(l()(),e.pb(5,0,null,null,32,"div",[["class","columns is-mobile"]],null,null,null,null,null)),(l()(),e.pb(6,0,null,null,7,"div",[["class","column is-2"]],null,null,null,null,null)),(l()(),e.pb(7,0,null,null,6,"div",[["class","control"]],null,null,null,null,null)),(l()(),e.pb(8,0,null,null,5,"input",[["class","input"],["formControlName","amount"],["id","amount-field"],["placeholder","Anzahl"],["type","text"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"input"],[null,"blur"],[null,"compositionstart"],[null,"compositionend"]],function(l,n,u){var i=!0;return"input"===n&&(i=!1!==e.Ab(l,9)._handleInput(u.target.value)&&i),"blur"===n&&(i=!1!==e.Ab(l,9).onTouched()&&i),"compositionstart"===n&&(i=!1!==e.Ab(l,9)._compositionStart()&&i),"compositionend"===n&&(i=!1!==e.Ab(l,9)._compositionEnd(u.target.value)&&i),i},null,null)),e.ob(9,16384,null,0,R.c,[e.B,e.k,[2,R.a]],null,null),e.Fb(1024,null,R.h,function(l){return[l]},[R.c]),e.ob(11,671744,null,0,R.f,[[3,R.b],[8,null],[8,null],[6,R.h],[2,R.p]],{name:[0,"name"]},null),e.Fb(2048,null,R.i,null,[R.f]),e.ob(13,16384,null,0,R.j,[[4,R.i]],null,null),(l()(),e.pb(14,0,null,null,7,"div",[["class","column is-2"]],null,null,null,null,null)),(l()(),e.pb(15,0,null,null,6,"div",[["class","control"]],null,null,null,null,null)),(l()(),e.pb(16,0,null,null,5,"input",[["class","input"],["formControlName","unit"],["id","unit-field"],["placeholder","Einheit"],["type","text"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"input"],[null,"blur"],[null,"compositionstart"],[null,"compositionend"]],function(l,n,u){var i=!0;return"input"===n&&(i=!1!==e.Ab(l,17)._handleInput(u.target.value)&&i),"blur"===n&&(i=!1!==e.Ab(l,17).onTouched()&&i),"compositionstart"===n&&(i=!1!==e.Ab(l,17)._compositionStart()&&i),"compositionend"===n&&(i=!1!==e.Ab(l,17)._compositionEnd(u.target.value)&&i),i},null,null)),e.ob(17,16384,null,0,R.c,[e.B,e.k,[2,R.a]],null,null),e.Fb(1024,null,R.h,function(l){return[l]},[R.c]),e.ob(19,671744,null,0,R.f,[[3,R.b],[8,null],[8,null],[6,R.h],[2,R.p]],{name:[0,"name"]},null),e.Fb(2048,null,R.i,null,[R.f]),e.ob(21,16384,null,0,R.j,[[4,R.i]],null,null),(l()(),e.pb(22,0,null,null,7,"div",[["class","column is-6"]],null,null,null,null,null)),(l()(),e.pb(23,0,null,null,6,"div",[["class","control"]],null,null,null,null,null)),(l()(),e.pb(24,0,null,null,5,"input",[["class","input"],["formControlName","name"],["id","name-field"],["placeholder","Zutat"],["type","text"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"input"],[null,"blur"],[null,"compositionstart"],[null,"compositionend"]],function(l,n,u){var i=!0;return"input"===n&&(i=!1!==e.Ab(l,25)._handleInput(u.target.value)&&i),"blur"===n&&(i=!1!==e.Ab(l,25).onTouched()&&i),"compositionstart"===n&&(i=!1!==e.Ab(l,25)._compositionStart()&&i),"compositionend"===n&&(i=!1!==e.Ab(l,25)._compositionEnd(u.target.value)&&i),i},null,null)),e.ob(25,16384,null,0,R.c,[e.B,e.k,[2,R.a]],null,null),e.Fb(1024,null,R.h,function(l){return[l]},[R.c]),e.ob(27,671744,null,0,R.f,[[3,R.b],[8,null],[8,null],[6,R.h],[2,R.p]],{name:[0,"name"]},null),e.Fb(2048,null,R.i,null,[R.f]),e.ob(29,16384,null,0,R.j,[[4,R.i]],null,null),(l()(),e.pb(30,0,null,null,7,"div",[["class","column is-2"]],null,null,null,null,null)),(l()(),e.pb(31,0,null,null,6,"div",[["class","control"]],null,null,null,null,null)),(l()(),e.eb(16777216,null,null,1,null,x)),e.ob(33,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null),(l()(),e.eb(16777216,null,null,1,null,j)),e.ob(35,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null),(l()(),e.eb(16777216,null,null,1,null,K)),e.ob(37,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null)],function(l,n){var u=n.component;l(n,2,0,u.ingredientForm),l(n,11,0,"amount"),l(n,19,0,"unit"),l(n,27,0,"name"),l(n,33,0,!u.ingredientForm.valid),l(n,35,0,u.ingredientForm.valid&&!u.editMode),l(n,37,0,u.ingredientForm.valid&&u.editMode)},function(l,n){l(n,0,0,e.Ab(n,4).ngClassUntouched,e.Ab(n,4).ngClassTouched,e.Ab(n,4).ngClassPristine,e.Ab(n,4).ngClassDirty,e.Ab(n,4).ngClassValid,e.Ab(n,4).ngClassInvalid,e.Ab(n,4).ngClassPending),l(n,8,0,e.Ab(n,13).ngClassUntouched,e.Ab(n,13).ngClassTouched,e.Ab(n,13).ngClassPristine,e.Ab(n,13).ngClassDirty,e.Ab(n,13).ngClassValid,e.Ab(n,13).ngClassInvalid,e.Ab(n,13).ngClassPending),l(n,16,0,e.Ab(n,21).ngClassUntouched,e.Ab(n,21).ngClassTouched,e.Ab(n,21).ngClassPristine,e.Ab(n,21).ngClassDirty,e.Ab(n,21).ngClassValid,e.Ab(n,21).ngClassInvalid,e.Ab(n,21).ngClassPending),l(n,24,0,e.Ab(n,29).ngClassUntouched,e.Ab(n,29).ngClassTouched,e.Ab(n,29).ngClassPristine,e.Ab(n,29).ngClassDirty,e.Ab(n,29).ngClassValid,e.Ab(n,29).ngClassInvalid,e.Ab(n,29).ngClassPending)})}class U{constructor(){this.ingredientDeletionEmitter=new e.m,this.editMode=!1}editIngredient(){this.editMode=!0}saveIngredient(l){this.ingredient.amount=l.amount,this.ingredient.unit=l.unit,this.ingredient.name=l.name,this.editMode=!1}deleteIngredient(){this.ingredientDeletionEmitter.emit(this.ingredient)}}var J=e.nb({encapsulation:0,styles:[[""]],data:{}});function D(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,13,"div",[["class","columns is-mobile"]],null,null,null,null,null)),(l()(),e.pb(1,0,null,null,2,"div",[["class","column is-2"]],null,null,null,null,null)),(l()(),e.Ib(2,null,["",""])),e.Eb(3,1),(l()(),e.pb(4,0,null,null,1,"div",[["class","column is-2"]],null,null,null,null,null)),(l()(),e.Ib(5,null,["",""])),(l()(),e.pb(6,0,null,null,1,"div",[["class","column is-4"]],null,null,null,null,null)),(l()(),e.Ib(7,null,["",""])),(l()(),e.pb(8,0,null,null,2,"div",[["class","column is-2"]],null,null,null,null,null)),(l()(),e.pb(9,0,null,null,1,"span",[["class","icon"]],[[8,"id",0]],[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.editIngredient()&&e),e},null,null)),(l()(),e.pb(10,0,null,null,0,"i",[["class","fas fa-pen"]],null,null,null,null,null)),(l()(),e.pb(11,0,null,null,2,"div",[["class","column is-2"]],null,null,null,null,null)),(l()(),e.pb(12,0,null,null,1,"span",[["class","icon"]],[[8,"id",0]],[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.deleteIngredient()&&e),e},null,null)),(l()(),e.pb(13,0,null,null,0,"i",[["class","fas fa-trash"]],null,null,null,null,null))],null,function(l,n){var u=n.component,i=e.Jb(n,2,0,l(n,3,0,e.Ab(n.parent,0),u.ingredient.amount));l(n,2,0,i),l(n,5,0,u.ingredient.unit),l(n,7,0,u.ingredient.name),l(n,9,0,e.tb(1,"edit-ingredient-button-",u.index,"")),l(n,12,0,e.tb(1,"delete-ingredient-button-",u.index,""))})}function O(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"app-ingredient-form",[],null,[[null,"ingredientEmitter"]],function(l,n,u){var e=!0;return"ingredientEmitter"===n&&(e=!1!==l.component.saveIngredient(u)&&e),e},T,M)),e.ob(1,114688,null,0,z,[R.d],{editMode:[0,"editMode"],initialAmount:[1,"initialAmount"],initialUnit:[2,"initialUnit"],initialName:[3,"initialName"]},{ingredientEmitter:"ingredientEmitter"})],function(l,n){var u=n.component;l(n,1,0,u.editMode,u.ingredient.amount,u.ingredient.unit,u.ingredient.name)},null)}function N(l){return e.Kb(0,[e.Cb(0,r,[]),(l()(),e.eb(16777216,null,null,1,null,D)),e.ob(2,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null),(l()(),e.eb(16777216,null,null,1,null,O)),e.ob(4,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null)],function(l,n){var u=n.component;l(n,2,0,!u.editMode),l(n,4,0,u.editMode)},null)}var B=u("nlbl");class L{constructor(l,n){this.formBuilder=l,this.cookbookService=n,this.cookbookEmitter=new e.m,this.open=!1,this.loading=!1,this.ingredients=[]}ngOnInit(){this.createForms()}ngOnChanges(){B.a.isObject(this.recipe)&&(this.recipeForm.controls.recipeName.setValue(this.recipe.name),this.recipeForm.controls.recipeUrl.setValue(this.recipe.url),this.ingredients=this.recipe.ingredients,this.open=!0)}createForms(){this.recipeForm=this.formBuilder.group({recipeName:["",R.m.required],recipeUrl:[""]})}openDialog(){this.open=!0}closeDialog(){this.resetFields(),this.open=!1,this.cookbookEmitter.emit(this.cookbook)}addIngredient(l){this.ingredients.push(l)}deleteIngredient(l){this.ingredients.splice(this.ingredients.indexOf(l),1)}saveRecipe(){this.loading=!0,B.a.isObject(this.recipe)?this.editRecipe():this.createRecipe()}editRecipe(){this.recipe.name=this.recipeForm.controls.recipeName.value,this.recipe.url=this.recipeForm.controls.recipeUrl.value,this.recipe.ingredients=this.ingredients,this.cookbookService.editRecipe(this.recipe).subscribe(l=>{this.resetFields(),this.loading=!1,this.open=!1,this.cookbookEmitter.emit(l)})}createRecipe(){this.cookbookService.createRecipe(this.cookbook,{name:this.recipeForm.controls.recipeName.value,url:this.recipeForm.controls.recipeUrl.value,ingredients:this.ingredients}).subscribe(l=>{this.resetFields(),this.loading=!1,this.open=!1,this.cookbookEmitter.emit(l)})}resetFields(){this.ingredients=[],this.recipeForm.controls.recipeName.reset(),this.recipeForm.controls.recipeUrl.reset()}}var w=e.nb({encapsulation:0,styles:[[""]],data:{}});function _(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"app-editable-ingredient",[],null,[[null,"ingredientDeletionEmitter"]],function(l,n,u){var e=!0;return"ingredientDeletionEmitter"===n&&(e=!1!==l.component.deleteIngredient(u)&&e),e},N,J)),e.ob(1,49152,null,0,U,[],{ingredient:[0,"ingredient"],index:[1,"index"]},{ingredientDeletionEmitter:"ingredientDeletionEmitter"})],function(l,n){l(n,1,0,n.context.$implicit,n.context.index)},null)}function q(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"a",[["class","button is-primary"],["id","add-recipe-button"]],null,[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.openDialog()&&e),e},null,null)),(l()(),e.Ib(-1,null,["Neues Rezept"])),(l()(),e.pb(2,0,null,null,41,"div",[["class","modal"]],null,null,null,null,null)),e.Fb(512,null,d.q,d.r,[e.q,e.r,e.k,e.B]),e.ob(4,278528,null,0,d.h,[d.q],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),e.Db(5,{"is-active":0}),(l()(),e.pb(6,0,null,null,0,"div",[["class","modal-background"]],null,null,null,null,null)),(l()(),e.pb(7,0,null,null,35,"div",[["class","modal-content"]],null,null,null,null,null)),(l()(),e.pb(8,0,null,null,34,"div",[["class","box"]],null,null,null,null,null)),(l()(),e.pb(9,0,null,null,20,"form",[["novalidate",""]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"submit"],[null,"reset"]],function(l,n,u){var i=!0;return"submit"===n&&(i=!1!==e.Ab(l,11).onSubmit(u)&&i),"reset"===n&&(i=!1!==e.Ab(l,11).onReset()&&i),i},null,null)),e.ob(10,16384,null,0,R.q,[],null,null),e.ob(11,540672,null,0,R.g,[[8,null],[8,null]],{form:[0,"form"]},null),e.Fb(2048,null,R.b,null,[R.g]),e.ob(13,16384,null,0,R.k,[[4,R.b]],null,null),(l()(),e.pb(14,0,null,null,7,"div",[["class","columns is-mobile"]],null,null,null,null,null)),(l()(),e.pb(15,0,null,null,6,"div",[["class","column"]],null,null,null,null,null)),(l()(),e.pb(16,0,null,null,5,"input",[["class","input"],["formControlName","recipeName"],["id","recipe-name-field"],["placeholder","Rezept"],["type","text"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"input"],[null,"blur"],[null,"compositionstart"],[null,"compositionend"]],function(l,n,u){var i=!0;return"input"===n&&(i=!1!==e.Ab(l,17)._handleInput(u.target.value)&&i),"blur"===n&&(i=!1!==e.Ab(l,17).onTouched()&&i),"compositionstart"===n&&(i=!1!==e.Ab(l,17)._compositionStart()&&i),"compositionend"===n&&(i=!1!==e.Ab(l,17)._compositionEnd(u.target.value)&&i),i},null,null)),e.ob(17,16384,null,0,R.c,[e.B,e.k,[2,R.a]],null,null),e.Fb(1024,null,R.h,function(l){return[l]},[R.c]),e.ob(19,671744,null,0,R.f,[[3,R.b],[8,null],[8,null],[6,R.h],[2,R.p]],{name:[0,"name"]},null),e.Fb(2048,null,R.i,null,[R.f]),e.ob(21,16384,null,0,R.j,[[4,R.i]],null,null),(l()(),e.pb(22,0,null,null,7,"div",[["class","columns is-mobile"]],null,null,null,null,null)),(l()(),e.pb(23,0,null,null,6,"div",[["class","column"]],null,null,null,null,null)),(l()(),e.pb(24,0,null,null,5,"input",[["class","input"],["formControlName","recipeUrl"],["id","recipe-url-field"],["placeholder","URL zum Rezept"],["type","text"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"input"],[null,"blur"],[null,"compositionstart"],[null,"compositionend"]],function(l,n,u){var i=!0;return"input"===n&&(i=!1!==e.Ab(l,25)._handleInput(u.target.value)&&i),"blur"===n&&(i=!1!==e.Ab(l,25).onTouched()&&i),"compositionstart"===n&&(i=!1!==e.Ab(l,25)._compositionStart()&&i),"compositionend"===n&&(i=!1!==e.Ab(l,25)._compositionEnd(u.target.value)&&i),i},null,null)),e.ob(25,16384,null,0,R.c,[e.B,e.k,[2,R.a]],null,null),e.Fb(1024,null,R.h,function(l){return[l]},[R.c]),e.ob(27,671744,null,0,R.f,[[3,R.b],[8,null],[8,null],[6,R.h],[2,R.p]],{name:[0,"name"]},null),e.Fb(2048,null,R.i,null,[R.f]),e.ob(29,16384,null,0,R.j,[[4,R.i]],null,null),(l()(),e.pb(30,0,null,null,1,"h5",[["class","title is-5"]],null,null,null,null,null)),(l()(),e.Ib(-1,null,["Zutaten"])),(l()(),e.eb(16777216,null,null,1,null,_)),e.ob(33,278528,null,0,d.i,[e.M,e.J,e.q],{ngForOf:[0,"ngForOf"]},null),(l()(),e.pb(34,0,null,null,1,"app-ingredient-form",[],null,[[null,"ingredientEmitter"]],function(l,n,u){var e=!0;return"ingredientEmitter"===n&&(e=!1!==l.component.addIngredient(u)&&e),e},T,M)),e.ob(35,114688,null,0,z,[R.d],null,{ingredientEmitter:"ingredientEmitter"}),(l()(),e.pb(36,0,null,null,6,"div",[["class","columns is-mobile"]],null,null,null,null,null)),(l()(),e.pb(37,0,null,null,5,"div",[["class","column"]],null,null,null,null,null)),(l()(),e.pb(38,0,null,null,4,"button",[["class","button is-primary"],["id","save-recipe-button"]],[[8,"disabled",0]],[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.saveRecipe()&&e),e},null,null)),e.Fb(512,null,d.q,d.r,[e.q,e.r,e.k,e.B]),e.ob(40,278528,null,0,d.h,[d.q],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),e.Db(41,{"is-loading":0}),(l()(),e.Ib(-1,null,["Speichern"])),(l()(),e.pb(43,0,null,null,0,"button",[["aria-label","close"],["class","modal-close is-large"],["id","close-button"]],null,[[null,"click"]],function(l,n,u){var e=!0;return"click"===n&&(e=!1!==l.component.closeDialog()&&e),e},null,null))],function(l,n){var u=n.component,e=l(n,5,0,u.open);l(n,4,0,"modal",e),l(n,11,0,u.recipeForm),l(n,19,0,"recipeName"),l(n,27,0,"recipeUrl"),l(n,33,0,u.ingredients),l(n,35,0);var i=l(n,41,0,u.loading);l(n,40,0,"button is-primary",i)},function(l,n){var u=n.component;l(n,9,0,e.Ab(n,13).ngClassUntouched,e.Ab(n,13).ngClassTouched,e.Ab(n,13).ngClassPristine,e.Ab(n,13).ngClassDirty,e.Ab(n,13).ngClassValid,e.Ab(n,13).ngClassInvalid,e.Ab(n,13).ngClassPending),l(n,16,0,e.Ab(n,21).ngClassUntouched,e.Ab(n,21).ngClassTouched,e.Ab(n,21).ngClassPristine,e.Ab(n,21).ngClassDirty,e.Ab(n,21).ngClassValid,e.Ab(n,21).ngClassInvalid,e.Ab(n,21).ngClassPending),l(n,24,0,e.Ab(n,29).ngClassUntouched,e.Ab(n,29).ngClassTouched,e.Ab(n,29).ngClassPristine,e.Ab(n,29).ngClassDirty,e.Ab(n,29).ngClassValid,e.Ab(n,29).ngClassInvalid,e.Ab(n,29).ngClassPending),l(n,38,0,!u.recipeForm.valid)})}var P=u("wjSy"),V=u("7csi");class H{constructor(l,n,u){this.householdService=l,this.cookbookService=n,this.shoppingListService=u,this.subscriptions=[]}ngOnInit(){this.observeHousehold(),this.observeShoppingList()}ngOnDestroy(){this.subscriptions.forEach(l=>l.unsubscribe())}observeHousehold(){this.subscriptions.push(this.householdService.observeHousehold().subscribe(l=>{this.cookbookService.determineCookbook(l).subscribe(this.handleCookbook.bind(this)),this.shoppingListService.determineShoppingList(l).subscribe(l=>this.shoppingList=l)}))}observeShoppingList(){this.subscriptions.push(this.shoppingListService.observeShoppingList().subscribe(l=>this.shoppingList=l))}onIngredientSelection(l){const n=[];l.forEach(l=>n.push(l)),this.shoppingListService.addShoppingListItems(this.shoppingList.shoppingListGroups[0],n).subscribe(()=>this.recipeToBuy=null)}handleCookbook(l){l.recipes.sort((l,n)=>l.name.toLowerCase().localeCompare(n.name.toLowerCase())),this.cookbook=l,this.recipeToEdit=void 0,this.recipeToBuy=void 0}handleRecipe(l){l.action===m.Edit?this.recipeToEdit=l.recipe:this.recipeToBuy=l.recipe}}var X=e.nb({encapsulation:0,styles:[[""]],data:{}});function Z(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"app-select-recipe",[],null,[[null,"ingredientsEmitter"]],function(l,n,u){var e=!0;return"ingredientsEmitter"===n&&(e=!1!==l.component.onIngredientSelection(u)&&e),e},o.b,o.a)),e.ob(1,114688,null,0,s.a,[],{recipe:[0,"recipe"]},{ingredientsEmitter:"ingredientsEmitter"})],function(l,n){l(n,1,0,n.component.recipeToBuy)},null)}function G(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"app-recipe",[],null,[[null,"cookbookEmitter"],[null,"recipeEmitter"]],function(l,n,u){var e=!0,i=l.component;return"cookbookEmitter"===n&&(e=!1!==i.handleCookbook(u)&&e),"recipeEmitter"===n&&(e=!1!==i.handleRecipe(u)&&e),e},S,v)),e.ob(1,245760,null,0,f,[p.a,g.a],{recipe:[0,"recipe"]},{cookbookEmitter:"cookbookEmitter",recipeEmitter:"recipeEmitter"})],function(l,n){l(n,1,0,n.context.$implicit)},null)}function $(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,10,"div",[],null,null,null,null,null)),(l()(),e.eb(16777216,null,null,1,null,Z)),e.ob(2,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null),(l()(),e.pb(3,0,null,null,3,"div",[["class","columns is-mobile"]],null,null,null,null,null)),(l()(),e.pb(4,0,null,null,2,"div",[["class","column"]],null,null,null,null,null)),(l()(),e.pb(5,0,null,null,1,"app-add-recipe",[],null,[[null,"cookbookEmitter"]],function(l,n,u){var e=!0;return"cookbookEmitter"===n&&(e=!1!==l.component.handleCookbook(u)&&e),e},q,w)),e.ob(6,638976,null,0,L,[R.d,p.a],{cookbook:[0,"cookbook"],recipe:[1,"recipe"]},{cookbookEmitter:"cookbookEmitter"}),(l()(),e.pb(7,0,null,null,3,"div",[["class","columns is-mobile"]],null,null,null,null,null)),(l()(),e.pb(8,0,null,null,2,"div",[["class","column"]],null,null,null,null,null)),(l()(),e.eb(16777216,null,null,1,null,G)),e.ob(10,278528,null,0,d.i,[e.M,e.J,e.q],{ngForOf:[0,"ngForOf"]},null)],function(l,n){var u=n.component;l(n,2,0,u.recipeToBuy),l(n,6,0,u.cookbook,u.recipeToEdit),l(n,10,0,u.cookbook.recipes)},null)}function Y(l){return e.Kb(0,[(l()(),e.eb(16777216,null,null,1,null,$)),e.ob(1,16384,null,0,d.j,[e.M,e.J],{ngIf:[0,"ngIf"]},null)],function(l,n){l(n,1,0,n.component.cookbook)},null)}function W(l){return e.Kb(0,[(l()(),e.pb(0,0,null,null,1,"app-cookbook-page",[],null,null,null,Y,X)),e.ob(1,245760,null,0,H,[P.a,p.a,V.a],null,null)],function(l,n){l(n,1,0)},null)}var Q=e.lb("app-cookbook-page",H,W,{},{},[]),ll=u("IheW"),nl=u("Ukb2"),ul=u("iInd");class el{}u.d(n,"CookbookModuleNgFactory",function(){return il});var il=e.mb(i,[],function(l){return e.yb([e.zb(512,e.j,e.X,[[8,[t.a,Q]],[3,e.j],e.v]),e.zb(4608,d.l,d.k,[e.s,[2,d.t]]),e.zb(4608,R.d,R.d,[]),e.zb(4608,R.o,R.o,[]),e.zb(4608,ll.i,ll.o,[d.c,e.z,ll.m]),e.zb(4608,ll.p,ll.p,[ll.i,ll.n]),e.zb(5120,ll.a,function(l){return[l]},[ll.p]),e.zb(4608,ll.l,ll.l,[]),e.zb(6144,ll.j,null,[ll.l]),e.zb(4608,ll.h,ll.h,[ll.j]),e.zb(6144,ll.b,null,[ll.h]),e.zb(4608,ll.g,ll.k,[ll.b,e.p]),e.zb(4608,ll.c,ll.c,[ll.g]),e.zb(1073742336,d.b,d.b,[]),e.zb(1073742336,R.n,R.n,[]),e.zb(1073742336,R.l,R.l,[]),e.zb(1073742336,ll.e,ll.e,[]),e.zb(1073742336,ll.d,ll.d,[]),e.zb(1073742336,nl.a,nl.a,[]),e.zb(1073742336,ul.o,ul.o,[[2,ul.t],[2,ul.l]]),e.zb(1073742336,el,el,[]),e.zb(1073742336,i,i,[]),e.zb(256,ll.m,"XSRF-TOKEN",[]),e.zb(256,ll.n,"X-XSRF-TOKEN",[]),e.zb(1024,ul.j,function(){return[[{path:"",component:H}]]},[])])})}}]);
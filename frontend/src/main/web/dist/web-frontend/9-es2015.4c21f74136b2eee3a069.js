(window.webpackJsonp=window.webpackJsonp||[]).push([[9],{"4QK9":function(n,l,e){"use strict";e.r(l);var o=e("8Y7J");class t{}var i=e("pMnS"),a=e("GY0A"),r=e("XJZM"),s=e("s7LF"),u=e("SVse"),c=e("3kpS"),d=e("nlbl"),m=e("xdv0"),p=e("q+Bj"),b=e("IheW");let h=(()=>{class n extends p.a{constructor(n,l){super(),this.userService=n,this.httpClient=l}determineFoodPlan(n){const l=this.determineUrl(n,"foodPlan");return this.httpClient.get(l,{headers:{Accept:"application/vnd.household.v1+json"}})}saveMeal(n,l){const e=this.determineUrl(n,"self"),o={meal:n,cookbookId:this.determineCookbookId(l),recipeId:this.determineRecipeId(l)};return this.httpClient.put(e,o,{headers:{"Content-Type":"application/vnd.household.v1+json",Accept:"application/vnd.household.v1+json"}})}determineCookbookId(n){if(d.a.isObject(n)){const l=this.determineUrl(n,"self").split("/");return parseInt(l[l.length-3],10)}return null}determineRecipeId(n){if(d.a.isObject(n)){const l=this.determineUrl(n,"self").split("/");return parseInt(l[l.length-1],10)}return null}clearFoodPlan(n){const l=this.determineUrl(n,"clear");return this.httpClient.delete(l,{headers:{Accept:"application/vnd.household.v1+json"}})}}return n.ngInjectableDef=o.Ob({factory:function(){return new n(o.Pb(m.a),o.Pb(b.c))},token:n,providedIn:"root"}),n})();class f{constructor(n,l,e){this.foodPlanService=n,this.cookbookService=l,this.router=e,this.foodPlanEmitter=new o.m,this.recipeEmitter=new o.m,this.recipes=[],this.subscriptions=[]}ngOnInit(){this.createForm()}ngOnDestroy(){this.subscriptions.forEach(n=>n.unsubscribe())}createForm(){const n=new s.e(this.meal.name);n.valueChanges.subscribe(()=>this.searchForRecipes()),this.parentForm.addControl(this.controlName,n)}searchForRecipes(){let n="";null!==this.parentForm.controls[this.controlName].value&&(n=this.parentForm.controls[this.controlName].value.toLowerCase()),this.recipes=n.length>0?this.cookbook.recipes.filter(l=>this.recipeMatchesSearchString(l,n)):[]}recipeMatchesSearchString(n,l){return n.name.toLowerCase().indexOf(l.toLowerCase())>=0}selectRecipe(n){this.cookbookService.determineRecipe(n).subscribe(n=>{this.parentForm.controls[this.controlName].setValue(n.name),this.meal.name=n.name,this.recipes=[],this.recipeEmitter.emit({recipe:n,meal:this.meal})})}changeMealName(){window.setTimeout(()=>{this.meal.name=this.parentForm.controls[this.controlName].value,this.foodPlanService.saveMeal(this.meal,null).subscribe(n=>this.foodPlanEmitter.emit(n))},50)}unfocus(){setTimeout(()=>this.recipes=[],250)}hasConnectedRecipe(){return this.meal&&d.a.isObject(this.meal.links.find(n=>"recipe"===n.rel))}jumpToRecipe(){this.router.navigate(["/cookbook"]).then(()=>{const n=this.meal.links.find(n=>"recipe"===n.rel).href;this.cookbookService.determineRecipeByUrl(n)})}}var g=e("iInd"),v=o.nb({encapsulation:0,styles:[[""]],data:{}});function k(n){return o.Kb(0,[(n()(),o.pb(0,0,null,null,2,"div",[],null,null,null,null,null)),(n()(),o.pb(1,0,null,null,1,"a",[],null,[[null,"click"]],function(n,l,e){var o=!0;return"click"===l&&(o=!1!==n.component.selectRecipe(n.context.$implicit)&&o),o},null,null)),(n()(),o.Ib(2,null,["",""]))],null,function(n,l){n(l,2,0,l.context.$implicit.name)})}function P(n){return o.Kb(0,[(n()(),o.pb(0,0,null,null,1,"span",[["class","icon"]],null,[[null,"click"]],function(n,l,e){var o=!0;return"click"===l&&(o=!1!==n.component.jumpToRecipe()&&o),o},null,null)),(n()(),o.pb(1,0,null,null,0,"i",[["class","fas fa-utensil-spoon"]],null,null,null,null,null))],null,null)}function y(n){return o.Kb(0,[(n()(),o.pb(0,0,null,null,19,"div",[["class","columns is-mobile is-vcentered"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"submit"],[null,"reset"]],function(n,l,e){var t=!0;return"submit"===l&&(t=!1!==o.Ab(n,1).onSubmit(e)&&t),"reset"===l&&(t=!1!==o.Ab(n,1).onReset()&&t),t},null,null)),o.ob(1,540672,null,0,s.g,[[8,null],[8,null]],{form:[0,"form"]},null),o.Fb(2048,null,s.b,null,[s.g]),o.ob(3,16384,null,0,s.k,[[4,s.b]],null,null),(n()(),o.pb(4,0,null,null,12,"div",[["class","column is-8"]],null,null,null,null,null)),(n()(),o.pb(5,0,null,null,11,"div",[["class","field"]],null,null,null,null,null)),(n()(),o.pb(6,0,null,null,1,"label",[["class","label"]],[[8,"htmlFor",0]],null,null,null,null)),(n()(),o.Ib(7,null,["",""])),(n()(),o.pb(8,0,null,null,8,"div",[["class","control"]],null,null,null,null,null)),(n()(),o.pb(9,0,null,null,5,"input",[["autocomplete","off"],["autocorrect","off"],["class","input"],["type","text"]],[[8,"id",0],[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"change"],[null,"blur"],[null,"input"],[null,"compositionstart"],[null,"compositionend"]],function(n,l,e){var t=!0,i=n.component;return"input"===l&&(t=!1!==o.Ab(n,10)._handleInput(e.target.value)&&t),"blur"===l&&(t=!1!==o.Ab(n,10).onTouched()&&t),"compositionstart"===l&&(t=!1!==o.Ab(n,10)._compositionStart()&&t),"compositionend"===l&&(t=!1!==o.Ab(n,10)._compositionEnd(e.target.value)&&t),"change"===l&&(t=!1!==i.changeMealName()&&t),"blur"===l&&(t=!1!==i.unfocus()&&t),t},null,null)),o.ob(10,16384,null,0,s.c,[o.B,o.k,[2,s.a]],null,null),o.Fb(1024,null,s.h,function(n){return[n]},[s.c]),o.ob(12,671744,null,0,s.f,[[3,s.b],[8,null],[8,null],[6,s.h],[2,s.p]],{name:[0,"name"]},null),o.Fb(2048,null,s.i,null,[s.f]),o.ob(14,16384,null,0,s.j,[[4,s.i]],null,null),(n()(),o.eb(16777216,null,null,1,null,k)),o.ob(16,278528,null,0,u.i,[o.M,o.J,o.q],{ngForOf:[0,"ngForOf"]},null),(n()(),o.pb(17,0,null,null,2,"div",[["class","column is-4"]],null,null,null,null,null)),(n()(),o.eb(16777216,null,null,1,null,P)),o.ob(19,16384,null,0,u.j,[o.M,o.J],{ngIf:[0,"ngIf"]},null)],function(n,l){var e=l.component;n(l,1,0,e.parentForm),n(l,12,0,e.controlName),n(l,16,0,e.recipes),n(l,19,0,e.hasConnectedRecipe())},function(n,l){var e=l.component;n(l,0,0,o.Ab(l,3).ngClassUntouched,o.Ab(l,3).ngClassTouched,o.Ab(l,3).ngClassPristine,o.Ab(l,3).ngClassDirty,o.Ab(l,3).ngClassValid,o.Ab(l,3).ngClassInvalid,o.Ab(l,3).ngClassPending),n(l,6,0,e.controlName),n(l,7,0,e.day),n(l,9,0,e.controlName,o.Ab(l,14).ngClassUntouched,o.Ab(l,14).ngClassTouched,o.Ab(l,14).ngClassPristine,o.Ab(l,14).ngClassDirty,o.Ab(l,14).ngClassValid,o.Ab(l,14).ngClassInvalid,o.Ab(l,14).ngClassPending)})}var E=e("wjSy"),F=e("7csi"),S=e("yubq");class C{constructor(n,l,e,o,t,i){this.householdService=n,this.cookbookService=l,this.shoppingListService=e,this.foodPlanService=o,this.deleteHintService=t,this.formBuilder=i,this.subscriptions=[],this.loading=!1}ngOnInit(){this.observeUndo(),this.observeHousehold(),this.observeCookbook(),this.observeShoppingList(),this.createForm()}ngOnDestroy(){this.subscriptions.forEach(n=>n.unsubscribe())}observeUndo(){this.subscriptions.push(this.deleteHintService.onUndo().subscribe(()=>this.loading=!1))}observeHousehold(){this.subscriptions.push(this.householdService.observeHousehold().subscribe(n=>{this.foodPlanService.determineFoodPlan(n).subscribe(n=>this.foodPlan=n),this.shoppingListService.determineShoppingList(n).subscribe(n=>this.shoppingList=n),this.cookbookService.determineCookbook(n).subscribe(n=>this.cookbook=n)}))}observeShoppingList(){this.subscriptions.push(this.shoppingListService.observeShoppingList().subscribe(n=>this.shoppingList=n))}observeCookbook(){this.subscriptions.push(this.cookbookService.observeCookbook().subscribe(n=>this.cookbook=n))}createForm(){this.foodPlanForm=this.formBuilder.group({})}clearFoodPlan(){this.loading=!0,this.foodPlanService.clearFoodPlan(this.foodPlan).subscribe(n=>{this.foodPlan=n,this.copyDataToForm(),this.loading=!1})}isLoading(){return this.loading}copyDataToForm(){this.foodPlanForm.controls.monday.setValue(this.foodPlan.meals.monday.name),this.foodPlanForm.controls.tuesday.setValue(this.foodPlan.meals.tuesday.name),this.foodPlanForm.controls.wednesday.setValue(this.foodPlan.meals.wednesday.name),this.foodPlanForm.controls.thursday.setValue(this.foodPlan.meals.thursday.name),this.foodPlanForm.controls.friday.setValue(this.foodPlan.meals.friday.name),this.foodPlanForm.controls.saturday.setValue(this.foodPlan.meals.saturday.name),this.foodPlanForm.controls.sunday.setValue(this.foodPlan.meals.sunday.name)}onRecipeSelection(n){this.selectedRecipe=n.recipe,this.currentMeal=n.meal}onMealNameChange(n){this.foodPlan=n}onIngredientSelection(n){this.loading=!0;const l=[];n.forEach(n=>l.push(n)),this.shoppingListService.addShoppingListItems(this.shoppingList.shoppingListGroups[0],l).subscribe(()=>{this.foodPlanService.saveMeal(this.currentMeal,this.selectedRecipe).subscribe(n=>{this.foodPlan=n,this.currentMeal=null,this.selectedRecipe=null,this.loading=!1})})}}var N=o.nb({encapsulation:0,styles:[[""]],data:{}});function A(n){return o.Kb(0,[(n()(),o.pb(0,0,null,null,1,"app-select-recipe",[],null,[[null,"ingredientsEmitter"]],function(n,l,e){var o=!0;return"ingredientsEmitter"===l&&(o=!1!==n.component.onIngredientSelection(e)&&o),o},a.b,a.a)),o.ob(1,114688,null,0,r.a,[],{recipe:[0,"recipe"]},{ingredientsEmitter:"ingredientsEmitter"})],function(n,l){n(l,1,0,l.component.selectedRecipe)},null)}function I(n){return o.Kb(0,[(n()(),o.pb(0,0,null,null,25,"form",[["novalidate",""]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"submit"],[null,"reset"]],function(n,l,e){var t=!0;return"submit"===l&&(t=!1!==o.Ab(n,2).onSubmit(e)&&t),"reset"===l&&(t=!1!==o.Ab(n,2).onReset()&&t),t},null,null)),o.ob(1,16384,null,0,s.q,[],null,null),o.ob(2,540672,null,0,s.g,[[8,null],[8,null]],{form:[0,"form"]},null),o.Fb(2048,null,s.b,null,[s.g]),o.ob(4,16384,null,0,s.k,[[4,s.b]],null,null),(n()(),o.pb(5,0,null,null,1,"app-meal",[["controlName","monday"],["day","Montag"]],null,[[null,"foodPlanEmitter"],[null,"recipeEmitter"]],function(n,l,e){var o=!0,t=n.component;return"foodPlanEmitter"===l&&(o=!1!==t.onMealNameChange(e)&&o),"recipeEmitter"===l&&(o=!1!==t.onRecipeSelection(e)&&o),o},y,v)),o.ob(6,245760,null,0,f,[h,c.a,g.l],{cookbook:[0,"cookbook"],meal:[1,"meal"],day:[2,"day"],controlName:[3,"controlName"],parentForm:[4,"parentForm"]},{foodPlanEmitter:"foodPlanEmitter",recipeEmitter:"recipeEmitter"}),(n()(),o.pb(7,0,null,null,1,"app-meal",[["controlName","tuesday"],["day","Dienstag"]],null,[[null,"foodPlanEmitter"],[null,"recipeEmitter"]],function(n,l,e){var o=!0,t=n.component;return"foodPlanEmitter"===l&&(o=!1!==t.onMealNameChange(e)&&o),"recipeEmitter"===l&&(o=!1!==t.onRecipeSelection(e)&&o),o},y,v)),o.ob(8,245760,null,0,f,[h,c.a,g.l],{cookbook:[0,"cookbook"],meal:[1,"meal"],day:[2,"day"],controlName:[3,"controlName"],parentForm:[4,"parentForm"]},{foodPlanEmitter:"foodPlanEmitter",recipeEmitter:"recipeEmitter"}),(n()(),o.pb(9,0,null,null,1,"app-meal",[["controlName","wednesday"],["day","Mittwoch"]],null,[[null,"foodPlanEmitter"],[null,"recipeEmitter"]],function(n,l,e){var o=!0,t=n.component;return"foodPlanEmitter"===l&&(o=!1!==t.onMealNameChange(e)&&o),"recipeEmitter"===l&&(o=!1!==t.onRecipeSelection(e)&&o),o},y,v)),o.ob(10,245760,null,0,f,[h,c.a,g.l],{cookbook:[0,"cookbook"],meal:[1,"meal"],day:[2,"day"],controlName:[3,"controlName"],parentForm:[4,"parentForm"]},{foodPlanEmitter:"foodPlanEmitter",recipeEmitter:"recipeEmitter"}),(n()(),o.pb(11,0,null,null,1,"app-meal",[["controlName","thursday"],["day","Donnerstag"]],null,[[null,"foodPlanEmitter"],[null,"recipeEmitter"]],function(n,l,e){var o=!0,t=n.component;return"foodPlanEmitter"===l&&(o=!1!==t.onMealNameChange(e)&&o),"recipeEmitter"===l&&(o=!1!==t.onRecipeSelection(e)&&o),o},y,v)),o.ob(12,245760,null,0,f,[h,c.a,g.l],{cookbook:[0,"cookbook"],meal:[1,"meal"],day:[2,"day"],controlName:[3,"controlName"],parentForm:[4,"parentForm"]},{foodPlanEmitter:"foodPlanEmitter",recipeEmitter:"recipeEmitter"}),(n()(),o.pb(13,0,null,null,1,"app-meal",[["controlName","friday"],["day","Freitag"]],null,[[null,"foodPlanEmitter"],[null,"recipeEmitter"]],function(n,l,e){var o=!0,t=n.component;return"foodPlanEmitter"===l&&(o=!1!==t.onMealNameChange(e)&&o),"recipeEmitter"===l&&(o=!1!==t.onRecipeSelection(e)&&o),o},y,v)),o.ob(14,245760,null,0,f,[h,c.a,g.l],{cookbook:[0,"cookbook"],meal:[1,"meal"],day:[2,"day"],controlName:[3,"controlName"],parentForm:[4,"parentForm"]},{foodPlanEmitter:"foodPlanEmitter",recipeEmitter:"recipeEmitter"}),(n()(),o.pb(15,0,null,null,1,"app-meal",[["controlName","saturday"],["day","Samstag"]],null,[[null,"foodPlanEmitter"],[null,"recipeEmitter"]],function(n,l,e){var o=!0,t=n.component;return"foodPlanEmitter"===l&&(o=!1!==t.onMealNameChange(e)&&o),"recipeEmitter"===l&&(o=!1!==t.onRecipeSelection(e)&&o),o},y,v)),o.ob(16,245760,null,0,f,[h,c.a,g.l],{cookbook:[0,"cookbook"],meal:[1,"meal"],day:[2,"day"],controlName:[3,"controlName"],parentForm:[4,"parentForm"]},{foodPlanEmitter:"foodPlanEmitter",recipeEmitter:"recipeEmitter"}),(n()(),o.pb(17,0,null,null,1,"app-meal",[["controlName","sunday"],["day","Sonntag"]],null,[[null,"foodPlanEmitter"],[null,"recipeEmitter"]],function(n,l,e){var o=!0,t=n.component;return"foodPlanEmitter"===l&&(o=!1!==t.onMealNameChange(e)&&o),"recipeEmitter"===l&&(o=!1!==t.onRecipeSelection(e)&&o),o},y,v)),o.ob(18,245760,null,0,f,[h,c.a,g.l],{cookbook:[0,"cookbook"],meal:[1,"meal"],day:[2,"day"],controlName:[3,"controlName"],parentForm:[4,"parentForm"]},{foodPlanEmitter:"foodPlanEmitter",recipeEmitter:"recipeEmitter"}),(n()(),o.pb(19,0,null,null,6,"div",[["class","columns is-mobile"]],null,null,null,null,null)),(n()(),o.pb(20,0,null,null,5,"div",[["class","column"]],null,null,null,null,null)),(n()(),o.pb(21,0,null,null,4,"button",[["class","button"],["id","clear-button"]],[[8,"disabled",0]],[[null,"click"]],function(n,l,e){var o=!0;return"click"===l&&(o=!1!==n.component.clearFoodPlan()&&o),o},null,null)),o.Fb(512,null,u.q,u.r,[o.q,o.r,o.k,o.B]),o.ob(23,278528,null,0,u.h,[u.q],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),o.Db(24,{"is-loading":0}),(n()(),o.Ib(-1,null,["Leeren"]))],function(n,l){var e=l.component;n(l,2,0,e.foodPlanForm),n(l,6,0,e.cookbook,e.foodPlan.meals.monday,"Montag","monday",e.foodPlanForm),n(l,8,0,e.cookbook,e.foodPlan.meals.tuesday,"Dienstag","tuesday",e.foodPlanForm),n(l,10,0,e.cookbook,e.foodPlan.meals.wednesday,"Mittwoch","wednesday",e.foodPlanForm),n(l,12,0,e.cookbook,e.foodPlan.meals.thursday,"Donnerstag","thursday",e.foodPlanForm),n(l,14,0,e.cookbook,e.foodPlan.meals.friday,"Freitag","friday",e.foodPlanForm),n(l,16,0,e.cookbook,e.foodPlan.meals.saturday,"Samstag","saturday",e.foodPlanForm),n(l,18,0,e.cookbook,e.foodPlan.meals.sunday,"Sonntag","sunday",e.foodPlanForm);var o=n(l,24,0,e.isLoading());n(l,23,0,"button",o)},function(n,l){var e=l.component;n(l,0,0,o.Ab(l,4).ngClassUntouched,o.Ab(l,4).ngClassTouched,o.Ab(l,4).ngClassPristine,o.Ab(l,4).ngClassDirty,o.Ab(l,4).ngClassValid,o.Ab(l,4).ngClassInvalid,o.Ab(l,4).ngClassPending),n(l,21,0,!e.foodPlanForm.valid)})}function M(n){return o.Kb(0,[(n()(),o.eb(16777216,null,null,1,null,A)),o.ob(1,16384,null,0,u.j,[o.M,o.J],{ngIf:[0,"ngIf"]},null),(n()(),o.eb(16777216,null,null,1,null,I)),o.ob(3,16384,null,0,u.j,[o.M,o.J],{ngIf:[0,"ngIf"]},null)],function(n,l){var e=l.component;n(l,1,0,e.selectedRecipe),n(l,3,0,e.foodPlan)},null)}function R(n){return o.Kb(0,[(n()(),o.pb(0,0,null,null,1,"app-food-plan-page",[],null,null,null,M,N)),o.ob(1,245760,null,0,C,[E.a,c.a,F.a,h,S.a,s.d],null,null)],function(n,l){n(l,1,0)},null)}var z=o.lb("app-food-plan-page",C,R,{},{},[]),w=e("Ukb2");class j{}e.d(l,"FoodPlanModuleNgFactory",function(){return L});var L=o.mb(t,[],function(n){return o.yb([o.zb(512,o.j,o.X,[[8,[i.a,z]],[3,o.j],o.v]),o.zb(4608,u.l,u.k,[o.s,[2,u.t]]),o.zb(4608,s.d,s.d,[]),o.zb(4608,s.o,s.o,[]),o.zb(4608,b.i,b.o,[u.c,o.z,b.m]),o.zb(4608,b.p,b.p,[b.i,b.n]),o.zb(5120,b.a,function(n){return[n]},[b.p]),o.zb(4608,b.l,b.l,[]),o.zb(6144,b.j,null,[b.l]),o.zb(4608,b.h,b.h,[b.j]),o.zb(6144,b.b,null,[b.h]),o.zb(4608,b.g,b.k,[b.b,o.p]),o.zb(4608,b.c,b.c,[b.g]),o.zb(1073742336,u.b,u.b,[]),o.zb(1073742336,s.n,s.n,[]),o.zb(1073742336,s.l,s.l,[]),o.zb(1073742336,b.e,b.e,[]),o.zb(1073742336,b.d,b.d,[]),o.zb(1073742336,w.a,w.a,[]),o.zb(1073742336,g.o,g.o,[[2,g.t],[2,g.l]]),o.zb(1073742336,j,j,[]),o.zb(1073742336,t,t,[]),o.zb(256,b.m,"XSRF-TOKEN",[]),o.zb(256,b.n,"X-XSRF-TOKEN",[]),o.zb(1024,g.j,function(){return[[{path:"",component:C}]]},[])])})}}]);
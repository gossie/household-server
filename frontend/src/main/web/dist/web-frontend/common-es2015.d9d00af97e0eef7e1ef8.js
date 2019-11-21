(window.webpackJsonp=window.webpackJsonp||[]).push([[1],{"3kpS":function(e,n,t){"use strict";t.d(n,"a",function(){return p});var l=t("xdv0"),i=t("dNeE"),s=t("q+Bj"),u=t("yrbL"),o=t("nlbl"),c=t("8Y7J"),r=t("IheW");let p=(()=>{class e extends s.a{constructor(e,n){super(),this.userService=e,this.httpClient=n,this.cookbookSubject=new i.BehaviorSubject(null),this.recipeSubject=new i.BehaviorSubject(null)}determineCookbook(e){const n=this.determineUrl(e,"cookbook");return this.httpClient.get(n,{headers:{Accept:"application/vnd.household.min.v1+json"}}).pipe(Object(u.tap)(e=>this.cookbookSubject.next(e)))}determineRecipe(e){const n=this.determineUrl(e,"self");return this.httpClient.get(n,{headers:{Accept:"application/vnd.household.v1+json"}})}deleteRecipe(e){const n=this.determineUrl(e,"self");return this.httpClient.delete(n,{headers:{Accept:"application/vnd.household.min.v1+json"}}).pipe(Object(u.tap)(e=>this.cookbookSubject.next(e)))}createRecipe(e,n){const t=this.determineUrl(e,"create");return this.httpClient.post(t,n,{headers:{"Content-Type":"application/vnd.household.v1+json",Accept:"application/vnd.household.min.v1+json"}}).pipe(Object(u.tap)(e=>this.cookbookSubject.next(e)))}editRecipe(e){const n=this.determineUrl(e,"self");return this.httpClient.put(n,e,{headers:{"Content-Type":"application/vnd.household.v1+json",Accept:"application/vnd.household.min.v1+json"}}).pipe(Object(u.tap)(e=>this.cookbookSubject.next(e)))}determineRecipeByUrl(e){this.httpClient.get(e,{headers:{Accept:"application/vnd.household.v1+json"}}).subscribe(e=>{this.recipeSubject.next(e)})}observeCookbook(){return this.cookbookSubject.asObservable().pipe(Object(u.filter)(e=>o.a.isObject(e)))}observeRecipe(){return this.recipeSubject.asObservable().pipe(Object(u.filter)(e=>o.a.isObject(e)))}}return e.ngInjectableDef=c.Ob({factory:function(){return new e(c.Pb(l.a),c.Pb(r.c))},token:e,providedIn:"root"}),e})()},"7csi":function(e,n,t){"use strict";t.d(n,"a",function(){return r});var l=t("dNeE"),i=t("q+Bj"),s=t("xdv0"),u=t("yrbL"),o=t("8Y7J"),c=t("IheW");let r=(()=>{class e extends i.a{constructor(e,n){super(),this.userService=e,this.httpClient=n,this.subject=new l.BehaviorSubject(null)}determineShoppingList(e){const n=this.determineUrl(e,"shoppingList");return this.httpClient.get(n,{headers:{Accept:"application/vnd.household.v1+json"}}).pipe(Object(u.tap)(e=>this.subject.next(e)))}addShoppingListGroup(e,n){const t=this.determineUrl(e,"add");return this.httpClient.post(t,{name:n,shoppingListItems:[],links:[]},{headers:{"Content-Type":"application/vnd.household.v1+json",Accept:"application/vnd.household.v1+json"}}).pipe(Object(u.tap)(e=>this.subject.next(e)))}deleteShoppingListGroup(e){const n=this.determineUrl(e,"delete");return this.httpClient.delete(n,{headers:{Accept:"application/vnd.household.v1+json"}}).pipe(Object(u.tap)(e=>this.subject.next(e)))}toggleShoppingListGroup(e){const n=this.determineUrl(e,"toggle");return this.httpClient.put(n,null,{headers:{Accept:"application/vnd.household.v1+json"}}).pipe(Object(u.tap)(e=>this.subject.next(e)))}clearShoppingListGroup(e){const n=this.determineUrl(e,"clear");return this.httpClient.delete(n,{headers:{Accept:"application/vnd.household.v1+json"}}).pipe(Object(u.tap)(e=>this.subject.next(e)))}addShoppingListItems(e,n){const t=this.determineUrl(e,"add"),l=n.map(e=>({name:e,selected:!1,links:[]}));return this.httpClient.post(t,l,{headers:{"Content-Type":"application/vnd.household.v1+json",Accept:"application/vnd.household.v1+json"}}).pipe(Object(u.tap)(e=>this.subject.next(e)))}toggleShoppingListItem(e){const n=this.determineUrl(e,"toggle");return this.httpClient.patch(n,null,{headers:{Accept:"application/vnd.household.v1+json"}}).pipe(Object(u.tap)(e=>this.subject.next(e)))}observeShoppingList(){return this.subject.asObservable()}}return e.ngInjectableDef=o.Ob({factory:function(){return new e(o.Pb(s.a),o.Pb(c.c))},token:e,providedIn:"root"}),e})()},GY0A:function(e,n,t){"use strict";var l=t("8Y7J"),i=t("p9E1"),s=t("ZCrL"),u=t("SVse");t("XJZM"),t.d(n,"a",function(){return o}),t.d(n,"b",function(){return r});var o=l.nb({encapsulation:0,styles:[[""]],data:{}});function c(e){return l.Kb(0,[(e()(),l.pb(0,0,null,null,3,"div",[["class","columns is-mobile"]],null,null,null,null,null)),(e()(),l.pb(1,0,null,null,2,"div",[["class","column"]],null,null,null,null,null)),(e()(),l.pb(2,0,null,null,1,"app-checkbox",[],[[8,"id",0]],[[null,"onSelect"]],function(e,n,t){var l=!0;return"onSelect"===n&&(l=!1!==e.component.toggleIngredient(t)&&l),l},i.b,i.a)),l.ob(3,49152,null,0,s.a,[],{label:[0,"label"],selected:[1,"selected"]},{onSelect:"onSelect"})],function(e,n){e(n,3,0,n.context.$implicit.name,n.component.isSelected(n.context.$implicit))},function(e,n){e(n,2,0,l.tb(1,"ingredient-",n.context.index,""))})}function r(e){return l.Kb(0,[(e()(),l.pb(0,0,null,null,14,"div",[["class","modal is-active"]],null,null,null,null,null)),(e()(),l.pb(1,0,null,null,0,"div",[["class","modal-background"]],null,null,null,null,null)),(e()(),l.pb(2,0,null,null,12,"div",[["class","modal-content"]],null,null,null,null,null)),(e()(),l.pb(3,0,null,null,11,"div",[["class","box"]],null,null,null,null,null)),(e()(),l.pb(4,0,null,null,1,"h4",[["class","title is-4"]],null,null,null,null,null)),(e()(),l.Ib(-1,null,["Was muss noch auf die Einkaufsliste?"])),(e()(),l.eb(16777216,null,null,1,null,c)),l.ob(7,278528,null,0,u.i,[l.M,l.J,l.q],{ngForOf:[0,"ngForOf"]},null),(e()(),l.pb(8,0,null,null,6,"div",[["class","columns is-mobile"]],null,null,null,null,null)),(e()(),l.pb(9,0,null,null,5,"div",[["class","column"]],null,null,null,null,null)),(e()(),l.pb(10,0,null,null,4,"button",[["class","button is-primary"],["id","save-ingredients-button"]],null,[[null,"click"]],function(e,n,t){var l=!0;return"click"===n&&(l=!1!==e.component.saveIngredients()&&l),l},null,null)),l.Fb(512,null,u.q,u.r,[l.q,l.r,l.k,l.B]),l.ob(12,278528,null,0,u.h,[u.q],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),l.Db(13,{"is-loading":0}),(e()(),l.Ib(-1,null,[" Speichern "]))],function(e,n){var t=n.component;e(n,7,0,t.recipe.ingredients);var l=e(n,13,0,t.loading);e(n,12,0,"button is-primary",l)},null)}},XJZM:function(e,n,t){"use strict";t.d(n,"a",function(){return i});var l=t("8Y7J");class i{constructor(){this.ingredientsEmitter=new l.m,this.loading=!1,this.selectedIngredients=new Set}ngOnInit(){this.loading=!1}toggleIngredient(e){this.selectedIngredients.has(e.value)?this.selectedIngredients.delete(e.value):this.selectedIngredients.add(e.value)}isSelected(e){return this.selectedIngredients.has(e.name)}saveIngredients(){this.loading=!0,this.ingredientsEmitter.emit(this.selectedIngredients),this.selectedIngredients=new Set}}},ZCrL:function(e,n,t){"use strict";t.d(n,"a",function(){return i});var l=t("8Y7J");class i{constructor(){this.selected=!1,this.onSelect=new l.m}toggleCheckbox(){this.selected=!this.selected,this.onSelect.emit({selected:this.selected,value:this.label})}}},p9E1:function(e,n,t){"use strict";var l=t("8Y7J"),i=t("SVse");t("ZCrL"),t.d(n,"a",function(){return s}),t.d(n,"b",function(){return c});var s=l.nb({encapsulation:0,styles:[[""]],data:{}});function u(e){return l.Kb(0,[(e()(),l.pb(0,0,null,null,1,"span",[["class","icon"]],null,null,null,null,null)),(e()(),l.pb(1,0,null,null,0,"i",[["class","far fa-square"]],null,null,null,null,null))],null,null)}function o(e){return l.Kb(0,[(e()(),l.pb(0,0,null,null,1,"span",[["class","icon"]],null,null,null,null,null)),(e()(),l.pb(1,0,null,null,0,"i",[["class","far fa-check-square"]],null,null,null,null,null))],null,null)}function c(e){return l.Kb(0,[(e()(),l.pb(0,0,null,null,5,"span",[],null,[[null,"click"]],function(e,n,t){var l=!0;return"click"===n&&(l=!1!==e.component.toggleCheckbox()&&l),l},null,null)),(e()(),l.eb(16777216,null,null,1,null,u)),l.ob(2,16384,null,0,i.j,[l.M,l.J],{ngIf:[0,"ngIf"]},null),(e()(),l.eb(16777216,null,null,1,null,o)),l.ob(4,16384,null,0,i.j,[l.M,l.J],{ngIf:[0,"ngIf"]},null),(e()(),l.Ib(5,null,[" ","\n"]))],function(e,n){var t=n.component;e(n,2,0,!t.selected),e(n,4,0,t.selected)},function(e,n){e(n,5,0,n.component.label)})}}}]);
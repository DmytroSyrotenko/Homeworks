import {Routes} from "@angular/router";
import {RegisterComponent} from "../register/register.component";
import {CartComponent} from "./cart.component";

export const CART_ROUTES: Routes = [

  {
    path: '',
    component: CartComponent
  }
]

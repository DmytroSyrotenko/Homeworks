import {Routes} from "@angular/router";
import {RegisterComponent} from "../register/register.component";
import {CartComponent} from "./cart.component";
import {PdpComponent} from "../pdp/pdp.component";

export const CART_ROUTES: Routes = [

  {
    path: '',
    component: CartComponent
  }
]

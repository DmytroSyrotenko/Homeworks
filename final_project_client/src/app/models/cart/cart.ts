import {CartEntry} from "./cart-entry";

export interface Cart {
  id:number
  active: boolean
  totalPrice: number
  entries: CartEntry[]
}

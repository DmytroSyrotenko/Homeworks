import {CartEntry} from "./cart-entry";

export interface Cart {
  active: boolean
  totalPrice: number
  entries: CartEntry[]
}

import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Observable, Subscription} from "rxjs";
import {Router} from "@angular/router";
import {AsyncPipe, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {CartEntry} from "../../models/cart-entry";
import {CartService} from "../../services/cart.service";
import {Cart} from "../../models/cart";

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    AsyncPipe,
    NgForOf,
    NgIf,
    JsonPipe
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent implements OnInit,OnDestroy{

  private _subscription= new Subscription();
  constructor(private _authService:AuthService,private _router: Router,private _cartService:CartService) {
  }

  cart$:Observable<Cart>= this._cartService.getCart();//нужно ли вносить в подписку чтобы потом отписаться


  ngOnInit(): void {//проверка что залогинен перед действиями в карте
    this._subscription.add(
      this._authService.isLoggedIn()
        .subscribe(isLoggedIn=>{
          if(!isLoggedIn){
            this._router.navigateByUrl('/plp')
          }
          }
        )
    )
  }

  ngOnDestroy(): void {
    this._subscription.unsubscribe()
  }



}

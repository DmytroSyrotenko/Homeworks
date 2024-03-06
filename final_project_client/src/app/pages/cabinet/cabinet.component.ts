import {Component, OnDestroy, OnInit} from "@angular/core";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {AsyncPipe, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Observable, Subscription} from "rxjs";

import {CabinetService} from "../../services/cabinet.service";
import {UserInfo} from "../../models/cabinet/user-info";
import {ProductPlp} from "../../models/product/product-plp";
import {OrderInfo} from "../../models/cabinet/order-info";
import {CartService} from "../../services/cart.service";


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    AsyncPipe,
    NgForOf,
    JsonPipe
  ],
  templateUrl: './cabinet.component.html',
  styleUrl: './cabinet.component.scss'
})
export class CabinetComponent implements OnInit, OnDestroy {
  private _subscription = new Subscription();

  userInfo$: Observable<UserInfo> = this._cabinetService.getUserInfo();
  ordersInfo$: Observable<OrderInfo[]> = this._cartService.getUserOrders();


  formUserUpdate: FormGroup = this._fb.group({  //реактивная форма для сбора значений
      firstName: new FormControl(null, [Validators.required]),
      lastName: new FormControl(null, [Validators.required]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      deliveryInfo: new FormControl(null, [Validators.required]),
      balance: new FormControl(null, [Validators.required])
    }
  )


  private updateFormValues(): void {
    let temp = this.userInfo$.forEach(i => {
      this.formUserUpdate.patchValue({
        firstName: i.firstName,
        lastName: i.lastName,
        email: i.email,
        deliveryInfo: i.deliveryInfo,
        balance: i.balance
      })
    });
  }


  constructor(
    private _authService: LoginService,
    private _router: Router,
    private _cabinetService: CabinetService,
    private _fb: FormBuilder,
    private _cartService: CartService
  ) {
  }

  ngOnInit(): void {//проверка что залогинен перед действиями на стр
    this._subscription.add(
      this._authService.isLoggedIn()
        .subscribe(isLoggedIn => {
            if (!isLoggedIn) {
              this._router.navigateByUrl('/plp')
            }
          }
        )
    )
    this.updateFormValues();
  }

  updateUserData(): void {
    if (this.formUserUpdate.valid) {
      console.log(this.formUserUpdate)
      let data: UserInfo = {...this.formUserUpdate.value}// перелив пересекающихся полей
      this._subscription.add(
        this._cabinetService.updateUserInfo(data).subscribe(
          () => this._router.navigateByUrl('/plp'),
          (error) =>
            console.log('error', error)
        )
      )
    }
  }

  ngOnDestroy(): void {
    this._subscription.unsubscribe()
  }
}



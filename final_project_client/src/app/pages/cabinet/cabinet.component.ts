import {Component, OnDestroy, OnInit} from "@angular/core";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Observable, Subscription} from "rxjs";

import {CabinetService} from "../../services/cabinet.service";
import {UserInfo} from "../../models/cabinet/user-info";


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    AsyncPipe,
    NgForOf
  ],
  templateUrl: './cabinet.component.html',
  styleUrl: './cabinet.component.scss'
})
export class CabinetComponent implements OnInit, OnDestroy {
  private _subscription = new Subscription();

  userInfo$: Observable<UserInfo> = this._cabinetService.getUserInfo();


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
        email:i.email,
        deliveryInfo:i.deliveryInfo,
        balance:i.balance
      })
    });
  }


  constructor(
    private _authService: AuthService,
    private _router: Router,
    private _cabinetService: CabinetService,
    private _fb: FormBuilder
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



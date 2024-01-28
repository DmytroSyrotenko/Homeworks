import {Component, OnDestroy} from '@angular/core';
import {NgIf} from "@angular/common";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {RegisterData} from "../../models/register-data";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnDestroy {
  private _subscription = new Subscription();//складываем туда все подписки и потом отписываемся

  formLogin: FormGroup = this._fb.group({  //реактивная форма для сбора значений
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required]),
    }
  )

  constructor(
    private _fb: FormBuilder,
    private _authService: AuthService,
    private _router:Router) {
  }

  login(): void {
    if (this.formLogin.valid) {
      let data: RegisterData = {...this.formLogin.value}// перелив пересекающихся полей
      this._subscription.add(
        this._authService.login(data).subscribe(
          (auth) => {
            localStorage.setItem('token', JSON.stringify(auth))
            this._authService.setLoggedIn(true);
            this._router.navigateByUrl('/plp')
            console.log('current user',data.email)
          },
          (error) => {
            this._authService.setLoggedIn(false);
            localStorage.removeItem('token')
          }
        )
      )
    }
  }

  ngOnDestroy(): void {
    this._subscription.unsubscribe();
  }

}

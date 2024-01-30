import {Component, OnDestroy} from '@angular/core';
import {NgIf} from "@angular/common";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {LoginService} from "../../services/login.service";
import {RegisterData} from "../../models/auth/register-data";
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
    private _loginService: LoginService,
    private _router: Router) {
  }

  login(): void {
    if (this.formLogin.valid) {
      let data: RegisterData = {...this.formLogin.value}// перелив пересекающихся полей
      this._subscription.add(
        this._loginService.login(data).subscribe(
          (auth) => {
            localStorage.setItem('token', JSON.stringify(auth))
            this._loginService.setLoggedIn(true);
            this._router.navigateByUrl('/plp').then(() => {
              window.location.reload();
            })
          },
          (error) => {
            this._loginService.setLoggedIn(false);
            localStorage.removeItem('token')
          }
        )
      )
    }
  }

  createAccount(): void {
    this._router.navigateByUrl('/register')
  }

  ngOnDestroy(): void {
    this._subscription.unsubscribe();
  }

}

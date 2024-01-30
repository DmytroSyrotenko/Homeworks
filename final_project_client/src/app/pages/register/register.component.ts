import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {RegisterData} from "../../models/auth/register-data";
import {Subscription} from "rxjs";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  private _subscription = new Subscription();

  formRegister: FormGroup = this._fb.group({  //реактивная форма для сбора значений
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required]),
    }
  )

  constructor(private _fb: FormBuilder,private _loginService: LoginService, private _router: Router) {
  }


  register(): void {
    if (this.formRegister.valid) {
      let data: RegisterData = {...this.formRegister.value}// перелив пересекающихся полей
      this._subscription.add(
        this._loginService.register(data).subscribe(
          (auth) => {
            localStorage.setItem('token', JSON.stringify(auth))
            this._loginService.setLoggedIn(true);
            this._router.navigateByUrl('/plp')
          },
          (error) => {
            this._loginService.setLoggedIn(false);
            localStorage.removeItem('token')
          }
        )
      )
    }
  }
}

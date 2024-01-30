import {Component} from '@angular/core';
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import {LoginService} from "./services/login.service";
import {AsyncPipe, NgIf} from "@angular/common";
import {Observable} from "rxjs";
import {UserInfo} from "./models/cabinet/user-info";
import {CabinetService} from "./services/cabinet.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, NgIf, AsyncPipe],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'final_project_client';

//достали переменную с компонента на главный компонент и потом в хтмл
  isLoggedIn$: Observable<boolean> = this._loginService.isLoggedIn();

  userInfo$: Observable<UserInfo> = this._cabinetService.getUserInfo();

  constructor(private _loginService: LoginService, private _router: Router, private _cabinetService: CabinetService) {
  }

  login(): void {
    this._router.navigateByUrl('/login');
  }

  logout(): void {
    this._router.navigateByUrl('/logout');
  }

  register(): void {
    this._router.navigateByUrl('/register');
  }

  cart() {
    if (this._loginService.isLoggedIn()) {
      this._router.navigateByUrl('/cart');
    }
  }

  cabinet() {
    this._router.navigateByUrl('/cabinet')
  }

  mainPage(): void {
    this._router.navigateByUrl("plp");
  }

}

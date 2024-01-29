import {Component} from '@angular/core';
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import {AuthService} from "./services/auth.service";
import {AsyncPipe, NgIf} from "@angular/common";
import {Observable} from "rxjs";

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
  isLoggedIn$: Observable<boolean> = this._authService.isLoggedIn();

  constructor(private _authService: AuthService, private _router: Router) {
  }

  login(): void {
    this._router.navigateByUrl('/login');
  }

  register(): void {
    this._router.navigateByUrl('/register');
  }

  cart() {
    if (this._authService.isLoggedIn()) {
      this._router.navigateByUrl('/cart');
    }
  }

  cabinet(){
    this._router.navigateByUrl('/cabinet')
  }

  mainPage():void{
    this._router.navigateByUrl("plp");
  }
}

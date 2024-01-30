import {Component, OnDestroy, OnInit} from "@angular/core";

import {Router} from "@angular/router";
import {NgIf} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.scss'
})
export class LogoutComponent implements OnInit {


  constructor(private _router: Router, private _authService: LoginService,) {
  }

  ngOnInit(): void {
    this.logout();
  }

  logout(): void {
    this._authService.setLoggedIn(false);
    localStorage.removeItem('token')
    this._router.navigateByUrl('/plp').then(
      () => {
        window.location.reload();
      })
  }

}

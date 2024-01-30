import {Injectable} from "@angular/core";
import {httpConfig} from "../app.config";
import {HttpClient} from "@angular/common/http";
import {RegisterData} from "../models/auth/register-data";
import {BehaviorSubject, map, Observable, tap} from "rxjs";
import {AuthData} from "../models/auth/auth-data";

@Injectable({// аннотация бина в angular
  providedIn: "root" // чтобы был доступен везде
})

export class LoginService {

  private _isLoggedInSubject$ = new BehaviorSubject<boolean | undefined>(undefined);
  private _apiUrl: string = `${httpConfig.apiUrl}/auth`;

  constructor(private _http: HttpClient) {
  }


  register(data: RegisterData): Observable<AuthData> {
    return this._http.post<AuthData>(`${this._apiUrl}/register`, data)
  }

  login(data: RegisterData): Observable<AuthData> {
    return this._http.post<AuthData>(`${this._apiUrl}/login`, data)
  }

  setLoggedIn(isLoggedIn: boolean): void {
    this._isLoggedInSubject$.next(isLoggedIn);
  }

  getToken(): string | null {
    let token = localStorage.getItem('token');
    if (token) {
      let authData: AuthData = JSON.parse(token);
      return authData.token;
    }
    return null;
  }

  isLoggedIn(): Observable<boolean> {
    return this._isLoggedInSubject$.asObservable()
      .pipe(
        map(isLoggedIn => {
          if (!isLoggedIn) {
            let token = localStorage.getItem('token');
            if (token) {
              let authData: AuthData = JSON.parse(token);
              return !!authData?.token;
            } else {
              return false;
            }
          }
          return isLoggedIn;
        })
      );
  }
}

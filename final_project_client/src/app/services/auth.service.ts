import {Injectable} from "@angular/core";
import {httpConfig} from "../app.config";
import {HttpClient} from "@angular/common/http";
import {RegisterData} from "../models/register-data";
import {Observable} from "rxjs";
import {AuthData} from "../models/auth-data";

@Injectable()// аннотация бина в angular
export class AuthService {

  private _apiUrl: string = `${httpConfig.apiUrl}/auth`;

  constructor(private _http: HttpClient/* подключение хттп клиента*/) {
  }


  register(data: RegisterData): Observable<AuthData> {
    return this._http.post<AuthData>('${this._apiUrl)}/register', data)
  }

  login(data: RegisterData): Observable<AuthData> {
    return this._http.post<AuthData>('${this._apiUrl)}/login', data)
  }
}

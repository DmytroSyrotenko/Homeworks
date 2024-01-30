import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {RegisterData} from "../models/auth/register-data";
import {Observable} from "rxjs";
import {AuthData} from "../models/auth/auth-data";
import {httpConfig} from "../app.config";

@Injectable({
  providedIn: "root"
})
export class RegisterService {

  private _apiUrl: string = `${httpConfig.apiUrl}/auth`;

  constructor(private _http: HttpClient) {
  }



}

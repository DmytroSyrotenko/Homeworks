import {httpConfig} from "../app.config";
import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthService} from "./auth.service";
import {ProductPlp} from "../models/product-plp";
import {CartEntry} from "../models/cart-entry";

@Injectable({
  providedIn: "root"
})
export class CartService {


  private _apiUrl: string = `${httpConfig.apiPersonalUrl}/cart`;

  constructor(private _http: HttpClient, private _authService: AuthService) {
  }

  addToCart(productVariantId: number, quantity: number = 1): Observable<string> {
    let headers = new HttpHeaders();
    let token = this._authService.getToken();
    headers = headers.set('Authorization', `Bearer ${token}`)//добавляем параметры в хттп-рек куда и как нам надо
    let params = new HttpParams();
    params = params.set('productVariantId', productVariantId);
    params = params.set('quantity', quantity);
    let options = {params, headers}
    console.log('options', options)
    return this._http.post<string>(this._apiUrl, null, options)
  }

  getAllCartEntries(): Observable<CartEntry[]> {
    let headers = new HttpHeaders();
    let token = this._authService.getToken();
    headers = headers.set('Authorization', `Bearer ${token}`)//добавляем параметры в хттп-рек куда и как нам надо
    let options = {headers}
    console.log('cart in service', null)
    return this._http.get<CartEntry[]>(this._apiUrl, options)
  }

}

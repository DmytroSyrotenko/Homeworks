import {httpConfig} from "../app.config";
import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {LoginService} from "./login.service";
import {Cart} from "../models/cart/cart";
import {OrderInfo} from "../models/cabinet/order-info";

@Injectable({
  providedIn: "root"
})
export class CartService {

  private _apiUrl: string = `${httpConfig.apiPersonalUrl}/cart`;
  private _apiUrlOnlyForOrder: string = `${httpConfig.apiPersonalUrl}/order`;

  constructor(
    private _http: HttpClient,
    private _authService: LoginService
  ) {
  }


  addToCart(productVariantId: number, quantity: number = 1): Observable<string> {
    let headers = new HttpHeaders();
    let token = this._authService.getToken();
    headers = headers.set('Authorization', `Bearer ${token}`)//добавляем хедері в хттп-рек куда и как нам надо
    let params = new HttpParams();
    params = params.set('productVariantId', productVariantId);
    params = params.set('quantity', quantity);
    let options = {params, headers}
    return this._http.post<string>(this._apiUrl, null, options)
  }


  getCart(): Observable<Cart> {
    let headers = new HttpHeaders();
    let token = this._authService.getToken();
    headers = headers.set('Authorization', `Bearer ${token}`)//добавляем параметры в хттп-рек куда и как нам надо
    let options = {headers};
    return this._http.get<Cart>(this._apiUrl, options)
  }

  createOrder(): Observable<string> {
    let headers = new HttpHeaders();
    let token = this._authService.getToken();
    headers = headers.set('Authorization', `Bearer ${token}`)//добавляем параметры в хттп-рек куда и как нам надо
    let options = {headers}
    return this._http.post<string>(this._apiUrlOnlyForOrder, null, options);
  }

  deleteItem(productVariantId: number):Observable<string> {
    let headers = new HttpHeaders();
    let token = this._authService.getToken();
    headers = headers.set('Authorization', `Bearer ${token}`)//добавляем параметры в хттп-рек куда и как нам надо
    let params = new HttpParams();
    params = params.set('productVariantId', productVariantId);
    let options = {headers ,params}
    return this._http.delete<string>(this._apiUrl, options);
  }


  getUserOrders(): Observable<OrderInfo[]>{
    let headers = new HttpHeaders();
    let token = this._authService.getToken();
    headers = headers.set('Authorization', `Bearer ${token}`)//добавляем параметры в хттп-рек куда и как нам надо
    let options = {headers};
    return this._http.get<OrderInfo[]>(this._apiUrlOnlyForOrder, options)

  }

}

import {Component, OnDestroy, OnInit} from '@angular/core';
import {PdpService} from "../../services/pdp.service";
import {Router} from "@angular/router";
import {BehaviorSubject, filter, map, Observable, of, Subscription, switchMap} from "rxjs";
import {ProductPdp} from "../../models/product-pdp";
import {AsyncPipe, JsonPipe, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {CartService} from "../../services/cart.service";
import {error} from "@angular/compiler-cli/src/transformers/util";


export interface PriceVariant {
  //хардкодом создали тут класс под вариант
  productId: number;
  price: string;
}

@Component({
  selector: 'app-pdp',
  standalone: true,
  imports: [
    AsyncPipe,
    JsonPipe,
    NgIf,
    NgForOf,
    NgOptimizedImage
  ],
  templateUrl: './pdp.component.html',
  styleUrl: './pdp.component.scss'
})
export class PdpComponent implements OnInit, OnDestroy {

  private _subscription = new Subscription();

  private _productPdpSubject
    = new BehaviorSubject<ProductPdp | undefined>(undefined);

  private _priceSubject$
    = new BehaviorSubject<PriceVariant | undefined>(undefined);

  readonly productPdp$: Observable<ProductPdp | undefined> = this._productPdpSubject.asObservable();
  readonly price$: Observable<PriceVariant | undefined> = this._priceSubject$.asObservable();
  readonly isLoggedIn$: Observable<boolean> = this._authService.isLoggedIn(); //создали переменную чтобы меняять статус активности корзины


  ssdSet: Set<number> = new Set<number>();
  ramSet: Set<number> = new Set<number>();
  colorSet: Set<string> = new Set<string>();

  formConfig: FormGroup = this._fb.group({  //реактивная форма для сбора значений
      ram: new FormControl(null, [Validators.required]),
      ssd: new FormControl(null, [Validators.required]),
      color: new FormControl(null, [Validators.required]),
    }
  )

  constructor(
    private _router: Router,
    private _pdpService: PdpService,
    private _fb: FormBuilder,
    private _authService: AuthService,
    private _cartService: CartService
  ) {
  }

  ngOnInit(): void {
    let url = this._router.routerState.snapshot.url;
    let productId = url.substring(url.lastIndexOf('/') + 1, url.length);
    // TODO привели тип переменной в файнд бай ид к намберу чтобы убрать конфликт
    if (productId) {
      this._subscription.add(
        this._pdpService.loadProductVariants(productId).subscribe(product => {
          if (product) {
            product.variants.forEach(variant => {
              this.ssdSet.add(variant.ssd);
              this.ramSet.add(variant.ram);
              this.colorSet.add(variant.color);
            })
            this._productPdpSubject.next(product);
          }
        })
      )
    }
    this._subscription.add(
      this.formConfig.valueChanges
        .pipe(
          filter(value => value.ram && value.ssd && value.color),
          switchMap(value => {
            return this.productPdp$
              .pipe(
                map(product => {
                  console.log('form', value)

                  let variants = product?.variants;

                  if (variants) {
                    for (let i = 0; i < variants.length; i++) {

                      console.log('id', variants[i].id)
                      console.log('ssd', variants[i].ssd)
                      console.log('ram', variants[i].ram)
                      console.log('color', variants[i].color)
                      if (variants[i].ram === value.ram && variants[i].ssd === value.ssd && variants[i].color === value.color) {
                        this._priceSubject$.next({
                          price: variants[i].price,
                          productId: variants[i].id
                          //создание анонимного класса через {}
                        });
                        break;
                      } else {
                        this._priceSubject$.next(undefined);
                      }
                    }
                  }
                  return value;
                })
              )
          })
        )
        .subscribe()
    )
  }

  addSsd(ssd: number): void {
    this.formConfig.controls['ssd'].setValue(ssd);
  }

  addRam(ram: number): void {
    this.formConfig.controls['ram'].setValue(ram);
  }

  addColor(color: string): void {
    this.formConfig.controls['color'].setValue(color);
  }


  shopNow(productId: number | undefined): void {
    if (productId) {
      console.log(productId);
      this._subscription.add(
        this._cartService.addToCart(productId)
          .subscribe(
          () => this._router.navigateByUrl('/cart'),
          (error) => console.log('error', error)
        )
      );

    }
  }


  ngOnDestroy(): void {
    this._subscription.unsubscribe();
    this._priceSubject$.complete();
    this._productPdpSubject.complete()
  }
}

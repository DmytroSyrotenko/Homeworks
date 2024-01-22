import {Component} from '@angular/core';
import {PlpService} from "../../services/plp.service";
import {Observable} from "rxjs";
import {ProductPlp} from "../../models/product-plp";
import {AsyncPipe, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-plp',
  standalone: true,
  imports: [
    AsyncPipe,
    JsonPipe,
    NgIf,
    NgForOf
  ],
  templateUrl: './plp.component.html',
  styleUrl: './plp.component.scss'
})
export class PlpComponent {

  products$: Observable<ProductPlp[]> = this._plpService.loadProducts();

  constructor(private _plpService: PlpService, private _router: Router) {
  }

  goToProductDetails(id: number) {
    this._router.navigateByUrl('/pdp/' + id);
  }
}


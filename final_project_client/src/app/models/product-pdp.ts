import {ProductVariant} from "./product-variant";

export interface ProductPdp {
  id: number;
  name: string;
  description: string;
  cpu: string;
  battery: string;
  camera: string;
  displayResolution: string;
  images: string[];
  variants: ProductVariant[];
}

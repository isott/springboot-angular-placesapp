import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SearchElement } from '../model/searchelement';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient: HttpClient) { }

  search(searchElement: SearchElement) {
    console.log(searchElement)
    return this.httpClient.get<any>('http://localhost:8070/apiv1/map/search?'
      + '&latitude=' + searchElement.latitude
      + '&longitude=' + searchElement.longitude
      + '&radius=' + searchElement.radius);
  }

}

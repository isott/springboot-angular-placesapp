import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SearchElement } from '../model/searchelement';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient: HttpClient) { }

  // http://localhost:8070
  hostUrl = environment.hostUrl;
  
  search(searchElement: SearchElement) {
    console.log(searchElement)
    return this.httpClient.get<any>(this.hostUrl + '/apiv1/map/nearbysearch?'
      + '&latitude=' + searchElement.latitude
      + '&longitude=' + searchElement.longitude
      + '&radius=' + searchElement.radius);
  }

}

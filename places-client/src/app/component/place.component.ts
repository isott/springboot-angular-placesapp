import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { Loader } from '@googlemaps/js-api-loader';
import { HttpClient } from '@angular/common/http';
import { Place } from '../model/place';
import { SearchElement } from '../model/searchelement';

@Component({
  selector: 'app-test',
  templateUrl: './place.component.html',
  styleUrls: ['./place.component.css']
})
export class PlaceComponent implements OnInit {

  searchElement = new SearchElement('','','')

  loader = new Loader({
    apiKey: 'AIzaSyBKLinya4d-da_FlDQRXEdRjNI4PHVcCAA'
  })


  constructor(private httpClientService: HttpClientService, private httpClient: HttpClient) { }

  onSubmit(): void {
    console.log('maps loading ...')

    let place: Place[] = [];

    console.log(this.searchElement)
    this.httpClientService.search(this.searchElement).subscribe(e => {

      place = e;

      
      this.loader.load().then(() => {
        let map = new google.maps.Map(
          document.getElementById("map") as HTMLElement, {
            center: { lat: parseFloat(this.searchElement.latitude), lng: parseFloat(this.searchElement.longitude) },
            zoom: 17,
            mapTypeId: "terrain"
        })

        let marker = new google.maps.Marker;
        place.forEach(p => {
          marker = new google.maps.Marker({
            position: { lat: parseFloat(p.lat), lng: parseFloat(p.lng) },
            title: p.lat + ", " + p.lng
          })

        marker.setMap(map)
        });
      })
    });
  }

  ngOnInit(): void {

    this.loader.load().then(() => {
      new google.maps.Map(
        document.getElementById("map") as HTMLElement, {
          center: { lat: 38.963745 , lng: 35.243322 },
          zoom: 4,
          mapTypeId: "terrain"
      })
    })


  }
}

import { Injectable } from '@angular/core';
import axios from 'axios';
import { config } from '../app/config'

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor() { }

  async getData () {
    return await axios.get(config.backend.host + '/api/data', {
      auth: config.backend.auth
    })
  }
}

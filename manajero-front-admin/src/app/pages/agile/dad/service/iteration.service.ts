import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IterationService {

  apiurl = 'http://localhost:8089/manajero/iterations';

  constructor(private http: HttpClient) { }

  public add(iteration: any, projectId: string): Observable<any> {
    return this.http.post<any>(`${this.apiurl}/add`, iteration, {
      params: { idProject: projectId }
    });
  }

  public getAll(id): Observable<any> {
    return this.http.get<any>(`${this.apiurl}/getAll/${id}`);
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class FeatureService {

    apiurl = 'http://localhost:8089/manajero/features';

    constructor(private http: HttpClient) { }

    public add(feature: any, projectId: string): Observable<any> {
        return this.http.post<any>(`${this.apiurl}/add`, feature, {
            params: { idProject: projectId }
        });
    }

    public getAll(id): Observable<any> {
        return this.http.get<any>(`${this.apiurl}/getAll/${id}`);
    }

    public delete(id: string): Observable<any> {
        return this.http.delete<any>(`${this.apiurl}/delete/${id}`);
    }

    public edit(feature): Observable<any> {
        return this.http.put<any>(`${this.apiurl}/edit`, feature);
    }

}

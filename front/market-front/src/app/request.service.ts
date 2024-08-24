import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse, HttpStatusCode } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from './model/Article';
import { User } from './model/User';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getArticleList(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.apiUrl}/article/list`);
  }

  getArticle(uuid: string): Observable<Article> {
    return this.http.get<Article>(`${this.apiUrl}/article/info/${uuid}`);
  }

  createArticle(article: Article): Observable<Article> {
    return this.http.post<Article>(`${this.apiUrl}/article/create`, article);
  }

  deleteArticle(uuid: string): Observable<HttpStatusCode> {
    return this.http.delete<HttpStatusCode>(`${this.apiUrl}/article/delete/${uuid}`);
  }

  updateArticle(uuid: string, article: Article): Observable<Article> {
    return this.http.patch<Article>(`${this.apiUrl}/article/update/${uuid}`, article);
  }

  login(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/user/login`, user);
  }

  updateProfile(uuid: string, user: User): Observable<User> {
    return this.http.patch<User>(`${this.apiUrl}/user/update/${uuid}`, user);
  }

  deleteUser(uuid: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/user/delete/${uuid}`);
  }

  register(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/user/register`, user);
  }

  getUser(uuid: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/user/info/${uuid}`);
  }
}

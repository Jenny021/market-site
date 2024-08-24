import { Component, OnInit } from '@angular/core';
import { Article } from '../model/Article';
import { RequestService } from '../request.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent implements OnInit {
  articles: Article[];

  constructor(private req : RequestService, private router: Router) {
    this.articles = [];
  }

  ngOnInit(): void {
    this.req.getArticleList().subscribe(res => {
      this.articles = res;
    });
  }

  redirect() {
    this.router.navigate(['/new']);
  }

  viewDetails(uuid : string) {
    if (uuid) {
      this.router.navigate(['/article', uuid]);
    }
  }
}

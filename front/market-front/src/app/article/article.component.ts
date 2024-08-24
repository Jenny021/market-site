import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Article } from '../model/Article';
import { RequestService } from '../request.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css'],
  standalone: true,
  imports: [FormsModule],
})
export class ArticleComponent implements OnInit {
  article: Article | null = null;
  uuid: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private req: RequestService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.uuid = params.get('id');
      this.loadArticle();
    });
  }

  loadArticle(): void {
    if (this.uuid) {
      this.req.getArticle(this.uuid).subscribe(res => {
        this.article = res;
      })
    }
  }

  save(): void {
    if (this.article && this.uuid) {
      this.req.updateArticle(this.uuid, this.article).subscribe(() =>
      this.router.navigate(['/home']));
    }
  }

  delete(): void {
    if (this.uuid) {
      this.req.deleteArticle(this.uuid).subscribe((res)=> {
      this.router.navigate(['/home']);
    });
    }
  }
}

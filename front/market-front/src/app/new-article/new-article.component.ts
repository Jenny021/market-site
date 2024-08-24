import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Article } from '../model/Article';
import { RequestService } from '../request.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-new-article',
  templateUrl: './new-article.component.html',
  styleUrls: ['./new-article.component.css'],
  standalone: true,
  imports: [FormsModule],
})
export class NewArticleComponent {
  article: Article = {
    uuid: '',
    name: '',
    cost: 0
  };

  constructor(private req: RequestService, private router: Router) {}

  save(): void {
    this.req.createArticle(this.article).subscribe((res) => {
      console.log('Article created');
      this.router.navigate(['/home']);
    });
  }
}

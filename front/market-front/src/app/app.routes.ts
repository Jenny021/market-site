import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { NewArticleComponent } from './new-article/new-article.component';
import { ArticleComponent } from './article/article.component';
import { RegisterComponent } from './register/register.component';
import { UserComponent } from './user/user.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    {path: 'login', component: LoginComponent},
    {path: 'home', component: HomepageComponent},
    {path: 'article/:id', component: ArticleComponent},
    {path: 'new', component: NewArticleComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'user', component: UserComponent},
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes),
        FormsModule,
        CommonModule,
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {}
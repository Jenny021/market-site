import { Component } from '@angular/core';
import { RequestService } from '../request.service';
import { User } from '../model/User';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [FormsModule],
})
export class LoginComponent {
  user: User = {
    uuid: '',
    email: '',
    password: ''
  };

  constructor(private req: RequestService, private router: Router) {
  }

  onSubmit() {
    if (this.user) {
      this.req.login(this.user).subscribe(() =>{
        this.router.navigate(['/home']);
      });
    }
  }

  redirect() {
    this.router.navigate(['/register']);
  }
}

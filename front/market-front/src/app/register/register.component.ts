import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RequestService } from '../request.service';
import { User } from '../model/User';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  standalone: true,
  imports: [FormsModule],
})
export class RegisterComponent {
  user: User = {
    uuid: '',
    email: '',
    password: ''
  };

  constructor(private req: RequestService, private router: Router) {}

  onSubmit(): void {
    this.req.register(this.user).subscribe(() => {
      console.log('User registered');
      this.router.navigate(['/home']);
    });
  }

  redirect() {
    this.router.navigate(['/login']);
  }
}

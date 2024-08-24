import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RequestService } from '../request.service';
import { User } from '../model/User';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-user',
  templateUrl: 'user.component.html',
  styleUrl: 'user.component.css',
  standalone: true,
  imports: [FormsModule],
})
export class UserComponent implements OnInit {
  user: User | null = null;
  isEditing: boolean = false;
  uuid: string | null = null;

  constructor(private route: ActivatedRoute, private req: RequestService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.uuid = params.get('id');
      if (this.uuid) {
        this.req.getUser(this.uuid).subscribe(user => this.user = user);
      }
    });
  }

  toggleEdit(): void {
    this.isEditing = !this.isEditing;
  }

  save(): void {
    if (this.user && this.uuid) {
      this.req.updateProfile(this.uuid, this.user).subscribe(() => {
        console.log('User updated');
        this.isEditing = false;
      });
    }
  }
}

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewArticleComponent } from './new-article.component';

describe('NewArticleComponent', () => {
  let component: NewArticleComponent;
  let fixture: ComponentFixture<NewArticleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewArticleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

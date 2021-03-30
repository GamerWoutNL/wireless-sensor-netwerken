import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimpleValueHolderComponent } from './simple-value-holder.component';

describe('SimpleValueHolderComponent', () => {
  let component: SimpleValueHolderComponent;
  let fixture: ComponentFixture<SimpleValueHolderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SimpleValueHolderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SimpleValueHolderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

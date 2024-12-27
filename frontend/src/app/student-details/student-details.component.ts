import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {StudentsService} from '../services/students.service';
import {Payment} from '../model/students.model';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrl: './student-details.component.css'
})
export class StudentDetailsComponent implements OnInit {
  studentCode!: string;
  studentsPayments! : Array<Payment>;
  paymentsDataSource! : MatTableDataSource<Payment>;
  public displayedColumns =['id','date','amount','type','status','firstName'];

  constructor(private ActivatedRoute: ActivatedRoute,
              private studentsService : StudentsService,
              private route:Router) {
  }

  ngOnInit(): void {
    this.studentCode = this.ActivatedRoute.snapshot.params['code']
    this.studentsService.getStudentPayments(this.studentCode).subscribe({
      next: value => {
        this.studentsPayments=value;
        this.paymentsDataSource=new MatTableDataSource<Payment>(this.studentsPayments)
      },
      error : err => {
        console.log(err);
      }
    });
  }

  newPayment() {
    this.route.navigateByUrl("/admin/new-payment/"+this.studentCode)
  }
}

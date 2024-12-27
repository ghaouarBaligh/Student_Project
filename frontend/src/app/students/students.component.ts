import {Component, OnInit} from '@angular/core';
import {StudentsService} from '../services/students.service';
import {MatTableDataSource} from '@angular/material/table';
import {Student} from '../model/students.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrl: './students.component.css'
})
export class StudentsComponent implements OnInit{

  public students! : Array<Student>;
  public studentsDataSource! : MatTableDataSource<Student>;
  displayedColumns : string[] = ['id','firstName','lastName','code','programId','payments']

  constructor(private studentService : StudentsService, private router:Router) {

  }

  ngOnInit(): void {
    this.studentService.getAllStudents()
      .subscribe({
        next: data => {
          this.students = data;
          this.studentsDataSource=new MatTableDataSource<Student>(this.students);
        },
        error:err => {
          console.log(err);
        }
      })
  }

  studentPayments(student: Student) {
    this.router.navigateByUrl("/admin/student-details/"+student.code)
  }


}

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { HomepageComponent } from './homepage/homepage.component';

import { MatIconModule } from '@angular/material/icon';
import { FullCalendarModule } from '@fullcalendar/angular';
import { NavbarComponent } from './navbar/navbar.component';
import { CommonModule, DatePipe } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { AllCertificatesComponent } from './all-certificates/all-certificates.component';
import { CreateCertificateComponent } from './create-certificate/create-certificate.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateCertificateComponent,
    LoginComponent,
    HomeComponent,
    HomepageComponent,
    
    NavbarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatIconModule, 
    CommonModule,
    FullCalendarModule,
    MatTableModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-center', // Change this to the desired position
      // ... other options
    }),
    MatSlideToggleModule,
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }

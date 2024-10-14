import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HomepageComponent } from './homepage/homepage.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CreateCertificateComponent } from './create-certificate/create-certificate.component';
import { AllCertificatesComponent } from './all-certificates/all-certificates.component';

const routes: Routes = [
  {path:'login', component:LoginComponent},
  {path:'', component:HomeComponent},
  {path:'homepage', component:HomepageComponent},
  { path: 'navbar', component: NavbarComponent},
  {path: 'create-certificate', component:CreateCertificateComponent},
  {path: 'all-certificates', component:AllCertificatesComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

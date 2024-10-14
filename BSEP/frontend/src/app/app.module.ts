import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { NgxCaptchaModule } from 'ngx-captcha';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ButtonsComponent } from './buttons/buttons.component';
import { HeaderComponent } from './header/header.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { WelcomeContentComponent } from './welcome-content/welcome-content.component';
import { AuthContentComponent } from './auth-content/auth-content.component';
import { ContentComponent } from './content/content.component';
import { HttpClientModule } from '@angular/common/http'; // Dodajte ovo

import { AxiosService } from './axios.service';
import { ConfirmRegistrationComponent } from './confirm-registration/confirm-registration.component';
import { CaptchaComponent } from './captcha/captcha.component';

@NgModule({
  declarations: [
    AppComponent,
    ButtonsComponent,
    HeaderComponent,
    LoginFormComponent,
    WelcomeContentComponent,
    AuthContentComponent,
    ContentComponent,
    ConfirmRegistrationComponent,
    CaptchaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxCaptchaModule
  ],
  providers: [AxiosService],
  bootstrap: [AppComponent]
})
export class AppModule { }

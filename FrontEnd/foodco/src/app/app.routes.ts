import { Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { SearchPageComponent } from './components/search-page/search-page.component';
import { NewPostComponent } from './components/new-post/new-post.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { SignupComponent } from './components/signup/signup.component';
import { MainpageComponent } from './components/mainpage/mainpage.component';

export const routes: Routes = [
    
    
   
    {path: 'login', component: LoginPageComponent},
    {path: 'signup', component: SignupComponent},
    {path: 'mainpage', component: MainpageComponent, children: [
        {path: 'homepage', component: HomePageComponent},
        {path: 'search', component: SearchPageComponent},
        {path: 'new-post', component: NewPostComponent},
    ]}
];

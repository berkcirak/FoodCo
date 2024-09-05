import { Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { SearchPageComponent } from './components/search-page/search-page.component';
import { NewPostComponent } from './components/new-post/new-post.component';
import { LoginPageComponent } from './components/login-page/login-page.component';

export const routes: Routes = [
    {path: 'login', component: LoginPageComponent},
    {path: 'post/list', component: HomePageComponent},
    {path: 'search', component: SearchPageComponent},
    {path: 'new-post', component: NewPostComponent}

];

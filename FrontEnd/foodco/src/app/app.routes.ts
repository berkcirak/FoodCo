import { Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { SearchPageComponent } from './components/search-page/search-page.component';
import { NewPostComponent } from './components/new-post/new-post.component';

export const routes: Routes = [
    {path:'', component: HomePageComponent},
    {path:'search', component: SearchPageComponent},
    {path:'new-post', component: NewPostComponent}

];

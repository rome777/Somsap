// Modules
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HttpModule} from '@angular/http';

// Comments
import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {MainComponent} from './main/main.component';
import {WhoComponent} from './who/who.component';
import {BoardComponent} from './board/board.component';
import { LoginComponent } from './auth/login/login.component';
import { JoinComponent } from './auth/join/join.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainComponent,
    WhoComponent,
    BoardComponent,
    LoginComponent,
    JoinComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path: '',
        component: MainComponent
      },
      {
        path: 'who',
        component: WhoComponent
      },
      {
        path: 'board',
        component: BoardComponent
      },
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'join',
        component: JoinComponent
      }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

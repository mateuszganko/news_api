import { Component, OnInit} from '@angular/core';
import { NewsControllerService } from '../api/services/news-controller.service'
import {NewsDto} from "../api/models/news-dto";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private newsControllerService: NewsControllerService) { }

  public news: NewsDto
  public categories:string[] = ["business", "entertainment", "general", "health", "science", "sports", "technology"];
  private selectedCategory:string = "technology";

  public countries:string[] = ["pl","en"];
  public selectedCountry:string = "pl";

  ngOnInit() {
     this.reloadNews();
  }

  public reloadNews(){
    console.log("Reload news");

    var param = {} as NewsControllerService.GetNewsByCountryAndCategoryUsingGETParams;
    param.category = this.selectedCategory;
    param.country = this.selectedCountry;
    param.page = 0;
    param.pageSize = 20;

    this.newsControllerService.getNewsByCountryAndCategoryUsingGET(param).subscribe(
      value => {
        setTimeout(() => this.news = value )
      },
      err => { console.error(JSON.stringify(err))},

      () =>  console.log("Request complete")
    );
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-message-page',
  templateUrl: './message-page.component.html',
  styleUrls: ['./message-page.component.scss']
})
export class MessagePageComponent implements OnInit {
  msg = ""
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams      
      .subscribe(params => {      
        this.msg = params.msg;        
      });
  }

}

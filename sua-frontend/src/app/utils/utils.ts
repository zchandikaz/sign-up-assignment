import { HttpHeaders } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { DeprecatedI18NPipesModule } from '@angular/common';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';



export class Utils{
    static title:String = "Sign Up Assignment";    
    static AUTH_URL:String = "http://localhost:8080/api/auth/";

    public static addExtraHeaders(h: string | { [name: string]: string | string[]; }): string | { [name: string]: string | string[]; }{
        h['Access-Control-Allow-Headers'] = 'Content-Type, Authorization, Origin, Accept';
        h['Access-Control-Allow-Methods'] = 'GET, POST, OPTIONS';
        h['Access-Control-Allow-Origin'] = 'http://localhost:4200';
        h['Access-Control-Allow-Credentials'] = 'true';        
        return h;
    }

    public static addExtraHttpHeaders(h: HttpHeaders | { [header: string]: string | string[]; }): HttpHeaders | { [header: string]: string | string[]; }{
        h['Access-Control-Allow-Headers'] = 'Content-Type, Authorization, Origin, Accept';
        h['Access-Control-Allow-Methods'] = 'GET, POST, OPTIONS';
        h['Access-Control-Allow-Origin'] = 'http://localhost:4200';
        h['Access-Control-Allow-Credentials'] = 'true';   
        return h;
    }    

    
    static alertDialog(dialog: MatDialog, content: String, onOk:Function = null){
        dialog.closeAll();
        dialog.open(ConfirmDialogComponent, {
            data: {
                content: content,
                title: this.title,
                btn1Text: "Ok",
                btn2Text: "",
                onBtn1: this.replaceNull(onOk, ()=>{}),
                onBtn2: ()=>{}
            }
        });
    }

    static replaceNull(v:any, d:any):any{
        return v==null?d:v;
    }

    static log(message?: any, ...optionalParams: any[]){
        console.log(message);
    }

    static enumToArray(o: object): Array<string> {
        var keys = Object.keys(o);
        return keys.slice(keys.length / 2);
    }   
    
    static parseDate(date:string){
        return (new Date(date.slice(0,10))).toLocaleDateString()
    }

    static inDateRange(date:Date, from:Date, to:Date):boolean{
        return Utils.isDatesEquals(date, from) || Utils.isDatesEquals(date, to) || (from>date && to<date);
        
    }

    static isDatesEquals(d1:Date, d2:Date):boolean{
        return d1.getFullYear()==d2.getFullYear() && d1.getMonth()==d2.getMonth() && d1.getDay()==d2.getDay();
    }    
}

export class LocalStorage{
    static set(keyName:string, value:any){
        localStorage.setItem(keyName, JSON.stringify(value));
    }  
    
    static get(keyName:string, defaultVal:any = ""){
        let v = localStorage.getItem(keyName);
        if(v==null)
            return defaultVal;
        else
            return JSON.parse(v);
    }  
}
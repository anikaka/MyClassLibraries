package com.tongyan.utils;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class FileOpen {

	
	   //Android获取一个用于打开APK文件的intent  
    public static Intent getAllIntent( String param ) {  
  
        Intent intent = new Intent();    
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    
        intent.setAction(android.content.Intent.ACTION_VIEW);    
        Uri uri = Uri.fromFile(new File(param ));  
        intent.setDataAndType(uri,"*/*");   
        return intent;  
    }  
    //Android获取一个用于打开APK文件的intent  
    public static Intent getApkFileIntent( File file ) {  
  
        Intent intent = new Intent();    
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    
        intent.setAction(android.content.Intent.ACTION_VIEW);    
        Uri uri = Uri.fromFile(file);  
        intent.setDataAndType(uri,"application/vnd.android.package-archive");   
        return intent;  
    }  
  
    //Android获取一个用于打开VIDEO文件的intent  
    public static Intent getVideoFileIntent( String param ) {  
  
        Intent intent = new Intent("android.intent.action.VIEW");  
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
        intent.putExtra("oneshot", 0);  
        intent.putExtra("configchange", 0);  
        Uri uri = Uri.fromFile(new File(param ));  
        intent.setDataAndType(uri, "video/*");  
        return intent;  
    }  
  
    //Android获取一个用于打开AUDIO文件的intent  
    public static Intent getAudioFileIntent( String param ){  
  
        Intent intent = new Intent("android.intent.action.VIEW");  
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
        intent.putExtra("oneshot", 0);  
        intent.putExtra("configchange", 0);  
        Uri uri = Uri.fromFile(new File(param ));  
        intent.setDataAndType(uri, "audio/*");  
        return intent;  
    }  
  
    //Android获取一个用于打开Html文件的intent     
    public static Intent getHtmlFileIntent( String param ){  
  
        Uri uri = Uri.parse(param ).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param ).build();  
        Intent intent = new Intent("android.intent.action.VIEW");  
        intent.setDataAndType(uri, "text/html");  
        return intent;  
    }  
  
    //Android获取一个用于打开图片文件的intent  
    public static Intent getImageFileIntent(File file ) {  
        Intent intent = new Intent("android.intent.action.VIEW");  
        intent.addCategory("android.intent.category.DEFAULT");  
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
        Uri uri = Uri.fromFile(file);  
        intent.setDataAndType(uri, "image/*");  
        return intent;  
    }  
  
    //Android获取一个用于打开PPT文件的intent     
    public static Intent getPptFileIntent( String param ){    
        Intent intent = new Intent("android.intent.action.VIEW");     
        intent.addCategory("android.intent.category.DEFAULT");     
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     
        Uri uri = Uri.fromFile(new File(param ));     
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");     
        return intent;     
    }     
  
    //Android获取一个用于打开Excel文件的intent     
    public static Intent getExcelFileIntent(File file){    
  
        Intent intent = new Intent("android.intent.action.VIEW");     
        intent.addCategory("android.intent.category.DEFAULT");     
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     
        Uri uri = Uri.fromFile(file);     
        intent.setDataAndType(uri, "application/vnd.ms-excel");     
        return intent;     
    }     
  
    //Android获取一个用于打开Word文件的intent     
    public static Intent getWordFileIntent(File file){    
  
        Intent intent = new Intent("android.intent.action.VIEW");     
        intent.addCategory("android.intent.category.DEFAULT");     
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     
        Uri uri = Uri.fromFile(file);     
        intent.setDataAndType(uri,"application/msword");//    
        return intent;     
    }     
  
    //Android获取一个用于打开CHM文件的intent     
    public static Intent getChmFileIntent( String param ){     
  
        Intent intent = new Intent("android.intent.action.VIEW");     
        intent.addCategory("android.intent.category.DEFAULT");     
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     
        Uri uri = Uri.fromFile(new File(param ));     
        intent.setDataAndType(uri, "application/x-chm");     
        return intent;     
    }     
  
    //Android获取一个用于打开文本文件的intent     
    
    public static Intent getTextFileIntent( String param, boolean paramBoolean){     
        Intent intent = new Intent("android.intent.action.VIEW");     
        intent.addCategory("android.intent.category.DEFAULT");     
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     
        if (paramBoolean){     
            Uri uri1 = Uri.parse(param);     
            intent.setDataAndType(uri1, "text/plain");     
        }else{     
            Uri uri2 = Uri.fromFile(new File(param));     
            intent.setDataAndType(uri2, "text/plain");     
        }     
        return intent;     
    }  
    public static Intent getTextFileIntent(File file){     
    	Intent intent = new Intent("android.intent.action.VIEW");     
    	intent.addCategory("android.intent.category.DEFAULT");     
    	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     
     	Uri uri = Uri.fromFile(file);     
        intent.setDataAndType(uri, "text/plain");     
    	return intent;     
    }  
    
    //Android获取一个用于打开PDF文件的intent     
    public static Intent getPdfFileIntent(File file){     
        Intent intent = new Intent("android.intent.action.VIEW");     
        intent.addCategory("android.intent.category.DEFAULT");     
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     
        Uri uri = Uri.fromFile(file);     
        intent.setDataAndType(uri, "application/pdf");     
        return intent;     
    }  
    
    
}

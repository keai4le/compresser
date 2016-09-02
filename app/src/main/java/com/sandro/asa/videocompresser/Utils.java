package com.sandro.asa.videocompresser;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by wy on 16-9-3.
 */
public class Utils {
    public static String getPathFromUri(Context context,Uri uri){
        if(uri==null) return null;
        final String scheme = uri.getScheme();
        String data=null;
        if(scheme==null)
            data=uri.getPath();
        else if(scheme.equals(ContentResolver.SCHEME_FILE)){
            data=uri.getPath();
        }else if(scheme.equals(ContentResolver.SCHEME_CONTENT)){
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Video.Media.DATA},null,null,null);
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    data=cursor.getString(0);
                }
            }
        }
        return data;
    }
    public static void getFile(Activity ac){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        ac.startActivityForResult(intent,123);
    }
}

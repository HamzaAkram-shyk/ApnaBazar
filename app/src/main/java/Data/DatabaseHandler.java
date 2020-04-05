package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="contactsDB";
    public static final String TABLE_NAME="contacts";
    public static final String KEY_ID ="ID";
    public static final String KEY_NAME ="NAME";
    public static final String KEY_PHONE_NUMBER ="NUMBER";

    public DatabaseHandler(Context context ) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
      //  String CREATE_CONTACT_TABLE= "CREATE TABLE contacts(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,phoneNumber TEXT)";

                db.execSQL(
                        "create table contacts " +
                                "(ID integer primary key,NAME text,NUMBRE text)"
                );
    }

//     db.execSQL(
//             "create table contacts " +
//             "(id integer primary key,name text,phone text,email text,street text,place text)"
//             );

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS contacts");
         onCreate(db);
    }

    public boolean addContact(String Name,String Phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues  values= new ContentValues();
        values.put("NAME",Name);
        values.put("NUMBER",Phone);
       long result= db.insert("contacts",null,values);
        //db.close();
        if(result==-1){

       return false ;} else{
         return true ;
        }
        }
    public Cursor getAllContacts(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Util.TABLE_NAME,null);



    return cursor;
    }

    public Contact getContact(int id){
//        SQLiteDatabase db= this.getReadableDatabase();
//         Cursor cursor=db.query(Util.TABLE_NAME,new String[]{Util.KEY_ID,
//                 Util.KEY_NAME,Util.KEY_PHONE_NUMBER}, Util.KEY_ID+ "=?", new String[]{String.valueOf(id)},null, null, null, null);
//                 if(cursor!=null)
//                     cursor.moveToFirst();
//                     Contact contact=new Contact(Integer.parseInt(cursor.getColumnName(0)),cursor.getColumnName(1),cursor.getColumnName(2));
//                     return contact ;
        SQLiteDatabase db=this.getWritableDatabase();
        String selectAll ="SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor=db.rawQuery(selectAll,null);

        while (cursor.moveToNext()){
            if(Integer.parseInt(cursor.getString(0))==id){
                Contact contact=new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(1));
                 return  contact;
            }
        }

                  return  new Contact();  }
}

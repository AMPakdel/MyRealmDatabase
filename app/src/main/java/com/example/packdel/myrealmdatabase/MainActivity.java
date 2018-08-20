package com.example.packdel.myrealmdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import Realm.MyRealm;
import RealmObjects.Person;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public static void log(String log){
        Log.i("AMPulator :", log);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyRealm myRealm = new MyRealm(getApplicationContext());

        /*myRealm.Create_Person(11892, "amir", 20, "nora", "mandy", "jupoo");

        myRealm.Create_Person(34501, "hamid", 22, "lira");

        myRealm.Create_Person(45663, "ehsan", 20, "semandra");*/

        //myRealm.Create_Person(21125, "amir", 12,"puffy");

        RealmResults<Person> persons = myRealm.Read_Person("amir");

        log(String.valueOf(persons.toArray().length));

        for(Person p : persons){

            log(p.getName());
        }
    }
}

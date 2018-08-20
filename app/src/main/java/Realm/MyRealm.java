package Realm;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.Arrays;

import RealmObjects.Person;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MyRealm {

    private Realm realm;

    public MyRealm(Context context){

        Realm.init(context);

        realm = Realm.getDefaultInstance();
    }

    public void Create_Person(int id, String name, int age, String ... dogs){

        realm.beginTransaction();

        Person user = realm.createObject(Person.class, id); // Create a new object

        user.setName(name);

        user.setAge(age);

        RealmList<String> realmList = new RealmList<>();

        // foreach arg in args add arg into the list
        realmList.addAll(Arrays.asList(dogs));

        user.setDogs(realmList);

        realm.commitTransaction();

    }

    public void Create_Person_Async(final int id, final String name, final int age, @Nullable Realm.Transaction.OnSuccess onSuccess, @Nullable Realm.Transaction.OnError onError, final String... dogs){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Person user = realm.createObject(Person.class, id); // Create a new object

                user.setName(name);

                user.setAge(age);

                RealmList<String> realmList = new RealmList<>();

                // foreach arg in args add arg into the list
                realmList.addAll(Arrays.asList(dogs));

                user.setDogs(realmList);

            }
        } , onSuccess, onError);
    }

    public RealmResults<Person> Read_Person(String ... names){

        RealmQuery<Person> query = realm.where(Person.class);

        for(int i=0; i<names.length; i++){

            query.equalTo("name", names[i]);

            if(i != names.length -1){

                query.or();
            }
        }

        RealmResults<Person> result = query.findAll();

        return result;
    }

    // TODO:: have no idea about Read_Person_Async :\

    public void Update_Person(final int id, final String new_name){

        Person user = realm.where(Person.class).equalTo("id", id).findFirst();

        user.setName(new_name);
    }

    public void Update_Person_Async(final int id, final String new_name, @Nullable Realm.Transaction.OnSuccess onSuccess, @Nullable Realm.Transaction.OnError onError){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Person user = realm.where(Person.class).equalTo("id", id).findFirst();

                user.setName(new_name);
            }
        }, onSuccess, onError);
    }

    public void Delete_Person(String name){

        RealmResults<Person> result = realm.where(Person.class).equalTo("name", name).findAll();

        result.deleteAllFromRealm();// or result.clear()
    }

    public void Delete_Person_Async(final String name, @Nullable Realm.Transaction.OnSuccess onSuccess, @Nullable Realm.Transaction.OnError onError){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                RealmResults<Person> result = realm.where(Person.class).equalTo("name", name).findAll();

                result.deleteAllFromRealm();// or result.clear()
            }
        }, onSuccess, onError);
    }

}

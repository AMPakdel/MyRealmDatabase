package RealmObjects;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Person extends RealmObject{

    @PrimaryKey
    private int id;

    private String name;

    private int age;

    private RealmList<String> dogs;

    public Person() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public RealmList<String> getDogs() {
        return dogs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDogs(RealmList<String> dogs) {
        this.dogs = dogs;
    }
}
package models;

import io.ebean.Model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModelWithStringId extends Model {
    @Id
    public String id = null;

    public static boolean notSaved(String id) {
        return id == null;
    }

    public static boolean isIdValid(String id) {
        return !notSaved(id) && (!id.equals(""));
    }
}

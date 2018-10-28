package models;

import io.ebean.Model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel extends Model {
    @Id
    public Long id;

    public static boolean notSaved(Long id) {
        return id == null;
    }

    public static boolean isIdValid(Long id) {
        return !notSaved(id) && (id > -1L);
    }
}

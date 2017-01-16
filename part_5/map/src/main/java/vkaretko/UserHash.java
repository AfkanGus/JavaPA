package vkaretko;

import java.util.Calendar;

/**
 * Model UserHash for creating Users with overrided hashcode.
 * @author Karetko Victor
 * @version 1.00
 * @since 24.12.2016
 */
public class UserHash extends User {
    /**
     * Constructor of UserHash.
     * @param name user name.
     * @param children amount of childrens.
     * @param birthday birthday.
     */
    public UserHash(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    /**
     * Override hashcode method, generated by idea.
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        final int multiplier = 31;
        int result = getName() != null ? getName().hashCode() : 0;
        result = multiplier * result + getChildren();
        result = multiplier * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        return result;
    }
}
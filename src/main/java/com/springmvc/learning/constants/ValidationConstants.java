package com.springmvc.learning.constants;

public class ValidationConstants {

    public static final String FIRST_LAST_NAME_PATTERN = "(?i)(^[a-zA-Zàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûśüųūÿýżźñçčšž" +
            "ÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹŚÑßÇŒÆČŠŽ∂ð])((?![ .,'-]$)" +
            "[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýśżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹŚÑßÇŒÆČŠŽ∂ð ,.'-])" +
            "{2,24}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$";
    public static final String USERNAME_PATTERN = "[a-zA-Z0-9]{3,}";

    public static final String PASSWORD_PATTERN_VIOLATION = "Minimum 5 symbols with at " +
            "least 1 special symbol, 1 upper letter and 1 digit";

    public static final String PASSWORD_EQUALITY_VIOLATION = "Passwords don't match";

    public static final String PASSWORD_EMPTY_FIELD_VIOLATION = "Both fields should be filled";

}

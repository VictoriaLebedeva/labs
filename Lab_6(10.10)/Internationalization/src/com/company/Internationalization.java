package com.company;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {

    private static Locale locale = Locale.getDefault();
    static Locale getLocale() {
        return Internationalization.locale;
    }
    static void setLocale( Locale locale ) {
        Internationalization.locale = locale;
        result = ResourceBundle.getBundle( "resource", locale);
    }

    private static  ResourceBundle result = ResourceBundle.getBundle( "resource", Locale.getDefault());
    static ResourceBundle getBundle() {
        return Internationalization.result;
    }
    static String getString( String key ) {
        return Internationalization.result.getString(key);
    }

    protected static final String Tree = "Tree";
    protected static final String age = "age";
    protected static final String type = "type";
    protected static final String ID = "ID";
    protected static final String fructification = "fructification";
    protected static final String creation = "creation";
    protected static final String APPLE = "APPLE";
    protected static final String CHERRY = "CHERRY";
    protected static final String PEAR = "PEAR";
    protected static final String PLUM = "PLUM";
    protected static final String status = "status";
    protected static final String REPLANT = "REPLANT";
    protected static final String KEEP = "KEEP";
    protected static final String garden = "garden";
}

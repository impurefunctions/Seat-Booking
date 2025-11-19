package com.university.soa.bus;

/**
 * Represents the data model for an employee.
 * This class holds the employee's name, code, phone number, and password.
 */
public class Addata {
    /**
     * The name of the employee.
     */
    String Ename;
    /**
     * The employee's unique code.
     */
    String Ecode;
    /**
     * The employee's phone number.
     */
    String Ephone;
    /**
     * The employee's password.
     */
    String Epass;

    /**
     * Gets the name of the employee.
     *
     * @return The employee's name.
     */
    public String getEname() {
        return Ename;
    }

    /**
     * Sets the name of the employee.
     *
     * @param ename The employee's name.
     */
    public void setEname(String ename) {
        Ename = ename;
    }

    /**
     * Gets the employee's code.
     *
     * @return The employee's code.
     */
    public String getEcode() {
        return Ecode;
    }

    /**
     * Sets the employee's code.
     *
     * @param ecode The employee's code.
     */
    public void setEcode(String ecode) {
        Ecode = ecode;
    }

    /**
     * Gets the employee's phone number.
     *
     * @return The employee's phone number.
     */
    public String getEphone() {
        return Ephone;
    }

    /**
     * Sets the employee's phone number.
     *
     * @param ephone The employee's phone number.
     */
    public void setEphone(String ephone) {
        Ephone = ephone;
    }

    /**
     * Gets the employee's password.
     *
     * @return The employee's password.
     */
    public String getEpass() {
        return Epass;
    }

    /**
     * Sets the employee's password.
     *
     * @param epass The employee's password.
     */
    public void setEpass(String epass) {
        Epass = epass;
    }

    /**
     * Constructs a new Addata object.
     *
     * @param name   The name of the employee.
     * @param code   The employee's code.
     * @param number The employee's phone number.
     * @param pass   The employee's password.
     */
    public Addata(String name, String code, String number, String pass) {
        this.Ecode = code;
        this.Ename = name;
        this.Epass = pass;
        this.Ephone = number;
    }
}

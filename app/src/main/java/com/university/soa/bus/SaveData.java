package com.university.soa.bus;

/**
 * Represents the data model for saving employee information.
 * This class holds the employee's name and code.
 */
public class SaveData {
    /**
     * The name of the employee.
     */
    String name;
    /**
     * The employee's unique code.
     */
    String empcode;

    /**
     * Default constructor required for Firebase.
     */
    public SaveData() {
        //Required for firebase
    }

    /**
     * Gets the name of the employee.
     *
     * @return The employee's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the employee's code.
     *
     * @return The employee's code.
     */
    public String getEmpcode() {
        return empcode;
    }

    /**
     * Sets the name of the employee.
     *
     * @param name The employee's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the employee's code.
     *
     * @param empcode The employee's code.
     */
    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    /**
     * Constructs a new SaveData object.
     *
     * @param name    The name of the employee.
     * @param empcode The employee's code.
     */
    public SaveData(String name, String empcode) {
        this.name = name;
        this.empcode = empcode;
    }
}

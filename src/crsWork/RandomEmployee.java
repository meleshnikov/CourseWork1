package crsWork;

import java.util.Random;


public final class RandomEmployee {
    static final Random random = new Random();

    public static int generateRandomInt(int min, int max) {
        max++;
        max -= min;
        return random.nextInt(max) + min;
    }

    public static String generateRandomString(String[] strs) {
        int index = generateRandomInt(0, strs.length - 1);
        return strs[index];
    }

    public static Employee genRandomEmployee() {
        String fName;
        String mName;
        String lName;

        if (random.nextBoolean()){
            fName = generateRandomString(Names.getMaleFirstNames());
            mName = generateRandomString(Names.getMaleMiddleNames());
            lName = generateRandomString(Names.getMaleLastNames());
        } else {
            fName = generateRandomString(Names.getFemaleFirstNames());
            mName = generateRandomString(Names.getFemaleMiddleNames());
            lName = generateRandomString(Names.getFemaleLastNames());
        }

        int department = generateRandomInt(1, 5);
        double salary = (double) generateRandomInt(80_000, 82_000);
        Employee employee = new Employee(fName, mName, lName, department, salary);
        return employee;
    }


}

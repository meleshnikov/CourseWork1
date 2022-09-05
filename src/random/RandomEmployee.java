package random;

import employee.Employee;
import names.Names;

import java.util.Random;


public final class RandomEmployee {
    static final Random random = new Random();

    public static int generateRandomInt(int min, int max) {
        max++;
        max -= min;
        return random.nextInt(max) + min;
    }

    public static String getRandomName(String[] names) {
        int index = generateRandomInt(0, names.length - 1);
        return names[index];
    }

    public static Employee generateRandomEmployee() {
        String fName;
        String mName;
        String lName;

        if (random.nextBoolean()){
            fName = getRandomName(Names.getMaleFirstNames());
            mName = getRandomName(Names.getMaleMiddleNames());
            lName = getRandomName(Names.getMaleLastNames());
        } else {
            fName = getRandomName(Names.getFemaleFirstNames());
            mName = getRandomName(Names.getFemaleMiddleNames());
            lName = getRandomName(Names.getFemaleLastNames());
        }

        int department = generateRandomInt(1, 5);
        double salary = generateRandomInt(60_000, 150_000);

        return new Employee(fName, mName, lName, department, salary);
    }


}

import java.util.ArrayList;

public class Model {
    private final ArrayList<Person> people = new ArrayList<>();
    private boolean isPeakSeason;
    private boolean childExists = false;
    private int singleRoomNum = 0;

    public void addPerson(String name, int age, int liveWith) {
        if (age < 0) {
            throw new IllegalArgumentException("age must be greater than 0");
        }
        if (age < 18) {
            childExists = true;
            people.add(new Child(age, name, liveWith));
        } else if (age > 65) {
            people.add(new Old(age, name));
        } else {
            people.add(new Person(age, name));
        }
    }

    private int roomPrice(int number) {
        if (childExists) {
            return (isPeakSeason ? 1600 : 1400) * number;
        } else {
            return 0;
        }
    }

    public int calculatePrice() {
        int finalPrice = 0;
        for (Person person : people) {
            finalPrice += person.getPrice();
        }
        finalPrice += roomPrice(this.singleRoomNum);
        if (people.size() > 2) {
            finalPrice += people.size() * (isPeakSeason ? 500 : 0);
            ArrayList<Person> outOfPortion = new ArrayList<>();
            for (Person person : people) {
                if (person.getAge() < 24 || person.getAge() > 65) {
                    outOfPortion.add(person);
                }
            }
            int difference = 2 * outOfPortion.size() - people.size();
            if (difference > 0) {
                finalPrice += difference * 300; // 超比例费用
            }
        }
        return finalPrice;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }


    public int getSingleRoomNum() {
        return singleRoomNum;
    }

    public void setSingleRoomNum(int singleRoomNum) {
        this.singleRoomNum = singleRoomNum;
    }


    public void setPeakSeason(boolean peakSeason) {
        isPeakSeason = peakSeason;
    }

}


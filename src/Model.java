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

    public long calculatePrice() {
        int finalPrice = 0;
        for (Person person : people) {
            finalPrice += person.getPrice();
        }
        finalPrice += roomPrice(this.singleRoomNum);
        if (people.size() > 2) {
            finalPrice += people.size() * (isPeakSeason ? 500 : 0);
            finalPrice += calculateOutOfPortion();
        }
        return finalPrice;
    }

    private int calculateOutOfPortion() {
        int outOfPortion = 0, res = 0;
        for (Person person : people) {
            if (person.getAge() < 24 || person.getAge() > 65) {
                outOfPortion++;
            }
        }
        int difference = 2 * outOfPortion - people.size();
        if (difference > 0) {
            res += difference * 300;
        }
        return res;
    }

    private long calculatePriceByPerson(int index) {
        Person person = people.get(index);
        long personPrice = 0;
        if (person instanceof Child) {
            if (((Child) person).getLiveWith() == 2) {
                personPrice += (isPeakSeason ? 1600 : 1400);
            }
        }
        if (people.size() > 2) {
            personPrice += (isPeakSeason ? 500 : 0);
        }
        personPrice += person.getPrice();
        return personPrice;
    }

    String getDetail() {
        StringBuilder detail = new StringBuilder();
        String season = isPeakSeason ? "旺季" : "淡季";
        detail.append("当前是 ").append(season).append("\n");
        for (int i = 0; i < people.size(); i++) {
            detail.append("旅客姓名：").append(people.get(i).getName()).append("   费用: ").append(calculatePriceByPerson(i)).append(" 元").append("\n");
        }
        int r = calculateOutOfPortion();
        if (r > 0 && people.size() > 2) {
            detail.append("超比例费用: ").append(r).append(" 元").append("\n");
        }
        detail.append("总计： ").append(calculatePrice()).append(" 元");
        return detail.toString();
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


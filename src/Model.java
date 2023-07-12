import java.util.ArrayList;
import java.util.Objects;

/*
一个成人的价格是1799，一个儿童不要床位的价格是1500，如果儿童需要床位则价格变为2099,
如果需要单间房则需要补淡季1400，旺季1600的单间房差价
除此之外65岁以上老人在成人基础上要加300元老人费用
然后上面就是基础套餐，两个人的套餐，
然后下面会新增超过两个人的要求，和在旺季情况下增加的费用

旺季情况下每个人都需要增加500元的旺季涨价费用

现在有个超比例费用概念：
因为优惠针对的是24到65岁的人，所以如果一个符合年龄段的人加上一个不符合年龄段的人我们称为不超比例，
也就是1:1（不超:超），我们不收取超比例费用，
如果超过这个比例，比如1:2（不超：超）我们便按超过一个人比例的费用多收取300元，
同理1:3便多收取600元，而2:1（不超：超）不收取超比例费用，你可以理解为一个符合年龄段的人自动可以同化一
个不符合年龄段的人，不符合年龄段的人额外要多收300元，但是由于之前有每个年龄段还有额外加的钱，
所以你不能写在一个代码里面，你要单独写出来，才能不跟前面的代码冲突。
 */
public class Model {
    private ArrayList<Person> people = new ArrayList<>();
    private int childNum;
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
            childNum++;

        } else if (age > 65) {
            people.add(new Old(age, name));
        } else {
            people.add(new Person(age, name));
        }
    }

    public Person deletePerson(Person target) {
        String name = target.getName();
        Person deleted = null;
        for (Person person : people) {
            if (Objects.equals(person.getName(), name)) {
                if (person instanceof Child) {
                    childNum--;
                }
                deleted = new Person(person.getAge(), person.getName());
                people.remove(person);
                break;
            }
        }
        childExists = childNum > 0;
        return deleted;
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

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public int getSingleRoomNum() {
        return singleRoomNum;
    }

    public void setSingleRoomNum(int singleRoomNum) {
        this.singleRoomNum = singleRoomNum;
    }

    public boolean isPeakSeason() {
        return isPeakSeason;
    }

    public void setPeakSeason(boolean peakSeason) {
        isPeakSeason = peakSeason;
    }
}


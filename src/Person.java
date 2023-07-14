
public class Person {
    private int age;
    private final String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        assert age > 0;
        this.age = age;
    }

    public int getPrice() {
        return 1799;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "姓名：" + name + "   年龄：" + age;
    }
}

class Child extends Person {
    private final int liveWith;

    public Child(int age, String name, int liveWith) {
        super(age, name);
        this.setAge(age);
        this.liveWith = liveWith;
    }


    @Override
    public void setAge(int age) {
        if (age < 18) {
            super.setAge(age);
        } else {
            throw new IllegalArgumentException("age must be less than 18");
        }
    }

    @Override
    public int getPrice() {
        return switch (liveWith) {
            case 1 -> 1500;// 不需要床位
            case 0 -> 2099;// 需要床位
            default -> 0;
        };
    }


    @Override
    public String toString() {
        String live = switch (liveWith) {
            case 1 -> "   不加床位";
            case 0 -> "   需要床位";
            default -> "   单间房";
        };
        return super.toString() + live;
    }

    public int getLiveWith() {
        return liveWith;
    }
}

class Old extends Person {
    public Old(int age, String name) {
        super(age, name);
        this.setAge(age);
    }

    @Override
    public void setAge(int age) {
        if (age > 65) {
            super.setAge(age);
        } else {
            throw new IllegalArgumentException("age must be greater than 65");
        }
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 300;
    }
}



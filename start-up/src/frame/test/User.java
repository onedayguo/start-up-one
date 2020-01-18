package frame.test;

import java.util.Objects;



public class User implements Comparable<User> {
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    public User(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        char[] temp = user.tel.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (!(temp[i] - '0' >=-9 && temp[i]-'0' <= 9))
            {
                System.out.println("号码中有非数字");
                return false;
            }

        }
        return tel.equals(user.tel) && name.toLowerCase().equals(user.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tel);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    private String tel;

    @Override
    public int compareTo(User o) {
        if (!o.getClass().isInstance(User.class)) return 0;
        if (this.getTel().equals(o.getTel())) return 1;
        return 0;
    }
}

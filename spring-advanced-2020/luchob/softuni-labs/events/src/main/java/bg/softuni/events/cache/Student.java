package bg.softuni.events.cache;

public class Student {
  private String name;
  private int age;

  public String getName() {
    return name;
  }

  public Student setName(String name) {
    this.name = name;
    return this;
  }

  public int getAge() {
    return age;
  }

  public Student setAge(int age) {
    this.age = age;
    return this;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}

package bg.softuni.errors.rest;


public class Student {
  private int age;
  private String name;

  public int getAge() {
    return age;
  }

  public Student setAge(int age) {
    this.age = age;
    return this;
  }

  public String getName() {
    return name;
  }

  public Student setName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public String toString() {
    return "Student{" +
        "age=" + age +
        ", name='" + name + '\'' +
        '}';
  }
}

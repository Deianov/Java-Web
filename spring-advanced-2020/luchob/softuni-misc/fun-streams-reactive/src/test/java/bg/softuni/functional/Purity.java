package bg.softuni.functional;

public class Purity {

  public int pureFunction(int x, int y) {
    return x + y;
  }

  private int v;

  public int nonPureFunction(int x, int y) {
    v = x + y + v;
    return pureFunction(x, y);
  }

  public void uselessFunction(int x, int y) {
    int z = x + y;
  }

  public int uselessFunction2() {
    return 10;
  }

}

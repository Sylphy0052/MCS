import java.util.*;

public class Main {
  public static long time;
  public static void main(String[] args) {
    Random rand = new Random();
    ArrayList<Position> ps = new ArrayList<Position>();
    for(int i=0;i<1000000;i++) {
      Position p = new Position(rand.nextInt(300), rand.nextInt(300), rand.nextInt(300));
      ps.add(p);
    }
    test1(ps);
    // test2(ps);
    // test3(ps);
  }

  // HashMap<Position, ArrayList>
  // 2772ms
  // ArrayList使うと5621ms
  private static void test1(ArrayList<Position> ps) {
    startTime();
    Grid1 g = new Grid1();
    ArrayList<Test> ts = new ArrayList<Test>();
    for(Position p: ps) {
      Test t = new Test(p);
      ts.add(t);
      g.add(t, p);
    }
    for(Test t: ts) {
      Test prevTest = t;
      t.move();
      g.add(t, t.getPosition());
      // g.remove(prevTest, prevTest.getPosition());
    }
    endTime();
  }

  // HashMap<String, ArrayList>
  // 9315ms
  // private static void test2(ArrayList<Position> ps) {
  //   startTime();
  //   Grid2 g = new Grid2();
  //   ArrayList<Test> ts = new ArrayList<Test>();
  //   for(Position p: ps) {
  //     Test t = new Test(p);
  //     ts.add(t);
  //     g.add(t, p.toHash());
  //   }
  //   for(Test t: ts) {
  //     Test prevTest = t;
  //     t.move();
  //     g.add(t, t.getPosition().toHash());
  //     g.remove(prevTest, prevTest.getPosition().toHash());
  //   }
  //   endTime();
  // }

  private static void test3(ArrayList<Position> ps) {
    startTime();
    Grid3 g = new Grid3();
    ArrayList<Test> ts = new ArrayList<Test>();
    for(Position p: ps) {
      Test t = new Test(p);
      ts.add(t);
    }
    for(Test t: ts) {
      t.move();
    }
    endTime();
  }

  public static class Position {
    public int x;
    public int y;
    public int z;

    public Position(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public String toHash() {
      return String.format("%03d%03d%03d", x, y, z);
    }

    public Position add(int x, int y, int z) {
      this.x += x;
      this.y += y;
      this.z += z;
      return new Position(x, y, z);
    }
  }

  public static class Test {
    private Position p;
    public Test(Position p) {
      this.p = p;
    }

    public void move() {
      Random r = new Random();
      Position nextPosition = p.add(r.nextInt(2) - 1, r.nextInt(2) - 1, r.nextInt(2) - 1);
      p = nextPosition;
    }

    public Position getPosition() {
      return p;
    }
  }

  public static class Grid1 {
    HashMap<Position, ArrayList<Test>> map = new HashMap<Position, ArrayList<Test>>();

    public void remove(Test t, Position p) {
      ArrayList<Test> ts = map.get(p);
      ts.remove(t);
    }

    public void add(Test t, Position p) {
      if (!map.containsKey(p)){
			  map.put(p, new ArrayList<Test>());
		  }
      map.get(p).add(t);
    }
  }

  // public static class Grid2 {
  //   HashMap<String, ArrayList<Test>> map = new HashMap<String, ArrayList<Test>>();
  //
  //   public void remove(Test t, String p) {
  //     ArrayList<Test> ts = map.get(p);
  //     ts.remove(ts.indexOf(t));
  //   }
  //
  //   public void add(Test t, String p) {
  //     if (!map.containsKey(p)){
	// 		  map.put(p, new ArrayList<Test>());
	// 	  }
  //     map.get(p).add(t);
  //   }
  // }
  //
  public static class Grid3 {
    Test[][][][] g = new Test[100][100][100][100];

    public void remove(Test t, Position p) {
      // ArrayList<Test> ts = map.get(p);
      // ts.remove(ts.indexOf(t));
      Test[] ts = g[p.x][p.y][p.z];
      // ts.
      // ts.remove(t);
    }

    public void add(Test t, Position p) {
      g[p.x][p.y][p.z].add(t);
      // if (!map.containsKey(p)){
			//   map.put(p, new ArrayList<Test>());
		  // }
      // map.get(p).add(t);
    }
  }

  public static void startTime() {
    time = System.currentTimeMillis();
  }

  public static void endTime() {
    System.out.println(String.format("%d ms", System.currentTimeMillis() - time));
  }
}

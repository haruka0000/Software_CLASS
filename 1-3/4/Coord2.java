public class Coord2{
  private double x, y;

  public double getX(){
    return this.x;
  }

  public double getY(){
    return this.y;
  }

  public void setCoord2(double x, double y){
    this.x = x;
    this.y = y;
  }

  public static double distance(Coord2 v1, Coord2 v2){  // 2点のユークリッド距離を返す
    return Math.sqrt(
                      Math.pow((v1.getX() - v2.getX()), 2)
                      + Math.pow((v1.getY() - v2.getY()), 2)
                    );
  }
}

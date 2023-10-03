public class MyCircle {
    int centerX;
    int centerY;
    int radius;
    String name;

    // Implement the constructor
    public MyCircle(){
        centerX = 0;
        centerY = 0;
        radius = 0;
        name = "";
    }

    public MyCircle(int centerX, int centerY, int radius, String name){
        centerX = this.centerX;
        centerY = this.centerY;
        radius = this.radius;
        name = this.name;
    }

    // Implement equals method
    public int getCenterX(){
        return centerX;
    }
    public int getCenterY(){
        return centerY;
    }
    public int getRadius(){
        return radius;
    }
    public String getName(){
        return name;
    }

    public void setCenterX(int centerX){
        centerX = this.centerX;
    }
    public void setCenterY(int centerY){
        centerY = this.centerY;
    }
    public void setRadius(int radius){
        radius = this.radius;
    }
    public void setName(String name){
        name = this.name;
    }

    // Check if two circles equal to each other
    public boolean equalTo (MyCircle x){
        if ((x.getCenterX() == centerX) && (x.getCenterY() == centerY) && (x.getRadius() == radius)){
            return true;
        }
        else{
            return false;
        }
    }

    // Implement the compareTo method
    public int compareTo(MyCircle x, MyCircle y){
        int radiusX = x.getRadius();
        int radiusY = y.getRadius();
        if (radiusX == radiusY){
            return 0;
        }
        else if (radiusX > radiusY){
            return 1;
        }
        else{
            return -1;
        }
    }

    // Compare the name and content of circle with another
    public String sameCircle(MyCircle x, MyCircle y){
        if (x.equalTo(y) && (x.getName().equals(y.getName()))){
            return "Same Circles";
        }
        else if (x.equalTo(y)){
            return "Similar Circles";
        }
        else{
            return "Different Circles";
        }
    }

}


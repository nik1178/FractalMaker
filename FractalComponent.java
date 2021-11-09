import java.awt.*;
public class FractalComponent{
    int x1;
    int x2;
    int y1;
    int y2;
    FractalComponent(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    Point returnStartPoint(){
        return new Point(x1,y1);
    }
    Point returnEndPoint(){
        return new Point(x2,y2);
    }
}
class OriginPoint extends FractalComponent{
    //This is the point from which we spawned the original shape. It is the starting ground for any other shapes drawn in the
    //Say we want to draw a fractal tree. The origin point would be at the bottom of the screen, a line drawn on top, then 2 continuation points from the top of the line
    OriginPoint(int x1, int y1){
        super(x1,y1,0,0);
    }
}
class FractalLine extends FractalComponent{
    //The actual shape that will be drawn. For now only line will be added
    FractalLine(int x1, int y1, int x2, int y2){
        super(x1,y1,x2,y2);
    }
}

class ContinuationPoint extends FractalComponent{
    //Where the fractal should continue from and the direction it should continue in
    ContinuationPoint(int x1, int y1, int x2, int y2){
        super(x1,y1,x2,y2);
    }
}
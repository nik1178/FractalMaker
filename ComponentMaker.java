import java.util.*;
public class ComponentMaker{
    //All the origin points, lines, continuation points...
    static int selectedComponent = 0; //Tells us what component the user is trying to draw
    static int currentComponent = 0; //Tells us the num of the last component in the component list(Which currently doesn't exist)
    int howManyComponents = 0;
    //ArrayList<FractalComponent> components = new ArrayList<>();
    OriginPoint originPoint;
    ArrayList<FractalLine> fractalLines = new ArrayList<>();
    ArrayList<ContinuationPoint> continuationPoints = new ArrayList<>();
    ComponentMaker(){

    }

    void addComponent(int x1, int y1, int x2, int y2){
        switch(selectedComponent){
            case 1:
                originPoint = new OriginPoint(x2, y2);
                break;
            case 2:
                fractalLines.add(new FractalLine(x1, y1, x2, y2));
                break;
            case 3:
                continuationPoints.add(new ContinuationPoint(x1, y1, x2, y2));
                break;
            default:
                break;
            
        }
        howManyComponents++;
        System.out.println("Test");
    }

}
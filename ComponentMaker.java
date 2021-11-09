import java.util.*;
public class ComponentMaker{
    //All the origin points, lines, continuation points...
    static int selectedComponent = 0;
    static int currentComponent = 0;
    ArrayList<FractalComponent> components = new ArrayList<>();
    ComponentMaker(){

    }

    void addComponent(int x1, int y1, int x2, int y2){
        switch(selectedComponent){
            case 1:
                components.add(new OriginPoint(x2, y2));
                break;
            case 2:
                components.add(new FractalLine(x1, y1, x2, y2));
                break;
            case 3:
                components.add(new ContinuationPoint(x1, y1, x2, y2));
                break;
            default:
                break;
            
        }
    }

}
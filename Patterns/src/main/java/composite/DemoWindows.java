package composite;

import java.util.ArrayList;
import java.util.List;

public class DemoWindows {
    public static void main(String[] args) {
        Layout horizontalLayoutObj = new Layout("Horizontal Layout");
        horizontalLayoutObj.addChild(new WallPaper("My Horizontal Wallpaper"));
        Layout layoutObj = new Layout("Vertical Layout");
        layoutObj.addChild(new WallPaper("My Vertical Wallpaper"));
        Window window = new Window("My window");
        window.addChild(horizontalLayoutObj);
        window.addChild(layoutObj);
        window.print();

    }
}


interface GraphicObject {
    default void print() {
        System.out.print("No op ");
    }
}

class AbstractListGraphicObject implements GraphicObject {
    protected List<GraphicObject> children = new ArrayList<>();

    public void addChild(GraphicObject obj) {
        this.children.add(obj);
    }

    @Override
    public void print() {
        children.forEach(x -> x.print());
    }
}


class Layout extends AbstractListGraphicObject {
    public Layout(String layoutType) {
        this.layoutType = layoutType;
    }

    private String layoutType;


    @Override
    public void print() {
        System.out.println("The Layout Type is " + layoutType);
        super.print();
    }
}

class Window extends AbstractListGraphicObject {
    public Window(String name) {
        this.windowName = name;
    }

    private String windowName;

    @Override
    public void print() {
        System.out.println("The Window name is : " + windowName);
        super.print();
    }
}

class WallPaper implements GraphicObject {
    private String wallpaperName;

    public WallPaper(String name) {
        this.wallpaperName = name;
    }

    @Override
    public void print() {
        System.out.println("Wallpaper name is  : " + wallpaperName);
    }
}




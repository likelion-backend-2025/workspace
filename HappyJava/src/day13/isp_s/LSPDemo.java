package day13.isp_s;

// 공통 인터페이스로 추상화
interface Shape {
    int area();
}

class Rectangle implements Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int area() {
        return width * height;
    }
}

class Square implements Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public int area() {
        return side * side;
    }
}

class LSPDemo {
    public static void main(String[] args) {
        Shape rect = new Rectangle(5, 10);
        System.out.println("Rectangle area: " + rect.area()); // 50

        Shape square = new Square(5);
        System.out.println("Square area: " + square.area()); // 25
    }
}
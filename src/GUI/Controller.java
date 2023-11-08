package GUI;

import Tests.Tester;
import Tree.GPS;
import Tree.Property;
import Tree.QuadTree;
import Tree.Shape;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Controller{

    private ArrayList<GPS> gpsList = new ArrayList<>();
    private ArrayList<Shape> treeShapeList = new ArrayList<Shape>();
    private ArrayList<Property> treePropertyList = new ArrayList<Property>();
    private ArrayList<Shape> treeShapeListForSearch = new ArrayList<Shape>();
    private ArrayList<Property> treePropertyListForSearch = new ArrayList<Property>();
    private QuadTree treeShape, treeProperty;

    private Tester tester = new Tester();


    public Controller() {

    }

    public void createTreeProperty(int width, int height, int depth, JTextArea outputArea) {
        try {
            treeProperty = new QuadTree(width, height, depth);
            outputArea.setText("Successfully created tree with PROPERTIES with parameters: width = " + width + ", height = " + height + " and depth = " + depth);
        } catch (NumberFormatException ex) {
            outputArea.setText("Error with creating tree: " + ex.getMessage());
        }

    }


    public void createTreeShape(int width, int height, int depth, JTextArea outputArea) {
        try {
            treeShape = new QuadTree(width, height, depth);
            outputArea.setText("Successfully created tree with SHAPES with parameters: width = " + width + ", height = " + height + " and depth = " + depth);
        } catch(NumberFormatException ex) {
            outputArea.setText("Error with creating tree: " + ex.getMessage());
        }
    }

    public void showGPS(JTextArea outputArea) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (GPS gps : gpsList) {
            counter++;
            sb.append(counter + ". -> " + "x = : " + gps.getX() + ", y = : " + gps.getY()).append("\n");
        }
        outputArea.setText(sb.toString());
    }
    public void showTreeShapes(JTextArea outputArea) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        GPS gps1 = new GPS(0, 0, 'N', 'W');
        GPS gps2 = new GPS(treeShape.getWidth(), treeShape.getHeight(), 'N', 'W');
        ArrayList<Shape> treeSearchList = treeShape.search(gps1.getX(), gps1.getY(), gps2.getX(), gps2.getY());

        for (Shape shape : treeSearchList) {
            counter++;
            sb.append(counter + ". -> " + "identifier" + shape.getIdentifier() + " x1 = : " + shape.getFirst().getX() + ", y1 = : " + shape.getFirst().getY() + ", x2 = : " + shape.getSecond().getX() + ", y2 = : " + shape.getSecond().getY()).append("\n");
        }
        outputArea.setText(sb.toString());
    }
    public void showTreeProperty(JTextArea outputArea) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        GPS gps1 = new GPS(0, 0, 'N', 'W');
        GPS gps2 = new GPS(treeProperty.getWidth(), treeProperty.getHeight(), 'N', 'W');
        ArrayList<Property> treeSearchList = treeProperty.search(gps1.getX(), gps1.getY(), gps2.getX(), gps2.getY());
        for (Property property : treeSearchList) {
            counter++;
            sb.append(counter + ". -> "+ "identifier" + property.getIdentifier() + " x1 = : " + property.getFirst().getX() + ", y1 = : " + property.getFirst().getY() + ", x2 = : " + property.getSecond().getX() + ", y2 = : " + property.getSecond().getY()).append("\n");
        }
        outputArea.setText(sb.toString());
    }
    public void createGPS(double x,double y, JTextArea outputArea) {
        try {
            char horizontal = 'W';
            char vertical = 'S';
            GPS newGPS = new GPS(x, y, horizontal, vertical);
            gpsList.add(newGPS);
            outputArea.setText("GPS successfully created with parameters: " +
                    "X: " + x + ", Y: " + y +
                    ", Horizontal: " + horizontal + ", Vertical: " + vertical + ".");
        } catch (NumberFormatException ex) {
            outputArea.setText("Please write correctly parameters: X, Y, Horizontal and Vertical.");
        }
    }
    public void createShape(int gp1, int gp2 ,JTextArea outputArea ) {
        try {
            GPS gps1 = gpsList.get(gp1 - 1);
            GPS gps2 = gpsList.get(gp2 - 1);
            Shape shape = new Shape(treeShapeList.size(), gps1, gps2, "blabla");
            treeShape.insert(shape);
            treeShapeList.add(shape);
            ArrayList<Property> temp = treeProperty.search(shape.getFirst().getX(), shape.getFirst().getY(), shape.getSecond().getX(), shape.getSecond().getY());
            for (Property item : temp) {
                item.getShapes().add(shape);
                shape.getProperties().add(item);
            }
            outputArea.setText("Shape created");
        } catch (Exception ex) {
            outputArea.setText("Error creating shape: " + ex.getMessage());
        }
    }
//
    public void createProperty(int gp1, int gp2 ,JTextArea outputArea ) {
        try {
            GPS gps1 = gpsList.get(gp1 - 1);
            GPS gps2 = gpsList.get(gp2 - 1);
            Property property = new Property(treePropertyList.size(), gps1, gps2, "blabla");
            treeProperty.insert(property);
            treePropertyList.add(property);
            ArrayList<Shape> temp = treeShape.search(property.getFirst().getX(), property.getFirst().getY(), property.getSecond().getX(), property.getSecond().getY());
            for (Shape item : temp) {
                item.getProperties().add(property);
                property.getShapes().add(item);
            }
            outputArea.setText("Property created");
        } catch (Exception ex) {
            outputArea.setText("Error creating property: " + ex.getMessage());
        }
    }

    public void searchShape(int gp1, int gp2 ,JTextArea outputArea ) {
        try {
            StringBuilder sb = new StringBuilder();
            GPS gps1 = gpsList.get(gp1 - 1);
            GPS gps2 = gpsList.get(gp2 - 1);
            int counter = 0;
            ArrayList<Shape> shapes = treeShape.search(gps1.getX(), gps1.getY(), gps2.getX(), gps2.getY());
            treeShapeListForSearch = shapes;
            for (Shape shape : shapes) {
                counter++;
                sb.append(counter + ". -> " +" identifier = " + shape.getIdentifier() +" x1 = : " + shape.getFirst().getX() + ", y1 = : " + shape.getFirst().getY() + ", x2 = : " + shape.getSecond().getX() + ", y2 = : " + shape.getSecond().getY()).append("\n");
            }
            outputArea.setText(sb.toString());
        } catch (Exception ex) {
            outputArea.setText("Error searching shapes: " + ex.getMessage());
        }
    }

    public void searchProperty(int gp1, int gp2 ,JTextArea outputArea ) {
        try {
            StringBuilder sb = new StringBuilder();
            GPS gps1 = gpsList.get(gp1 - 1);
            GPS gps2 = gpsList.get(gp2 - 1);
            int counter = 0;
            ArrayList<Property> properties = treeProperty.search(gps1.getX(), gps1.getY(), gps2.getX(), gps2.getY());
            treePropertyListForSearch = properties;
            for (Property property : properties) {
                counter++;
                sb.append(counter + ". -> "+" identifier = " + property.getIdentifier() +  " x1 = : " + property.getFirst().getX() + ", y1 = : " + property.getFirst().getY() + ", x2 = : " + property.getSecond().getX() + ", y2 = : " + property.getSecond().getY()).append("\n");
            }
            outputArea.setText(sb.toString());
        } catch (Exception ex) {
            outputArea.setText("Error searching properties: " + ex.getMessage());
        }
    }

    public void searchAll(int gp1, int gp2 ,JTextArea outputArea ) {
        try {
            StringBuilder sb = new StringBuilder();
            GPS gps1 = gpsList.get(gp1 - 1);
            GPS gps2 = gpsList.get(gp2 - 1);

            ArrayList<Shape> tempShape = treeShape.search(gps1.getX(), gps1.getY(), gps2.getX(), gps2.getY());
            int counter = 1;
            for (Shape shape : tempShape) {
                sb.append(counter++ + ". shape -> " +" identifier = " + shape.getIdentifier() + "x1 = : " + shape.getFirst().getX() + ", y1 = : " + shape.getFirst().getY() + ", x2 = : " + shape.getSecond().getX() + ", y2 = : " + shape.getSecond().getY()).append("\n");
            }

            ArrayList<Property> tempProperty = treeProperty.search(gps1.getX(), gps1.getY(), gps2.getX(), gps2.getY());
            for (Property property : tempProperty) {
                sb.append(counter++ + ". property -> " +" identifier = " + property.getIdentifier() +   "x1 = : " + property.getFirst().getX() + ", y1 = : " + property.getFirst().getY() + ", x2 = : " + property.getSecond().getX() + ", y2 = : " + property.getSecond().getY()).append("\n");
            }

            outputArea.setText(sb.toString());
        } catch (Exception ex) {
            outputArea.setText("Error searching: " + ex.getMessage());
        }
    }

    public void deleteShape(int sf,JTextArea outputArea ) {
        try {
            Shape shape = treeShapeListForSearch.get(sf - 1);
            if (treeShapeListForSearch.contains(shape)) {
                ArrayList<Property> relatedProperties = treeProperty.search(shape.getFirst().getX(), shape.getFirst().getY(), shape.getSecond().getX(), shape.getSecond().getY());
                for (Property property : relatedProperties) {
                    property.getShapes().remove(shape);
                }
                treeShape.delete(shape);
                treeShapeList.remove(shape);
                treeShapeListForSearch.remove(shape);
                outputArea.setText("Shape deleted");
            } else {
                outputArea.setText("Shape is not found");
            }
        } catch (Exception ex) {
            outputArea.setText("Error deleting shape: " + ex.getMessage());
        }
    }

    public void deleteProperty(int sf,JTextArea outputArea ) {
        try {
            Property property = treePropertyListForSearch.get(sf - 1);
            if (treePropertyListForSearch.contains(property)) {
                ArrayList<Shape> relatedShapes = treeShape.search(property.getFirst().getX(), property.getFirst().getY(), property.getSecond().getX(), property.getSecond().getY());
                for (Shape shape : relatedShapes) {
                    shape.getProperties().remove(property);
                }
                treeProperty.delete(property);
                treePropertyList.remove(property);
                treePropertyListForSearch.remove(property);
                outputArea.setText("Property deleted");
            } else {
                outputArea.setText("Property is not found");
            }
        } catch (Exception ex) {
            outputArea.setText("Error deleting property: " + ex.getMessage());
        }
    }

    public void calculateHealthShape(JTextArea outputArea ) {
        try {
            double health = Math.round(treeShape.treeHealth() * 100);
            outputArea.setText("Tree Shape health is " + health + "%");
        } catch (Exception ex) {
            outputArea.setText("Error calculating shape health: " + ex.getMessage());
        }
    }

    public void calculateHealthProperty(JTextArea outputArea ) {
        try {
            double health = Math.round(treeProperty.treeHealth() * 100);
            outputArea.setText("Tree Property health is " + health + "%");
        } catch (Exception ex) {
            outputArea.setText("Error calculating property health: " + ex.getMessage());
        }
    }

    public void changeDepthShape(int newDepth,JTextArea outputArea ) {
        try {
            treeShape.changeDepthTree(newDepth);
            outputArea.setText("Tree Shape depth changed, new depth is: " + newDepth);
        } catch (Exception ex) {
            outputArea.setText("Error changing shape depth: " + ex.getMessage());
        }
    }

    public void changeDepthProperty(int newDepth,JTextArea outputArea ) {
        try {
            treeProperty.changeDepthTree(newDepth);
            outputArea.setText("Tree Property depth changed, new depth is: " + newDepth);
        } catch (Exception ex) {
            outputArea.setText("Error changing property depth: " + ex.getMessage());
        }
    }

    public void resetAll(JTextArea outputArea ) {
        try {

            for (Shape shape : treeShapeList) {
                treeShape.delete(shape);
            }
            treeShapeList.clear();
            treeShape = null;


            for (Property property : treePropertyList) {
                treeProperty.delete(property);
            }
            treePropertyList.clear();
            treeProperty = null;


            gpsList.clear();

            outputArea.setText("Reset done!");
        } catch (Exception ex) {
            outputArea.setText("Error resetting: " + ex.getMessage());
        }
    }

    public void testAllShapes(int counter, int insert, int delete ,JTextArea outputArea  ) {
        try {
            ArrayList<Shape> temp = tester.testShapeAll(
                    treeShape,
                    counter,
                    insert,
                    delete
            );

            for (Shape shape : temp) {
                ArrayList<Property> propertiesNew = treeProperty.search(
                        shape.getFirst().getX(),
                        shape.getFirst().getY(),
                        shape.getSecond().getX(),
                        shape.getSecond().getY()
                );

                for (Property property : propertiesNew) {
                    property.getShapes().add(shape);
                    shape.getProperties().add(property);
                }

                treeShapeList.add(shape);
            }

            outputArea.setText("Test Shape successfully done!");
        } catch (Exception ex) {
            outputArea.setText("Error testing all shapes: " + ex.getMessage());
        }
    }

    public void testAllProperties(int counter, int insert, int delete ,JTextArea outputArea  ) {
        try {
            ArrayList<Property> temp = tester.testPropertyAll(
                    treeProperty,
                    counter,
                    insert,
                    delete
            );

            for (Property property : temp) {
                ArrayList<Shape> shapesNew = treeShape.search(
                        property.getFirst().getX(),
                        property.getFirst().getY(),
                        property.getSecond().getX(),
                        property.getSecond().getY()
                );

                for (Shape shape : shapesNew) {
                    shape.getProperties().add(property);
                    property.getShapes().add(shape);
                }

                treePropertyList.add(property);
            }

            outputArea.setText("Test Property successfully done!");
        } catch (Exception ex) {
            outputArea.setText("Error testing all properties: " + ex.getMessage());
        }
    }

    public void testInsert(int counter,JTextArea outputArea ) {
        try {
            if (tester.testInsert(treeShape, counter)) {
                outputArea.setText("Test insert successfully done!");
            } else {
                outputArea.setText("Test insert failed!");
            }
        } catch (Exception ex) {
            outputArea.setText("Error testing insert: " + ex.getMessage());
        }
    }
    public void editShape(int land,int gp1, int gp2, int identifier, String note, JTextArea outputArea) {
        try {
            if ((land-1) <= treeShapeListForSearch.size() && land != -1) {
                Shape shape = treeShapeListForSearch.get(land-1);
                if (gp1 != -1 && gp2!= -1) {
                    treeShape.delete(shape);
                    shape= new Shape(
                            identifier,
                            gpsList.get(gp1-1),
                            gpsList.get(gp2-1),
                            note);

                    treeShape.insert(shape);
                } else {
                    if (identifier!=-1) {
                        shape.setIdentifier(identifier);
                    }
                    if (note.length() > 0) {
                        shape.setNotes(note);
                    }
                }
                outputArea.setText("Edit shape successfully done!");
            } else {
                outputArea.setText("Edit shape failed!");
            }
        } catch (Exception ex) {
            outputArea.setText("Error testing delete: " + ex.getMessage());
        }
    }
    public void editProperty(int land,int gp1, int gp2, int identifier, String note, JTextArea outputArea) {
        try {
            if ((land-1) <= treePropertyListForSearch.size() && land != -1) {
                Property property = treePropertyListForSearch.get(land-1);
                if (gp1!=-1 && gp2!=-1) {
                    treeProperty.delete(property);
                    property= new Property(
                            identifier,
                            gpsList.get(gp1-1),
                            gpsList.get(gp2-1),
                            note);

                    treeProperty.insert(property);
                } else {
                    if (identifier!=-1) {
                        property.setIdentifier(identifier);
                    }
                    if (note.length()>0) {
                        property.setNotes(note);
                    }
                }
                outputArea.setText("Edit property successfully done!");
            } else {
                outputArea.setText("Edit property failed!");
            }
        } catch (Exception ex) {
            outputArea.setText("Error testing delete: " + ex.getMessage());
        }
    }
    public void testDelete(int counter, int insert, int delete, JTextArea outputArea) {
        try {
            if (tester.testInsertDelete(treeShape, counter, insert,delete)) {
                outputArea.setText("Test delete successfully done!");
            } else {
                outputArea.setText("Test delete failed!");
            }
        } catch (Exception ex) {
            outputArea.setText("Error testing delete: " + ex.getMessage());
        }
    }
    public void testSearch(int counter, JTextArea outputArea) {
        try {
            if (tester.testSearch(treeShape, counter)) {
                outputArea.setText("Test search successfully done!");
            } else {
                outputArea.setText("Test search failed!");
            }
        } catch (Exception ex) {
            outputArea.setText("Error testing delete: " + ex.getMessage());
        }
    }

    public void load(int land, JTextArea outputArea) {
        try {
            int decision = land;
            String fileName;
            String line;
            String[] data;
            switch (decision) {
                case 1:
                    fileName = "Shapes.csv";
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                        line = reader.readLine();
                        data = line.split(",");
                        treeShape = new QuadTree((int) Double.parseDouble(data[0]),
                                (int) Double.parseDouble(data[1]),
                                (int) Double.parseDouble(data[2]));
                        while ((line = reader.readLine()) != null) {
                            data = line.split(",");
                            Shape s = new Shape(
                                    Integer.parseInt(data[0]), // identifier
                                    new GPS(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3].charAt(0), data[4].charAt(0)),
                                    new GPS(Double.parseDouble(data[5]), Double.parseDouble(data[6]), data[7].charAt(0), data[8].charAt(0)),
                                    data[9] // notes
                            );
                            treeShape.insert(s);
                            treeShapeList.add(s);
                        }
                        outputArea.setText("Shapes were loaded from " + fileName);
                    } catch (IOException e) {
                        outputArea.setText("Error reading from file: " + e.getMessage());
                    }
                    break;
                case 2:
                    fileName = "Properties.csv";
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                        line = reader.readLine();
                        data = line.split(",");
                        treeProperty = new QuadTree((int) Double.parseDouble(data[0]),
                                (int) Double.parseDouble(data[1]),
                                (int) Double.parseDouble(data[2]));
                        while ((line = reader.readLine()) != null) {
                            data = line.split(",");
                            Property p = new Property(
                                    Integer.parseInt(data[0]),
                                    new GPS(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3].charAt(0), data[4].charAt(0)),
                                    new GPS(Double.parseDouble(data[5]), Double.parseDouble(data[6]), data[7].charAt(0), data[8].charAt(0)),
                                    data[9]
                            );
                            treeProperty.insert(p);
                            treePropertyList.add(p);
                        }
                        outputArea.setText("Shapes were loaded from " + fileName);
                    } catch (IOException e) {
                        outputArea.setText("Error reading from file: " + e.getMessage());
                    }
                    break;
                default:
                    outputArea.setText("Choose 1 or 2 to INDEX field for loading");
                    break;
            }
        } catch (Exception ex) {
            outputArea.setText("Error during load: " + ex.getMessage());
        }
    }


    public void save(int land, JTextArea outputArea) {
        try {
            int decision = land;
            String fileName;
            switch (decision){
                case 1:
                    fileName = "Shapes.csv";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        String data;
                        data = treeShape.getWidth()+ "," + treeShape.getHeight() + "," +treeShape.getMaxDepth() + "\n";
                        writer.write(data);
                        for (Shape s: treeShapeList) {
                            data = s.getIdentifier() + ","
                                    + s.getFirst().getX() + "," + s.getFirst().getY() + "," + s.getFirst().getHorizontal() + "," + s.getFirst().getVertical() + ","
                                    + s.getSecond().getX() + "," + s.getSecond().getY() + "," + s.getSecond().getHorizontal() + "," + s.getSecond().getVertical() + ","
                                    + s.getNotes() + "\n";

                            writer.write(data);
                        }
                        outputArea.setText("Data were saved to " +fileName);
                    } catch (IOException e) {
                        outputArea.setText("Error writing to file: " + e.getMessage());
                    }


                    outputArea.setText("Shapes were saved");
                    break;
                case 2:
                    fileName = "Properties.csv";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        String data;
                        data = treeProperty.getWidth()+ "," + treeProperty.getHeight() + "," + treeProperty.getMaxDepth() + "\n";
                        writer.write(data);
                        for (Property s: treePropertyList) {
                            data = s.getIdentifier() + ","
                                    + s.getFirst().getX() + "," + s.getFirst().getY() + "," + s.getFirst().getHorizontal() + "," + s.getFirst().getVertical() + ","
                                    + s.getSecond().getX() + "," + s.getSecond().getY() + "," + s.getSecond().getHorizontal() + "," + s.getSecond().getVertical() + ","
                                    + s.getNotes() + "\n";

                            writer.write(data);
                        }
                        outputArea.setText("Data were saved to " +fileName);
                    } catch (IOException e) {
                        outputArea.setText("Error writing to file: " + e.getMessage());
                    }
                    break;
                default:
                    outputArea.setText("Choose 1 or 2 to INDEX field");
                    break;
            }



        } catch (Exception ex) {
            outputArea.setText("Error testing delete: " + ex.getMessage());
        }
    }


}

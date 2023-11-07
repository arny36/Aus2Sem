package GUI;

import Tests.Tester;
import Tree.GPS;
import Tree.Property;
import Tree.QuadTree;
import Tree.Shape;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Gui extends JFrame {
    private ArrayList<GPS> gpsList = new ArrayList<>();
    private ArrayList<Shape> treeShapeList = new ArrayList<Shape>();
    private ArrayList<Property> treePropertyList = new ArrayList<Property>();
    private ArrayList<Shape> treeShapeListForSearch = new ArrayList<Shape>();
    private ArrayList<Property> treePropertyListForSearch = new ArrayList<Property>();
    private QuadTree treeShape, treeProperty;
    private Tester tester = new Tester();

    private JTextField widthField, heightField, depthField, xField, yField,
             insertPercentageField,deletePercentageField,counterField, newDepthField , gps1Field, gps2Field , identifierField , noteField, shapeField;
    private JButton healthShapeButton,healthPropertyButton, createTreeShapeButton, createTreePropertyButton, createGPSButton,
            testAllShapeButton, testAllPropertyButton, changeDepthShapeButton , changeDepthPropertyButton, createShapeButton , searchShapeButton , createPropertyButton , searchPropertyButton, editShapeButton, editPropertyButton , searchAllButton,
            showGPSButton, deleteShapeButton ,deletePropertyButton  , showTreeShapesButton , showTreePropertyButton, resetButton , testInsert, testDelete , testSearch , loadButton , saveButton;
    private JLabel widthLabel, heightLabel, depthLabel, xLabel, yLabel,identifierLabel, noteLabel, gps1Label,
            gps2Label, insertPercentageLabel, deletePercentageLabel, counterLabel,
            newDepthLabel, shapeLabel;
    private JPanel treePanel, gpsPanel, landShapesPanel,topPanel, bottomPanel, buttonsPanel, testPanel , depthChangePanel, leftPanel, rightPanel;

    private JTextArea outputArea;
    public Gui() {
        initializeUI();
        initializeActionListeners();
    }

    private void initializeFields() {
        widthField = new JTextField(5);
        heightField = new JTextField(5);
        depthField = new JTextField(5);
        xField = new JTextField(3);
        yField = new JTextField(3);
        gps1Field = new JTextField(10);
        gps2Field = new JTextField(10);
        insertPercentageField = new JTextField(5);
        deletePercentageField = new JTextField(5);
        counterField = new JTextField(5);
        newDepthField = new JTextField(5);
        shapeField = new JTextField(5);
        identifierField = new JTextField(5);
        noteField = new JTextField(5);
        outputArea = new JTextArea();
    }
    private void initializeButtons() {
        createTreeShapeButton = new JButton("Create ShapeTree");
        createTreePropertyButton = new JButton("Create PropertyTree");
        createGPSButton = new JButton("CreateGPS");
        createShapeButton = new JButton("Create Shape");
        searchShapeButton = new JButton("Search Shape");
        createPropertyButton = new JButton("Create Property");
        searchPropertyButton = new JButton("Search Property");
        searchAllButton = new JButton("Search all");
        healthShapeButton = new JButton("Tree Shape health");
        healthPropertyButton = new JButton("Tree Property health");
        testAllShapeButton = new JButton("Test all shape");
        testAllPropertyButton = new JButton("Test all property");
        testInsert = new JButton("Test insert");
        testDelete = new JButton("Test delete");
        changeDepthShapeButton = new JButton("ChangeDepth Shape");
        changeDepthPropertyButton = new JButton("ChangeDepth Property");
        showTreeShapesButton = new JButton("Show Shape tree");
        showTreePropertyButton = new JButton("Show Property tree");
        deleteShapeButton = new JButton("Delete shapes");
        deletePropertyButton = new JButton("Delete properties");
        resetButton = new JButton("RESET");
        showGPSButton = new JButton("Show all GPS locations");
        testSearch = new JButton("Test Search");
        editShapeButton = new JButton("Edit Shape");
        editPropertyButton = new JButton("Edit Property");
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");

    }

    private void initializeLabels() {
        widthLabel = new JLabel("Width:");
        heightLabel = new JLabel("Height:");
        depthLabel = new JLabel("Depth:");
        xLabel = new JLabel("X:");
        yLabel = new JLabel("Y:");
        identifierLabel = new JLabel("Identifier:");
        noteLabel = new JLabel("Note:");
        gps1Label = new JLabel("GPS1:");
        gps2Label = new JLabel("GPS2:");
        insertPercentageLabel = new JLabel("Insert %:");
        deletePercentageLabel = new JLabel("Delete %:");
        counterLabel = new JLabel("Counter:");
        newDepthLabel = new JLabel("NewDepth:");
        shapeLabel = new JLabel("INDEX LandShape:");
    }



    private void initializeTreePanel() {
        treePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        treePanel.add(widthLabel);
        treePanel.add(widthField);
        treePanel.add(heightLabel);
        treePanel.add(heightField);
        treePanel.add(depthLabel);
        treePanel.add(depthField);
        treePanel.add(createTreeShapeButton);
        treePanel.add(createTreePropertyButton);
    }

    private void initializeGpsPanel() {
        gpsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gpsPanel.add(xLabel);
        gpsPanel.add(xField);
        gpsPanel.add(yLabel);
        gpsPanel.add(yField);
        gpsPanel.add(createGPSButton);
    }

    private void initializeLandShapesPanel() {
        landShapesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        landShapesPanel.add(createShapeButton);
        landShapesPanel.add(searchShapeButton);
        landShapesPanel.add(createPropertyButton);
        landShapesPanel.add(searchPropertyButton);
        landShapesPanel.add(searchAllButton);
        landShapesPanel.add(editShapeButton);
        landShapesPanel.add(editPropertyButton);
        landShapesPanel.add(identifierLabel);
        landShapesPanel.add(identifierField);
        landShapesPanel.add(noteLabel);
        landShapesPanel.add(noteField);
        landShapesPanel.add(gps1Label);
        landShapesPanel.add(gps1Field);
        landShapesPanel.add(gps2Label);
        landShapesPanel.add(gps2Field);
    }


    private void initializeButtonsPanel() {
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(shapeLabel);
        buttonsPanel.add(shapeField);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(deleteShapeButton);
        buttonsPanel.add(deletePropertyButton);
        buttonsPanel.add(healthShapeButton);
        buttonsPanel.add(healthPropertyButton);
        buttonsPanel.add(showGPSButton);
        buttonsPanel.add(showTreeShapesButton);
        buttonsPanel.add(showTreePropertyButton);
        buttonsPanel.add(resetButton);
    }

    private void initializeTestPanel() {
        testPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        testPanel.add(testInsert);
        testPanel.add(testDelete);
        testPanel.add(testSearch);
        testPanel.add(testAllShapeButton);
        testPanel.add(testAllPropertyButton);
        testPanel.add(insertPercentageLabel);
        testPanel.add(insertPercentageField);
        testPanel.add(deletePercentageLabel);
        testPanel.add(deletePercentageField);
        testPanel.add(counterLabel);
        testPanel.add(counterField);
    }
    private void initializeDepthChangePanel() {
        depthChangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        depthChangePanel.add(changeDepthShapeButton);
        depthChangePanel.add(changeDepthPropertyButton);
        depthChangePanel.add(newDepthLabel);
        depthChangePanel.add(newDepthField);
    }
    private void initializeBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        initializeButtonsPanel();
        initializeTestPanel();
        initializeDepthChangePanel();
        bottomPanel.add(buttonsPanel);
        bottomPanel.add(testPanel);
        bottomPanel.add(depthChangePanel);
    }

    private void initializeTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        initializeTreePanel();
        initializeGpsPanel();
        initializeLandShapesPanel();
        topPanel.add(treePanel);
        topPanel.add(gpsPanel);
        topPanel.add(landShapesPanel);
    }

    private void initializeRightPanel() {
        rightPanel = new JPanel(new BorderLayout());
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        rightPanel.add(scrollPane);
    }

    private void initializeLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        initializeTopPanel();
        initializeBottomPanel();
        leftPanel.add(topPanel);
        leftPanel.add(bottomPanel);
    }

    private void initializeUI() {
        initializeComponents();
        initializeLeftPanel();
        initializeRightPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(350);

        setTitle("Landshapes");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(splitPane);
    }
    private void initializeComponents(){
        initializeFields();
        initializeButtons();
        initializeLabels();
    }


    private void createTreeProperty() {
        try {
            int width = Integer.parseInt(widthField.getText());
            int height = Integer.parseInt(heightField.getText());
            int depth = Integer.parseInt(depthField.getText());
            treeProperty = new QuadTree(width, height, depth);
            outputArea.setText("Successfully created tree with PROPERTIES with parameters: width = " + width + ", height = " + height + " and depth = " + depth);
        } catch(NumberFormatException ex) {
            outputArea.setText("Error with creating tree: " + ex.getMessage());
        }
    }


    private void createTreeShape() {
        try {
            int width = Integer.parseInt(widthField.getText());
            int height = Integer.parseInt(heightField.getText());
            int depth = Integer.parseInt(depthField.getText());

            treeShape = new QuadTree(width, height, depth);
            outputArea.setText("Successfully created tree with SHAPES with parameters: width = " + width + ", height = " + height + " and depth = " + depth);
        } catch(NumberFormatException ex) {
            outputArea.setText("Error with creating tree: " + ex.getMessage());
        }
    }

    private void showGPS() {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (GPS gps : gpsList) {
            counter++;
            sb.append(counter + ". -> " + "x = : " + gps.getX() + ", y = : " + gps.getY()).append("\n");
        }
        outputArea.setText(sb.toString());
    }

    private void showTreeShapes() {
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

    private void showTreeProperty() {
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

    private void createGPS() {
        try {
            double x = Double.parseDouble(xField.getText());
            double y = Double.parseDouble(yField.getText());
            char horizontal = 'W';
            char vertical = 'S';
            GPS newGPS = new GPS(x, y, horizontal, vertical);
            gpsList.add(newGPS);
            outputArea.setText("GPS successfully created with parameters: " +
                    "X: " + x + ", Y: " + y +
                    ", Horizontal: " + horizontal + ", Vertical: " + vertical + ".");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please write correctly parameters: X, Y, Horizontal and Vertical.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createShape() {
        try {
            GPS gps1 = gpsList.get(Integer.parseInt(gps1Field.getText()) - 1);
            GPS gps2 = gpsList.get(Integer.parseInt(gps2Field.getText()) - 1);
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

    private void createProperty() {
        try {
            GPS gps1 = gpsList.get(Integer.parseInt(gps1Field.getText()) - 1);
            GPS gps2 = gpsList.get(Integer.parseInt(gps2Field.getText()) - 1);
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

    private void searchShape() {
        try {
            StringBuilder sb = new StringBuilder();
            GPS gps1 = gpsList.get(Integer.parseInt(gps1Field.getText()) - 1);
            GPS gps2 = gpsList.get(Integer.parseInt(gps2Field.getText()) - 1);
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

    private void searchProperty() {
        try {
            StringBuilder sb = new StringBuilder();
            GPS gps1 = gpsList.get(Integer.parseInt(gps1Field.getText()) - 1);
            GPS gps2 = gpsList.get(Integer.parseInt(gps2Field.getText()) - 1);
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

    private void searchAll() {
        try {
            StringBuilder sb = new StringBuilder();
            GPS gps1 = gpsList.get(Integer.parseInt(gps1Field.getText()) - 1);
            GPS gps2 = gpsList.get(Integer.parseInt(gps2Field.getText()) - 1);

            // Search for shapes
            ArrayList<Shape> tempShape = treeShape.search(gps1.getX(), gps1.getY(), gps2.getX(), gps2.getY());
            int counter = 1;
            for (Shape shape : tempShape) {
                sb.append(counter++ + ". shape -> " +" identifier = " + shape.getIdentifier() + "x1 = : " + shape.getFirst().getX() + ", y1 = : " + shape.getFirst().getY() + ", x2 = : " + shape.getSecond().getX() + ", y2 = : " + shape.getSecond().getY()).append("\n");
            }

            // Search for properties
            ArrayList<Property> tempProperty = treeProperty.search(gps1.getX(), gps1.getY(), gps2.getX(), gps2.getY());
            for (Property property : tempProperty) {
                sb.append(counter++ + ". property -> " +" identifier = " + property.getIdentifier() +   "x1 = : " + property.getFirst().getX() + ", y1 = : " + property.getFirst().getY() + ", x2 = : " + property.getSecond().getX() + ", y2 = : " + property.getSecond().getY()).append("\n");
            }

            outputArea.setText(sb.toString());
        } catch (Exception ex) {
            outputArea.setText("Error searching: " + ex.getMessage());
        }
    }

    private void deleteShape() {
        try {
            Shape shape = treeShapeListForSearch.get(Integer.parseInt(shapeField.getText()) - 1);
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

    private void deleteProperty() {
        try {
            Property property = treePropertyListForSearch.get(Integer.parseInt(shapeField.getText()) - 1);
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

    private void calculateHealthShape() {
        try {
            double health = Math.round(treeShape.treeHealth() * 100);
            outputArea.setText("Tree Shape health is " + health + "%");
        } catch (Exception ex) {
            outputArea.setText("Error calculating shape health: " + ex.getMessage());
        }
    }

    private void calculateHealthProperty() {
        try {
            double health = Math.round(treeProperty.treeHealth() * 100);
            outputArea.setText("Tree Property health is " + health + "%");
        } catch (Exception ex) {
            outputArea.setText("Error calculating property health: " + ex.getMessage());
        }
    }

    private void changeDepthShape() {
        try {
            int newDepth = Integer.parseInt(newDepthField.getText());
            treeShape.changeDepthTree(newDepth);
            outputArea.setText("Tree Shape depth changed, new depth is: " + newDepth);
        } catch (Exception ex) {
            outputArea.setText("Error changing shape depth: " + ex.getMessage());
        }
    }

    private void changeDepthProperty() {
        try {
            int newDepth = Integer.parseInt(newDepthField.getText());
            treeProperty.changeDepthTree(newDepth);
            outputArea.setText("Tree Property depth changed, new depth is: " + newDepth);
        } catch (Exception ex) {
            outputArea.setText("Error changing property depth: " + ex.getMessage());
        }
    }

    private void resetAll() {
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

    private void testAllShapes() {
        try {
            ArrayList<Shape> temp = tester.testShapeAll(
                    treeShape,
                    Integer.parseInt(counterField.getText()),
                    Integer.parseInt(insertPercentageField.getText()),
                    Integer.parseInt(deletePercentageField.getText())
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

    private void testAllProperties() {
        try {
            ArrayList<Property> temp = tester.testPropertyAll(
                    treeProperty,
                    Integer.parseInt(counterField.getText()),
                    Integer.parseInt(insertPercentageField.getText()),
                    Integer.parseInt(deletePercentageField.getText())
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

    private void testInsert() {
        try {
            if (tester.testInsert(treeShape, Integer.parseInt(counterField.getText()))) {
                outputArea.setText("Test insert successfully done!");
            } else {
                outputArea.setText("Test insert failed!");
            }
        } catch (Exception ex) {
            outputArea.setText("Error testing insert: " + ex.getMessage());
        }
    }
    private void editShape() {
        try {
            if ((Integer.parseInt(shapeField.getText())-1) <= treeShapeListForSearch.size()) {
                Shape shape = treeShapeListForSearch.get(Integer.parseInt(shapeField.getText())-1);
                if (gps1Field.getText().length()>0 && gps2Field.getText().length()>0) {
                    treeShape.delete(shape);
                    shape= new Shape(
                            Integer.parseInt(identifierField.getText()),
                            gpsList.get(Integer.parseInt(gps1Field.getText())-1),
                            gpsList.get(Integer.parseInt(gps2Field.getText())-1),
                            noteField.getText());

                    treeShape.insert(shape);
                } else {
                    if (identifierField.getText().length() > 0) {
                        shape.setIdentifier(Integer.parseInt(identifierField.getText()));
                    }
                    if (noteField.getText().length() > 0) {
                        shape.setNotes(noteField.getText());
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
    private void editProperty() {
        try {
            if ((Integer.parseInt(shapeField.getText())-1) <= treePropertyListForSearch.size()) {
                Property property = treePropertyListForSearch.get(Integer.parseInt(shapeField.getText())-1);
                if (gps1Field.getText().length()>0 && gps2Field.getText().length()>0) {
                    treeProperty.delete(property);
                    property= new Property(
                            Integer.parseInt(identifierField.getText()),
                            gpsList.get(Integer.parseInt(gps1Field.getText())-1),
                            gpsList.get(Integer.parseInt(gps2Field.getText())-1),
                            noteField.getText());

                    treeProperty.insert(property);
                } else {
                    if (identifierField.getText().length() > 0) {
                        property.setIdentifier(Integer.parseInt(identifierField.getText()));
                    }
                    if (noteField.getText().length() > 0) {
                        property.setNotes(noteField.getText());
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
    private void testDelete() {
        try {
            if (tester.testInsertDelete(
                    treeShape,
                    Integer.parseInt(counterField.getText()),
                    Integer.parseInt(insertPercentageField.getText()),
                    Integer.parseInt(deletePercentageField.getText())
            )) {
                outputArea.setText("Test delete successfully done!");
            } else {
                outputArea.setText("Test delete failed!");
            }
        } catch (Exception ex) {
            outputArea.setText("Error testing delete: " + ex.getMessage());
        }
    }
    private void testSearch() {
        try {
            if (tester.testSearch(treeShape, Integer.parseInt(counterField.getText()))) {
                outputArea.setText("Test search successfully done!");
            } else {
                outputArea.setText("Test search failed!");
            }
        } catch (Exception ex) {
            outputArea.setText("Error testing delete: " + ex.getMessage());
        }
    }

    private void load() {
        try {
            int decision = Integer.parseInt(shapeField.getText());
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


    private void save() {
        try {
            int decision = Integer.parseInt(shapeField.getText());
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


    private void initializeActionListeners(){

        createTreePropertyButton.addActionListener(e -> createTreeProperty());
        createTreeShapeButton.addActionListener(e -> createTreeShape());
        showGPSButton.addActionListener(e -> showGPS());
        showTreeShapesButton.addActionListener(e -> showTreeShapes());
        showTreePropertyButton.addActionListener(e -> showTreeProperty());
        createGPSButton.addActionListener(e -> createGPS());
        createShapeButton.addActionListener(e -> createShape());
        createPropertyButton.addActionListener(e -> createProperty());
        searchShapeButton.addActionListener(e -> searchShape());
        searchPropertyButton.addActionListener(e -> searchProperty());
        searchAllButton.addActionListener(e -> searchAll());
        deleteShapeButton.addActionListener(e -> deleteShape());
        deletePropertyButton.addActionListener(e -> deleteProperty());
        healthShapeButton.addActionListener(e -> calculateHealthShape());
        healthPropertyButton.addActionListener(e -> calculateHealthProperty());
        changeDepthShapeButton.addActionListener(e -> changeDepthShape());
        changeDepthPropertyButton.addActionListener(e -> changeDepthProperty());
        resetButton.addActionListener(e -> resetAll());
        testAllShapeButton.addActionListener(e -> testAllShapes());
        testAllPropertyButton.addActionListener(e -> testAllProperties());
        testInsert.addActionListener(e -> testInsert());
        testDelete.addActionListener(e -> testDelete());
        testSearch.addActionListener(e -> testSearch());
        editShapeButton.addActionListener(e -> editShape());
        editPropertyButton.addActionListener(e -> editProperty());
        saveButton.addActionListener(e -> save());
        loadButton.addActionListener(e -> load());
    }

}

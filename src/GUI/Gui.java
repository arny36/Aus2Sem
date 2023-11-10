package GUI;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private JTextField widthField, heightField, depthField, xField, yField,
    insertPercentageField,deletePercentageField,counterField, newDepthField , gps1Field, gps2Field , identifierField , noteField, shapeField;
    private JButton healthShapeButton,healthPropertyButton, createTreeShapeButton, createTreePropertyButton, createGPSButton,
    testAllShapeButton, testAllPropertyButton, changeDepthShapeButton , changeDepthPropertyButton, createShapeButton , searchShapeButton , createPropertyButton , searchPropertyButton, editShapeButton, editPropertyButton , searchAllButton,
    showGPSButton, deleteShapeButton ,deletePropertyButton  , showTreeShapesButton , showTreePropertyButton, resetButton , testInsert, testDelete , testSearch , loadButton , saveButton , optimizeButton , insertDeleteSpeedButton;
    private JLabel widthLabel, heightLabel, depthLabel, xLabel, yLabel,identifierLabel, noteLabel, gps1Label,
    gps2Label, insertPercentageLabel, deletePercentageLabel, counterLabel,
    newDepthLabel, shapeLabel ,optimizeLabel;
    private JPanel treePanel, gpsPanel, landShapesPanel,topPanel, bottomPanel, buttonsPanel, testPanel , depthChangePanel, leftPanel, rightPanel;
    private JCheckBox optimizedCB;

    private JTextArea outputArea;
    private Controller controler = new Controller();
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
        insertDeleteSpeedButton = new JButton("Test speed i/e");
        testDelete = new JButton("Test delete");
        changeDepthShapeButton = new JButton("ChangeDepth Shape");
        changeDepthPropertyButton = new JButton("ChangeDepth Property");
        showTreeShapesButton = new JButton("Show Shape tree");
        showTreePropertyButton = new JButton("Show Property tree");
        deleteShapeButton = new JButton("Delete shapes");
        deletePropertyButton = new JButton("Delete properties");

        optimizeButton = new JButton("Optimize");
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
        optimizeLabel = new JLabel("Optimize ? :");
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
        buttonsPanel.add(optimizeButton);
        buttonsPanel.add(resetButton);
    }

    private void initializeTestPanel() {
        testPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        testPanel.add(testInsert);
        testPanel.add(insertDeleteSpeedButton);
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
        optimizedCB = new JCheckBox("Check me");
        testPanel.add(optimizeLabel);
        testPanel.add(optimizedCB);
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


    private void initializeActionListeners(){

        createTreePropertyButton.addActionListener(e -> controler.createTreeProperty(Integer.parseInt(widthField.getText()),Integer.parseInt(heightField.getText()),Integer.parseInt(depthField.getText()),outputArea));
        createTreeShapeButton.addActionListener(e -> controler.createTreeShape(Integer.parseInt(widthField.getText()),Integer.parseInt(heightField.getText()),Integer.parseInt(depthField.getText()),outputArea));
        showGPSButton.addActionListener(e -> controler.showGPS(outputArea));
        showTreeShapesButton.addActionListener(e -> controler.showTreeShapes(outputArea));
        showTreePropertyButton.addActionListener(e -> controler.showTreeProperty(outputArea));
        createGPSButton.addActionListener(e -> controler.createGPS(Double.parseDouble(xField.getText()),Double.parseDouble(yField.getText()),outputArea));
        createShapeButton.addActionListener(e -> controler.createShape(Integer.parseInt(identifierField.getText()),noteField.getText(),Integer.parseInt(gps1Field.getText()),Integer.parseInt(gps2Field.getText()),outputArea));
        createPropertyButton.addActionListener(e -> controler.createProperty(Integer.parseInt(identifierField.getText()),noteField.getText(),Integer.parseInt(gps1Field.getText()),Integer.parseInt(gps2Field.getText()),outputArea));
        searchShapeButton.addActionListener(e -> controler.searchShape(Integer.parseInt(gps1Field.getText()),Integer.parseInt(gps2Field.getText()),outputArea));
        searchPropertyButton.addActionListener(e -> controler.searchProperty(Integer.parseInt(gps1Field.getText()),Integer.parseInt(gps2Field.getText()),outputArea));
        searchAllButton.addActionListener(e -> controler.searchAll(Integer.parseInt(gps1Field.getText()),Integer.parseInt(gps2Field.getText()),outputArea));
        deleteShapeButton.addActionListener(e -> controler.deleteShape(Integer.parseInt(shapeField.getText()),outputArea));
        deletePropertyButton.addActionListener(e -> controler.deleteProperty(Integer.parseInt(shapeField.getText()),outputArea));
        healthShapeButton.addActionListener(e -> controler.calculateHealthShape(Integer.parseInt(shapeField.getText()),outputArea));
        healthPropertyButton.addActionListener(e -> controler.calculateHealthProperty(Integer.parseInt(shapeField.getText()),outputArea));
        changeDepthShapeButton.addActionListener(e -> controler.changeDepthShape(Integer.parseInt(newDepthField.getText()),outputArea));
        changeDepthPropertyButton.addActionListener(e -> controler.changeDepthProperty(Integer.parseInt(newDepthField.getText()),outputArea));
        optimizeButton.addActionListener(e -> controler.optimize(Integer.parseInt(shapeField.getText()),outputArea));
        resetButton.addActionListener(e -> controler.resetAll(outputArea));
        testAllShapeButton.addActionListener(e -> controler.testAllShapes(Integer.parseInt(shapeField.getText()),Integer.parseInt(counterField.getText()),Integer.parseInt(insertPercentageField.getText()),Integer.parseInt(deletePercentageField.getText()),outputArea));
        testAllPropertyButton.addActionListener(e -> controler.testAllProperties(Integer.parseInt(shapeField.getText()),Integer.parseInt(counterField.getText()),Integer.parseInt(insertPercentageField.getText()),Integer.parseInt(deletePercentageField.getText()),outputArea));
        testInsert.addActionListener(e -> controler.testInsert(Integer.parseInt(counterField.getText()),outputArea));
        insertDeleteSpeedButton.addActionListener(e -> controler.insertDeleteSpeed(optimizedCB.isSelected(),Integer.parseInt(counterField.getText()),outputArea));
        testDelete.addActionListener(e -> controler.testDelete(Integer.parseInt(counterField.getText()),Integer.parseInt(insertPercentageField.getText()),Integer.parseInt(deletePercentageField.getText()),outputArea));
        testSearch.addActionListener(e -> controler.testSearch(Integer.parseInt(counterField.getText()),outputArea));
        editShapeButton.addActionListener(e -> {
            int shape = shapeField.getText().isEmpty() ? -1 : Integer.parseInt(shapeField.getText());
            int gps1 = gps1Field.getText().isEmpty() ? -1 : Integer.parseInt(gps1Field.getText());
            int gps2 = gps2Field.getText().isEmpty() ? -1 : Integer.parseInt(gps2Field.getText());
            int identifier = identifierField.getText().isEmpty() ? -1 : Integer.parseInt(identifierField.getText());
            String note = noteField.getText();

            controler.editShape(shape, gps1, gps2, identifier, note, outputArea);
        });

        editPropertyButton.addActionListener(e -> {
            int shape = shapeField.getText().isEmpty() ? -1 : Integer.parseInt(shapeField.getText());
            int gps1 = gps1Field.getText().isEmpty() ? -1 : Integer.parseInt(gps1Field.getText());
            int gps2 = gps2Field.getText().isEmpty() ? -1 : Integer.parseInt(gps2Field.getText());
            int identifier = identifierField.getText().isEmpty() ? -1 : Integer.parseInt(identifierField.getText());
            String note = noteField.getText();

            controler.editProperty(shape, gps1, gps2, identifier, note, outputArea);
        });


        saveButton.addActionListener(e -> controler.save(Integer.parseInt(shapeField.getText()),outputArea));
        loadButton.addActionListener(e -> controler.load(Integer.parseInt(shapeField.getText()),outputArea));
    }
}

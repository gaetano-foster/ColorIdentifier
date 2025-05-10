package com.github.coloridentifier.graphing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Graph extends JPanel {
    private int windowWidth, windowHeight;
    private double startX, startY; // Bottom left corner
    private double endX, endY; // Top right corner
    private String title, xAxisLabel, yAxisLabel;
    private int regularity = 1; // how many numbers it should draw on axis

    private ArrayList<DataPoint> coordinates;
    private BufferedImage backgroundShade;
    private GraphType type = GraphType.SCATTER;
    private final int BORDER_START_X, BORDER_START_Y, BORDER_END_X, BORDER_END_Y;

    private JFrame frame;

    public Graph(
            int windowWidth,
            int windowHeight,
            double startX,
            double startY,
            double endX,
            double endY,
            String title,
            String xAxisLabel,
            String yAxisLabel)
            throws HeadlessException
    {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.coordinates = new ArrayList<DataPoint>();

        BORDER_START_X = windowWidth / 6;
        BORDER_START_Y = windowHeight * 5 / 6;
        BORDER_END_X = windowWidth * 5 / 6;
        BORDER_END_Y = windowHeight / 6;

        backgroundShade = new BufferedImage(BORDER_END_X - BORDER_START_X, BORDER_START_Y - BORDER_END_Y, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < backgroundShade.getHeight(); y++) {
            for (int x = 0; x < backgroundShade.getWidth(); x++) {
                backgroundShade.setRGB(x, y, Color.HSBtoRGB(1, 0, 1));
            }
        }

        createDisplayWindow();
    }

    private void createDisplayWindow() {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setMaximumSize(new Dimension(windowWidth, windowHeight));
        setMinimumSize(new Dimension(windowWidth, windowHeight));
        setFocusable(false);

        frame.add(this);
        frame.pack();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Always call super method first

        g.drawImage(backgroundShade, BORDER_START_X, BORDER_END_Y, null);

        // draw the graph borders
        g.setColor(Color.BLACK);
        g.drawLine(BORDER_START_X, BORDER_START_Y, BORDER_END_X, BORDER_START_Y);
        g.drawLine(BORDER_START_X, BORDER_START_Y, BORDER_START_X, BORDER_END_Y);
        
        // draw the numbers
        double scaleX = (double)(BORDER_END_X - BORDER_START_X) * regularity / (endX + 1);
        for (int i = 0; i < (endX + 1) / regularity; i++) {
            g.drawString(Integer.toString(i * regularity), (int) (BORDER_START_X + i * scaleX), BORDER_START_Y + g.getFontMetrics().getHeight());
        }

        double scaleY = (double)(BORDER_END_Y - BORDER_START_Y) * regularity / (endY + 1);
        for (int i = 0; i < (endY + 1) / regularity; i++) {
            g.drawString(Integer.toString(i * regularity), BORDER_START_X - g.getFontMetrics().getWidths()[1] * 3, (int) (BORDER_START_Y + i * scaleY));
        }

        // label axes
        g.drawString(xAxisLabel, (int) (BORDER_START_X + (endX / regularity / 2) * scaleX), BORDER_START_Y + g.getFontMetrics().getHeight() * 3);
        g.drawString(yAxisLabel, BORDER_START_X - g.getFontMetrics().getWidths()[1] * 10, (int) (BORDER_START_Y + (endY / regularity / 2) * scaleY));

        // plot points
        if (type == GraphType.SCATTER) {
            for (DataPoint c : coordinates) {
                g.setColor(c.color);
                g.drawString(Character.toString(c.symbol), (int) (BORDER_START_X + c.x * scaleX / regularity), (int) (BORDER_START_Y + c.y * scaleY / regularity));
            }
        }
    }

    public void setGraphType(GraphType type) {
        this.type = type;
    }

    public void addPoint(DataPoint dataPoint) {
        coordinates.add(dataPoint);
    }

    public void shadeGraph(double x, double y, Color color) {
        final int GRAPH_WIDTH = BORDER_END_X - BORDER_START_X;
        final int GRAPH_HEIGHT = BORDER_START_Y - BORDER_END_Y;

        int translatedX = (int) ((x / endX) * GRAPH_WIDTH);
        int translatedY = (int) (((endY - y) / endY) * GRAPH_HEIGHT - 1);

        backgroundShade.setRGB(translatedX, translatedY, color.getRGB());
    }

    public void setRegularity(int regularity) {
        this.regularity = regularity;
    }

    public void addPoint(double x, double y) {
        coordinates.add(new DataPoint(x, y));
    }

    public void addPoint(double x, double y, char symbol) {
        coordinates.add(new DataPoint(x, y, symbol));
    }

    public void addPoint(double x, double y, Color color) {
        coordinates.add(new DataPoint(x, y, color));
    }

    public void addPoint(double x, double y, char symbol, Color color) {
        coordinates.add(new DataPoint(x, y, symbol, color));
    }

    public DataPoint removePoint(int index) {
        try {
            return coordinates.remove(index);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getxAxisLabel() {
        return xAxisLabel;
    }

    public void setxAxisLabel(String xAxisLabel) {
        this.xAxisLabel = xAxisLabel;
    }

    public String getyAxisLabel() {
        return yAxisLabel;
    }

    public void setyAxisLabel(String yAxisLabel) {
        this.yAxisLabel = yAxisLabel;
    }
}
package fr.epita.last_exam.datamodels;

public class Image {
    private int label;
    private double[][] dataMatrix;

    public Image(int label, double[][] dataMatrix) {
        this.label = label;
        this.dataMatrix = dataMatrix;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public double[][] getDataMatrix() {
        return dataMatrix;
    }

    public void setDataMatrix(double[][] dataMatrix) {
        this.dataMatrix = dataMatrix;
    }
}
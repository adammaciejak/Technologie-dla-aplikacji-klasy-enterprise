package lab.servletsjspjstl;

public class ColorBean {
    private String foregroundColor;
    private String backgroundColor;
    private boolean tableBorder;

    public String getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isTableBorder() {
        return tableBorder;
    }

    public void setTableBorder(boolean tableBorder) {
        this.tableBorder = tableBorder;
    }
}
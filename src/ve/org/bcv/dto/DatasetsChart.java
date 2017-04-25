/**
 * 
 */
package ve.org.bcv.dto;

import java.util.List;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 27/07/2016 09:01:33
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class DatasetsChart {
	private String  label;
	private List<Integer> data;
	private List<String> backgroundColor;
	private List<String> borderColor;
  int  borderWidth;
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}
 
public List<String> getBackgroundColor() {
	return backgroundColor;
}
public void setBackgroundColor(List<String> backgroundColor) {
	this.backgroundColor = backgroundColor;
}
public List<String> getBorderColor() {
	return borderColor;
}
public void setBorderColor(List<String> borderColor) {
	this.borderColor = borderColor;
}
public int getBorderWidth() {
	return borderWidth;
}
public void setBorderWidth(int borderWidth) {
	this.borderWidth = borderWidth;
}
public List<Integer> getData() {
	return data;
}
public void setData(List<Integer> data) {
	this.data = data;
}
}

/**
 * 
 */
package ve.org.bcv.dto;

import java.util.List;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 27/07/2016 09:11:10
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class ScalesChart {
	private List<YAxesChart> yAxes;

	public List<YAxesChart> getyAxes() {
		return yAxes;
	}

	public void setyAxes(List<YAxesChart> yAxes) {
		this.yAxes = yAxes;
	}
}

/**
 * 
 */
package ve.org.bcv.dto;

import java.util.List;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 27/07/2016 08:56:58
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class DataChart {
	private List<String> labels;
	private List<DatasetsChart> datasets;
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public List<DatasetsChart> getDatasets() {
		return datasets;
	}
	public void setDatasets(List<DatasetsChart> datasets) {
		this.datasets = datasets;
	}
}

/**
 * 
 */
package ve.org.bcv.dto;


/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 27/07/2016 08:55:01
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class ChartDto {
	private String type;
	private DataChart data;
	private OptionsChart options;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public DataChart getData() {
		return data;
	}
	public void setData(DataChart data) {
		this.data = data;
	}
	public OptionsChart getOptions() {
		return options;
	}
	public void setOptions(OptionsChart options) {
		this.options = options;
	}

}
/***
{ 
	"type":"bar",
	"data": {
	      "labels": ["Miercoles Desde las 2pm hasta las 04y 30","Yoga TRX","Lunes desde las 2 a las 4 y 30","Domingo","jajajja","Martes Desde las 2pm hasta las 04y 30"],
	      "datasets":[{
	      		"label":"# of Votes",
			     "data":[12,19,3,5,2,3],
			      "backgroundColor":[
			      "rgba(255, 99, 132, 0.2)",
			      "rgba(54, 162, 235, 0.2)",
			      "rgba(255, 206, 86, 0.2)",
			      "rgba(75, 192, 192, 0.2)",
			      "rgba(153, 102, 255, 0.2)",
			      "rgba(255, 159, 64, 0.2)"
			      ],
			      "borderColor":[
			      "rgba(255,99,132,1)",
			      "rgba(54, 162, 235, 1)",
			      "rgba(255, 206, 86, 1)",
			      "rgba(75, 192, 192, 1)",
			      "rgba(153, 102, 255, 1)",
			      "rgba(255, 159, 64, 1)"
			      ],
			      "borderWidth":1
			      }]
			      },
			      "options":{
			      "scales":{
			      "yAxes":[{
			      "ticks":{
			      "beginAtZero":true
			      }
			      }]
			      }
			      }
			      }

***/
//{
//    type: 'bar',
//    data: {
//        labels: ["Miercoles Desde las 2pm hasta las 04y 30", "Yoga TRX", "Lunes desde las 2 a las 4 y 30", "Domingo", "jajajja", "Martes Desde las 2pm hasta las 04y 30"],
//        datasets: [{
//            label: '# of Votes',
//            data: [12, 19, 3, 5, 2, 3],
//            backgroundColor: [
//                'rgba(255, 99, 132, 0.2)',
//                'rgba(54, 162, 235, 0.2)',
//                'rgba(255, 206, 86, 0.2)',
//                'rgba(75, 192, 192, 0.2)',
//                'rgba(153, 102, 255, 0.2)',
//                'rgba(255, 159, 64, 0.2)'
//            ],
//            borderColor: [
//                'rgba(255,99,132,1)',
//                'rgba(54, 162, 235, 1)',
//                'rgba(255, 206, 86, 1)',
//                'rgba(75, 192, 192, 1)',
//                'rgba(153, 102, 255, 1)',
//                'rgba(255, 159, 64, 1)'
//            ],
//            borderWidth: 1
//        }]
//    },
//    options: {
//        scales: {
//            yAxes: [{
//                ticks: {
//                    beginAtZero:true
//                }
//            }]
//        }
//    }
//}
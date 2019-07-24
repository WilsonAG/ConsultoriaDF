package Control;

import java.io.Serializable;
import java.sql.ResultSet;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.PieChartModel;


@Named("graficas_1")
@RequestScoped
public class grafica_anio implements Serializable{
	
   
	private static final long serialVersionUID = 1L;
	private LineChartModel lineModel2;
    private LineChartModel zoomModel;
    private CartesianChartModel combinedModel;
    private CartesianChartModel fillToZero;
    private LineChartModel areaModel;
    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
    private PieChartModel pieModel2;
    private DonutChartModel donutModel2;
    private MeterGaugeChartModel meterGaugeModel2;
    private BubbleChartModel bubbleModel2;
    private OhlcChartModel ohlcModel;
    private OhlcChartModel ohlcModel2;
    private BarChartModel animatedModel2;
    private LineChartModel multiAxisModel;
    private LineChartModel dateModel;
 
    @PostConstruct
    public void init() {
        createPieModels();
    }
 
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
 
    public LineChartModel getZoomModel() {
        return zoomModel;
    }
 
    public CartesianChartModel getCombinedModel() {
        return combinedModel;
    }
 
    public CartesianChartModel getAreaModel() {
        return areaModel;
    }
 
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
 
    public MeterGaugeChartModel getMeterGaugeModel2() {
        return meterGaugeModel2;
    }
 
    public DonutChartModel getDonutModel2() {
        return donutModel2;
    }
 
    public CartesianChartModel getFillToZero() {
        return fillToZero;
    }
 
    public BubbleChartModel getBubbleModel2() {
        return bubbleModel2;
    }
 
    public OhlcChartModel getOhlcModel() {
        return ohlcModel;
    }
 
    public OhlcChartModel getOhlcModel2() {
        return ohlcModel2;
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
 
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
 
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }
 
    public LineChartModel getMultiAxisModel() {
        return multiAxisModel;
    }
 
    public LineChartModel getDateModel() {
        return dateModel;
    }
 
    private void createPieModels() {
        createPieModel2();
    }
 
 
    private void createPieModel2() {
        pieModel2 = new PieChartModel();
        pieModel2.set("2015", 1876);
        pieModel2.set("2016", 1748);
        pieModel2.setTitle("Número de Defunciones fetales");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(500);
        pieModel2.setShadow(false);
    }
 
/*
    public int total2015() {
    	coneccion c= new coneccion();
    	ResultSet rs = null; 
    	int total2015 = 0;
    	try {
			c.iniciar_con();
			String sqlDef ="Select count (*) from tg_defuncionesfetales "
					+ "where anio_fall = 2015";
			rs= c.Consulta(sqlDef);
			while (rs.next()) {
				total2015=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return total2015;
    	
    }
    
    public int total2016() {
    	coneccion c= new coneccion();
    	ResultSet rs = null; 
    	int total2016 = 0;
    	try {
			c.iniciar_con();
			String sqlDef ="Select count (*) from tg_defuncionesfetales "
					+ "where anio_fall = 2016";
			rs= c.Consulta(sqlDef);
			while (rs.next()) {
				total2016=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return total2016;  	
    }*/
}

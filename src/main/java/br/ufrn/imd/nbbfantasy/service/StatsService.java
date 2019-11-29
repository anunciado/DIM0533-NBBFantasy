package br.ufrn.imd.nbbfantasy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;

import br.ufrn.imd.nbbfantasy.dao.StatsAssistsDao;
import br.ufrn.imd.nbbfantasy.dao.StatsPointsDao;
import br.ufrn.imd.nbbfantasy.dao.StatsReboundsDao;
import br.ufrn.imd.nbbfantasy.entity.Player;
import br.ufrn.imd.nbbfantasy.entity.StatsAssists;
import br.ufrn.imd.nbbfantasy.entity.StatsPoints;
import br.ufrn.imd.nbbfantasy.entity.StatsRebounds;

@Service
public class StatsService {

    @Autowired
	private StatsPointsDao statsPointsDao;
    
    @Autowired
	private StatsReboundsDao statsReboundsDao;
    
    @Autowired
	private StatsAssistsDao StatsAssistsDao;

    public double getPontosPredicao(Player player) {
		String split[]= StringUtils.split(player.getName());
		List<StatsPoints> stats = findStatsPointsByName(split[1]+" "+split[0]);
		List<Double> pointsByWeeks = stats.stream()
							                .map(StatsPoints::getPoints)
							                .collect(Collectors.toList());
		return generatePredicao(pointsByWeeks);
	}
    
    public double getRebotesPredicao(Player player) {
		String split[]= StringUtils.split(player.getName());
		List<StatsRebounds> stats = findStatsReboundsByName(split[1]+" "+split[0]);
		List<Double> pointsByWeeks = stats.stream()
							                .map(StatsRebounds::getRebounds)
							                .collect(Collectors.toList());
		return generatePredicao(pointsByWeeks);
	}
    
    public double getAssistenciasPredicao(Player player) {
		String split[]= StringUtils.split(player.getName());
		List<StatsAssists> stats = findStatsAssistsByName(split[1]+" "+split[0]);
		List<Double> pointsByWeeks = stats.stream()
							                .map(StatsAssists::getAssists)
							                .collect(Collectors.toList());
		return generatePredicao(pointsByWeeks);
	}

	private double generatePredicao(List<Double> pointsByWeeks) {	
		double[] dataArray = new double[pointsByWeeks.size()];
		 for (int i = 0; i < dataArray.length; i++) {
			 dataArray[i] = pointsByWeeks.get(i);
		 }

		// Set ARIMA model parameters.
		int p = 3;
		int d = 0;
		int q = 3;
		int P = 1;
		int D = 1;
		int Q = 0;
		int m = 0;
		int forecastSize = 1;

		ArimaParams paramsForecast = new ArimaParams(p, d, q, P, D, Q, m);
		// Obtain forecast result. The structure contains forecasted values and performance metric etc.
		ForecastResult forecastResult = Arima.forecast_arima(dataArray, forecastSize, paramsForecast);

		// Read forecast values
		double[] forecastData = forecastResult.getForecast(); 
		double number = forecastData[0];
		int aux = (int)(number*100);//1243
		double result = aux/100d;//12.43
		return result;
	}
	
	public String getPointsLink(Player player) {
		String split[]= StringUtils.split(player.getName());
		List<StatsPoints> stats = findStatsPointsByName(split[1]+" "+split[0]);
		List<Double> pointsByWeeks = stats.stream()
							                .map(StatsPoints::getPoints)
							                .limit(7)
							                .collect(Collectors.toList());
		System.out.print(pointsByWeeks);
		return "https://quickchart.io/chart?c={type:'line',data:{labels:['Semana 1','Semana 2', 'Semana 3','Semana 4', 'Semana 5', 'Semana 6', 'Semana 7'], datasets:[{label:'Pontos',data:" + pointsByWeeks.toString() + "}]}}";
	}
	
	public String getReboundsLink(Player player) {
		String split[]= StringUtils.split(player.getName());
		List<StatsRebounds> stats = findStatsReboundsByName(split[1]+" "+split[0]);
		List<Double> pointsByWeeks = stats.stream()
							                .map(StatsRebounds::getRebounds)
							                .limit(7)
							                .collect(Collectors.toList());
		return "https://quickchart.io/chart?c={type:'line',data:{labels:['Semana 1','Semana 2', 'Semana 3','Semana 4', 'Semana 5', 'Semana 6', 'Semana 7'], datasets:[{label:'Rebotes',data:" + pointsByWeeks.toString() + "}]}}";
	}
	
	public String getAssistsLink(Player player) {
		String split[]= StringUtils.split(player.getName());
		List<StatsAssists> stats = findStatsAssistsByName(split[1]+" "+split[0]);
		List<Double> pointsByWeeks = stats.stream()
							                .map(StatsAssists::getAssists)
							                .limit(7)
							                .collect(Collectors.toList());
		return "https://quickchart.io/chart?c={type:'line',data:{labels:['Semana 1','Semana 2', 'Semana 3','Semana 4', 'Semana 5', 'Semana 6', 'Semana 7'], datasets:[{label:'Rebotes',data:" + pointsByWeeks.toString() + "}]}}";
	}
    
    public List<StatsPoints> findStatsPointsByName(String name) {
        return statsPointsDao.findByName(name);
    }
    
    public List<StatsRebounds> findStatsReboundsByName(String name) {
        return statsReboundsDao.findByName(name);
    }
    
    public List<StatsAssists> findStatsAssistsByName(String name) {
        return StatsAssistsDao.findByName(name);
    }
}

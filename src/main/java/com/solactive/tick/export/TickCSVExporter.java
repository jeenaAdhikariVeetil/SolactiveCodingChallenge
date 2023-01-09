/**
 * 
 */
package com.solactive.tick.export;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.solactive.tick.consumer.TickConsumer;
import com.solactive.tick.model.Tick;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Jeena A V
 *
 */

@Component
public class TickCSVExporter {

	public void export(String ric, HttpServletResponse httpServletResponse) throws IOException {
		
		List<Tick> listTicks = TickConsumer.getTickMap().get(ric);
		String fileName = "tickValues_" + ric + ".csv";
		httpServletResponse.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; fileName=" + fileName;
		httpServletResponse.setHeader(headerKey, headerValue);
		ICsvBeanWriter csvWriter = new CsvBeanWriter(httpServletResponse.getWriter(),
				CsvPreference.STANDARD_PREFERENCE);
		String[] csvHeader= {"TIMESTAMP","PRICE","CLOSE_PRICE","CURRENCY","RIC"};
		String[] mapping= {"timeStamp","price","closePrice","currency","ric"};
		csvWriter.writeHeader(csvHeader);
		for(Tick tick:listTicks)
		{
			if(tick.getClosePrice()!=0)
			csvWriter.write(tick, mapping);
		}
		
		csvWriter.close();
		
	}

}

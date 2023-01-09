/**
 * 
 */
package com.solactive.tick.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jeena A V
 *
 */
public class TickReponse {
private List<Tick> tickList;

public List<Tick> getTickList() {
	return tickList;
}

public void setTickList(List<Tick> tickList) {
	this.tickList = tickList;
}

public TickReponse(List<Tick> tickList) {
	this.tickList = tickList;
}

public TickReponse() {
}

public List<Tick> addTickValues(Tick tick,Map<String, List<Tick>> tickvaluesMap)
{
	List<Tick> tickList;
	if(!tickvaluesMap.containsKey(tick.getRic()))
	{
		tickList=new ArrayList<>();
		tickList.add(tick);
	}
	else
	{
		tickList=tickvaluesMap.get(tick.getRic());
		tickList.add(tick);
	}
	return tickList;
}
}

package com.assignment.application;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.assignment.model.DateRange;

public class DateMerger {
	    
    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy");
   
	
	public static void main(String[] args) throws ParseException {
		
		DateMerger dateMerger = new DateMerger();
		List<DateRange> dateRanges = new ArrayList<>();
		
		//TestCase I
		dateRanges.add(new DateRange(LocalDate.parse("01 Jan 2014", df ), LocalDate.parse("30 Jan 2014", df)));
		dateRanges.add(new DateRange(LocalDate.parse("15 Jan 2014", df), LocalDate.parse("15 Feb 2014", df)));
		dateRanges.add(new DateRange(LocalDate.parse("10 Mar 2014", df), LocalDate.parse("15 Apr 2014", df)));
		dateRanges.add(new DateRange(LocalDate.parse("10 Apr 2014", df), LocalDate.parse("15 May 2014", df)));
		
		//TestCase2
		 /*dateRanges.add(new DateRange(LocalDate.parse("01 Jan 2014", df ), LocalDate.parse("15 Jan 2014", df)));
		 dateRanges.add(new DateRange(LocalDate.parse("16 Jan 2014", df), LocalDate.parse("30 Jan 2014", df)));*/
		

		//TestCase3
		/*dateRanges.add(new DateRange(LocalDate.parse("01 Jan 2014", df ), LocalDate.parse("15 Jan 2014", df)));
		dateRanges.add(new DateRange(LocalDate.parse("15 Jan 2014", df), LocalDate.parse("30 Jan 2014", df)));
		*/
		
		List<DateRange> resultList = dateMerger.mergeDates(dateRanges);
		
		for(DateRange dateRange : resultList)
		{
			System.out.println(dateRange.getStartDate().format(df) + "-" + dateRange.getEndDate().format(df));
		}
			
	}
	
	public List<DateRange> mergeDates(List<DateRange> dateRanges)
	{
		List<DateRange> resultList = new ArrayList<DateRange>();
		DateRange[] dateRangesArray = new DateRange[dateRanges.size()];
		
		dateRanges.toArray(dateRangesArray);
		resultList.add(dateRangesArray[0]);

	      for(int i = 1; i < dateRangesArray.length; i++){
	        if( dateRangesArray[i].getStartDate().compareTo(resultList.get(resultList.size() - 1).getEndDate()) > 0  ){
	        	resultList.add(dateRangesArray[i]);
	        }else {	
	        	LocalDate startDate = resultList.get(resultList.size() - 1).getStartDate().compareTo(dateRangesArray[i].getStartDate()) > 1 ?
	        			dateRangesArray[i].getStartDate() : resultList.get(resultList.size() - 1).getStartDate();  
	        	LocalDate endDate = resultList.get(resultList.size() - 1).getEndDate().compareTo(dateRangesArray[i].getEndDate()) 
	        			< 1 ? dateRangesArray[i].getEndDate() : resultList.get(resultList.size() - 1).getEndDate(); 
	        	resultList.set(resultList.size() - 1, new DateRange(startDate, endDate));
	        }
	      }
		return resultList;
	}
	
}

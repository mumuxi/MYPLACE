package sus.scrofa.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TestMisc {

	@Test
	public void test() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//sdf.parse("1991-6-2 21:5:16");
			// util -> sql
			java.sql.Timestamp sqlDate = new java.sql.Timestamp(sdf.parse(
					"1991-6-2 21:5:16").getTime());
			System.out.println(sqlDate);
			System.out.println(new Date(sqlDate.getTime()));
			/*
			System.out.println(sqlDate.getYear() + "-" + sqlDate.getMonth()
					+ "-" + sqlDate.getDate() + " " + sqlDate.getHours() + ":"
					+ sqlDate.getMinutes() + ":" + sqlDate.getSeconds());
			*/
			// sql -> util
			java.util.Date utilDate = new java.util.Date(sdf.parse(
					"1991-6-2 21:5:16").getTime());
			System.out.println(utilDate.getHours());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class TimeManager implements Serializable{

  /**
   * Returns the Date object corresponding to the Date instance at which this method is called.
   *
   * @return    The Date object for this instant.
   */
  protected Date getDate() {
    return new Date();
  }

  /**
   * This method returns the String of today's date, but does not include the time.
   *
   * @return    Formatted String of today's date
   */
  protected String getTodaysDate() {
    Date d = new Date();
    String[] values = d.toString().split(" ");
    return values[0] + values[1] + values[2] + values[5];
  }

  /**
   * This method checks if the current instant is one a different date
   * than the instance defined in the parameter
   *
   * @param reference   The string date we wish to compare to
   * @return            Returns whether the current date and specified date are different
   */
  protected boolean checkDifferentDates(String reference) {
    return before(reference) || after(reference);
  }

  /**
   * Returns the formatted String of the current Date instant.
   *
   * @return    Formatted String of the current date instance.
   */
  protected String timeStamp() {
    Date d = new Date();
    return d.toString();
  }

  /**
   * This method checks whether the current instance is before the reference instance
   * specified in the parameter.
   *
   * @param reference   The string of the date to be compared to
   * @return            Whether or not the current date is before the reference
   */
  protected boolean before(String reference) {
    Date d = new Date();
    String[] cur_values = d.toString().split(" ");
    String[] ref_values = reference.split(" ");

    String inter_cur_month = cur_values[1];
    int cur_month = 0;
    switch (inter_cur_month) {
      case "Jan":
        cur_month = 1;
        break;

      case "Feb":
        cur_month = 2;
        break;

      case "Mar":
        cur_month = 3;
        break;

      case "Apr":
        cur_month = 4;
        break;

      case "May":
        cur_month = 5;
        break;

      case "Jun":
        cur_month = 6;
        break;

      case "Jul":
        cur_month = 7;
        break;

      case "Aug":
        cur_month = 8;
        break;

      case "Sep":
        cur_month = 9;
        break;

      case "Oct":
        cur_month = 10;
        break;

      case "Nov":
        cur_month = 11;
        break;

      case "Dec":
        cur_month = 12;
        break;
    }



    int cur_date = Integer.parseInt(cur_values[2]);
    int cur_year = Integer.parseInt(cur_values[5]);
    String[] long_cur_time = cur_values[3].split(":");
    String long_cur_time2 = long_cur_time[0] + long_cur_time[1] + long_cur_time[2];
    int cur_time = Integer.parseInt(long_cur_time2);

    String inter_ref_month = ref_values[1];
    int ref_month = 0;
    switch (inter_ref_month) {
      case "Jan":
        ref_month = 1;
        break;

      case "Feb":
        ref_month = 2;
        break;

      case "Mar":
        ref_month = 3;
        break;

      case "Apr":
        ref_month = 4;
        break;

      case "May":
        ref_month = 5;
        break;

      case "Jun":
        ref_month = 6;
        break;

      case "Jul":
        ref_month = 7;
        break;

      case "Aug":
        ref_month = 8;
        break;

      case "Sep":
        ref_month = 9;
        break;

      case "Oct":
        ref_month = 10;
        break;

      case "Nov":
        ref_month = 11;
        break;

      case "Dec":
        ref_month = 12;
        break;
    }
    int ref_date = Integer.parseInt(ref_values[2]);
    int ref_year = Integer.parseInt(ref_values[5]);
    String[] long_ref_time = ref_values[3].split(":");
    String long_ref_time2 = long_ref_time[0] + long_ref_time[1] + long_ref_time[2];
    int ref_time = Integer.parseInt(long_ref_time2);

    return cur_year <= ref_year && cur_month <= ref_month && cur_date <= ref_date &&
        cur_time < ref_time;
  }

  /**
   * This method checks whether the current instance is after the reference instance
   * specified in the parameter.
   *
   * @param reference   The string of the date to be compared to
   * @return            Whether or not the current date is after the reference
   */
  protected boolean after(String reference) {
    Date d = new Date();
    String[] cur_values = d.toString().split(" ");
    String[] ref_values = reference.split(" ");

    String inter_cur_month = cur_values[1];
    int cur_month = 0;
    switch (inter_cur_month) {
      case "Jan":
        cur_month = 1;
        break;

      case "Feb":
        cur_month = 2;
        break;

      case "Mar":
        cur_month = 3;
        break;

      case "Apr":
        cur_month = 4;
        break;

      case "May":
        cur_month = 5;
        break;

      case "Jun":
        cur_month = 6;
        break;

      case "Jul":
        cur_month = 7;
        break;

      case "Aug":
        cur_month = 8;
        break;

      case "Sep":
        cur_month = 9;
        break;

      case "Oct":
        cur_month = 10;
        break;

      case "Nov":
        cur_month = 11;
        break;

      case "Dec":
        cur_month = 12;
        break;
    }
    int cur_date = Integer.parseInt(cur_values[2]);
    int cur_year = Integer.parseInt(cur_values[5]);
    String[] long_cur_time = cur_values[3].split(":");
    String long_cur_time2 = long_cur_time[0] + long_cur_time[1] + long_cur_time[2];
    int cur_time = Integer.parseInt(long_cur_time2);

    String inter_ref_month = ref_values[1];
    int ref_month = 0;
    switch (inter_ref_month) {
      case "Jan":
        ref_month = 1;
        break;

      case "Feb":
        ref_month = 2;
        break;

      case "Mar":
        ref_month = 3;
        break;

      case "Apr":
        ref_month = 4;
        break;

      case "May":
        ref_month = 5;
        break;

      case "Jun":
        ref_month = 6;
        break;

      case "Jul":
        ref_month = 7;
        break;

      case "Aug":
        ref_month = 8;
        break;

      case "Sep":
        ref_month = 9;
        break;

      case "Oct":
        ref_month = 10;
        break;

      case "Nov":
        ref_month = 11;
        break;

      case "Dec":
        ref_month = 12;
        break;
    }
    int ref_date = Integer.parseInt(ref_values[2]);
    int ref_year = Integer.parseInt(ref_values[5]);
    String[] long_ref_time = ref_values[3].split(":");
    String long_ref_time2 = long_ref_time[0] + long_ref_time[1] + long_ref_time[2];
    int ref_time = Integer.parseInt(long_ref_time2);


    return cur_year >= ref_year && cur_month >= ref_month && cur_date >= ref_date &&
        cur_time > ref_time;
  }
}

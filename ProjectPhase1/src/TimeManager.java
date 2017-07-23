import java.io.Serializable;
import java.util.Date;

public class TimeManager implements Serializable{


  // I don't think this is used
  protected Date getDate() {
    return new Date();
  }

  protected boolean checkDifferentDates(String reference) {
    return before(reference) || after(reference);
  }

  protected String timeStamp() {
    Date d = new Date();
    return d.toString();
  }

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

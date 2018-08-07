package android.attendancecalculator;

/**
 * Created by The Dark Knight on 01-08-2016.
 */
public class Attendance {

    private int _id;
    private String _date;
    private int _lecturesAttended;

    public Attendance(){
    // empty constructor
    }

    public Attendance( String _date, int _lecturesAttended){
        this._date = _date;
        this._lecturesAttended = _lecturesAttended;

    }

    public void set_id(int _id){
        this._id = _id;
    }
    public int get_id(){
        return _id;
    }

    public void set_date(String _date){ this._date = _date;}
    public String get_date(){
        return _date;
    }

    public void set_lecturesAttended(int _lecturesAttended){this._lecturesAttended = _lecturesAttended;}
    public int get_lecturesAttended(){
        return _lecturesAttended;
    }


}

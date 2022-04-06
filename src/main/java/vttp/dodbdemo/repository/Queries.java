package vttp.dodbdemo.repository;

public interface Queries {
    public final static String SQL_CHECK_RECORD_EXISTS = "select count(*) as add_success from bff where email = ?";
    public static final String SQL_CREATE_FRIEND_RECORD = "INSERT into bff (email, name, phone, dob, status, pass_phrase) values (?,?,?,?,?,sha(?))";
    public static final String SQL_GET_ALL_FRIENDS = "select * from bff";
    public static final String SQL_DELETE_FRIEND = "delete from bff where email = ?";
}

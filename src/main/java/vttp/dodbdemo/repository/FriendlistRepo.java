package vttp.dodbdemo.repository;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import vttp.dodbdemo.model.Friend;

import static vttp.dodbdemo.repository.Queries.*;

@Repository
public class FriendlistRepo {
    private Logger logger = Logger.getLogger(FriendlistRepo.class.getName());

    @Autowired
    private JdbcTemplate template;

    public boolean checkEmailExists(String email) {
        final SqlRowSet result = template.queryForRowSet(SQL_CHECK_RECORD_EXISTS, email);
        logger.log(Level.INFO, "Checking for email " + email);
        if (result.next()) {
            logger.log(Level.INFO, "add_success = " + result.getInt("add_success"));
            return result.getInt("add_success") == 1;
        }
        return false;
    }

    public boolean createFriendRecord(MultiValueMap<String, String> form) {
        int added = template.update(SQL_CREATE_FRIEND_RECORD,
                form.getFirst("email"),
                form.getFirst("name"),
                form.getFirst("phone"),
                Date.valueOf(form.getFirst("dob")),
                form.getFirst("status"),
                form.getFirst("passphrase"));
        logger.log(Level.INFO, "Successfully created " + added + " record(s)");
        return added > 0;
    }

    public List<Friend> getAllFriends() {
        List<Friend> friends = new LinkedList<>();
        final SqlRowSet result = template.queryForRowSet(SQL_GET_ALL_FRIENDS);
        while (result.next()) {
            Friend friend = Friend.createWithoutPassphrase(result);
            friends.add(friend);
        }
        logger.log(Level.INFO, "Retrieved " + friends.size() + " records");
        return friends;
    }

    public boolean deleteFriendRecord(String email) {
        int deleted = template.update(SQL_DELETE_FRIEND, email);
        logger.log(Level.INFO, "Successfully deleted " + deleted + " record(s)");
        return deleted > 0;
    }
}

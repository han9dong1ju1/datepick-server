package app.hdj.datepick.data;


import app.hdj.datepick.data.entity.UserTable;
import app.hdj.datepick.domain.dto.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.time.Instant;

public class UserMappingTest {

    private ModelMapper mapper = new ModelMapper();

    @Test
    public void userMappingTest() {
        User user = new User(0L, "", "김휘진", "");
        UserTable userTable = new UserTable(0L, "", "김휘진", "", Timestamp.from(Instant.now()));
        UserTable mappedUserTable = mapper.map(user, UserTable.class);
        assert mappedUserTable.getNickname().equals(userTable.getNickname());
    }

}

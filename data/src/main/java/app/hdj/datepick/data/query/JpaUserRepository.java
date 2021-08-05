package app.hdj.datepick.data.query;

import app.hdj.datepick.data.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface JpaUserRepository extends JpaRepository<UserTable, Long> {


}

package dopamine.dao;

import dopamine.model.User;
import java.util.ArrayList;

public interface UserDAO {

	ArrayList<User> selectUsers();

	User selectUser(String email);

	boolean emailExists(String email);

	int delete(User user);

	int update(User user);

	int insert(User user);
	
}

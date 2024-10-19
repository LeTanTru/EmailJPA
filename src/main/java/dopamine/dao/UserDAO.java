package dopamine.dao;

import dopamine.model.User;

public interface UserDAO {

	boolean emailExists(String email);

	User selectUser(String email);

	void delete(User user);

	void update(User user);

	void insert(User user);

}

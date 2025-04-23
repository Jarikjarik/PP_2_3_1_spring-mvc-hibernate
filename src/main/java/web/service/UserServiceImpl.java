package web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        logger.debug("Finding user by id: {}", id);
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        logger.debug("Getting all users");
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        logger.info("Adding new user: {}", user);
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        logger.info("Editing user: {}", user);
        userDao.editUser(user);
    }
}
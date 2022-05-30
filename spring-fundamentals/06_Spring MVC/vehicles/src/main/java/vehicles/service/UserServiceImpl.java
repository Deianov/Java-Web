package vehicles.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import vehicles.repository.UserRepository;
import vehicles.events.UserCreationEvent;
import vehicles.exception.InvalidEntityException;
import vehicles.model.enums.Role;
import vehicles.model.User;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static vehicles.constant.Constant.USER_ALREADY_EXISTS;

@Service
//@Slf4j
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());
//    LOGGER.debug("Debug Message Logged !!!");
//    LOGGER.info("Info Message Logged !!!");
//    LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public User createUser(@Valid User user) {
        repository.findByUsername(user.getUsername()).ifPresent(u -> {
            // test logger
            LOGGER.error(String.format(USER_ALREADY_EXISTS, user.getUsername()));
            throw new InvalidEntityException(String.format(USER_ALREADY_EXISTS, user.getUsername()));
        });
        user.setCreated(new Date());
        user.setModified(new Date());
        if(user.getRoles() == null || user.getRoles().size() == 0) {
            user.setRoles(Set.of(Role.SELLER));
        }
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        // test logger
        LOGGER.info(String.format("User %s was created.", user.getUsername()));
        return repository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User old = getUserById(user.getId());
        if(user.getUsername() != null && !user.getUsername().equals(old.getUsername())) {
            throw new InvalidEntityException("Username of a user could not ne changed.");
        }
        user.setModified(new Date());
        return repository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with ID=%s not found.", id)));
    }

    @Override
    public User getUserByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() ->
                new EntityNotFoundException(String.format("User '%s' not found.", username)));
    }

    @Override
    public User deleteUser(Long id) {
        User old = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with ID=%s not found.", id)));
        repository.deleteById(id);
        return old;
    }

    @Override
    public long getUsersCount() {
        return repository.count();
    }

    // Declarative transaction
    @Transactional
    public List<User> createUsersBatch(List<User> users) {
        List<User> created = users.stream()
                .map(user -> createUser(user))
                .collect(Collectors.toList());
        return created;
    }

////    Programmatic transaction
//    public List<User> createUsersBatch(List<User> users) {
//        return transactionTemplate.execute(new TransactionCallback<List<User>>() {
//            // the code in this method executes in a transactional context
//            public List<User> doInTransaction(TransactionStatus status) {
//                List<User> created = users.stream()
//                        .map(user -> {
//                            try {
//                                return createUser(user);
//                            } catch (ConstraintViolationException ex) {
//                                log.error(">>> Constraint violation inserting users: {} - {}", user, ex.getMessage());
//                                status.setRollbackOnly();
//                                return null;
//                            }
//                        }).collect(Collectors.toList());
//                return created;
//            }
//        });
//    }

    // Managing transaction directly using PlatformTransactionManager
//    public List<User> createUsersBatch(List<User> users) {
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        // explicitly setting the transaction name is something that can only be done programmatically
//        def.setName("createUsersBatchTransaction");
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        def.setTimeout(5);
//
//        // Do in transaction
//        TransactionStatus status = transactionManager.getTransaction(def);
//        List<User> created = users.stream()
//            .map(user -> {
//                try {
//                    User resultUser = createUser(user);
//                    applicationEventPublisher.publishEvent(new UserCreationEvent(resultUser));
//                    return resultUser;
//                } catch (ConstraintViolationException ex) {
//                    log.error(">>> Constraint violation inserting user: {} - {}", user, ex.getMessage());
//                    transactionManager.rollback(status); // ROLLBACK
//                    throw ex;
//                }
//            }).collect(Collectors.toList());
//
//        transactionManager.commit(status); // COMMIT
//        return created;
//    }

    @TransactionalEventListener
    public void handleUserCreatedTransactionCommit(UserCreationEvent creationEvent) {
//        log.info(">>> Transaction COMMIT for user: {}", creationEvent.getUser());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleUserCreatedTransactionRollaback(UserCreationEvent creationEvent) {
//        log.info(">>> Transaction ROLLBACK for user: {}", creationEvent.getUser());
    }

}

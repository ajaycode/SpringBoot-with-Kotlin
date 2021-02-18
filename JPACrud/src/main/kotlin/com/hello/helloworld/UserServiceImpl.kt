package com.hello.helloworld

import com.hello.helloworld.model.User
import com.hello.helloworld.repository.UserRepository
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl : UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    companion object {
        val logger = LogManager.getLogger(javaClass.enclosingClass)
    }

    override fun addUser(user: User): User {

        logger.info("User to be added : " + user.firstName + " " + user.lastName + " " + user.email)
        return userRepository.save(user)
    }

    override fun getUsersByFirstNameIgnoreCase(first_name: String): Iterable<User> {
        logger.info("Searching for users with the first name : $first_name (ignoring case)")
        var users = userRepository.findByFirstName(first_name)
        logger.info("Found users : $users")
        for (user: User in users) {
            logger.info("Found user : " + user.firstName)
        }
        return users
    }


    override fun deleteUser(userid: Long) : Boolean {
        logger.info("Deleting employee # $userid")
        var status: Boolean = true;
        if (userRepository.existsById(userid)) {
            logger.info(" employee # $userid exists.")
            try {
                userRepository.deleteById(userid)
            }
            catch (e: Exception)
            {
                logger.error("Error occurred deleting userid $userid")
                status = false
            }
        } else {
            logger.info(" employee # $userid does not exist.")
            status = false
        }
        return status
    }

    //currently set to update the first_name only
    override fun updateUser(userid: Long, user: User): User {
        if (userRepository.existsById(userid)) {
            logger.info("Updating user information for user # : $userid")
            var optionalUser: Optional<User> = userRepository.findById(userid)
            var user_in_repo: User = if (optionalUser.isPresent) optionalUser.get() else User()


            logger.info("Value of user from repo is $user_in_repo")
            //user_in_repo.First_Name = user.First_Name
            //Preserve the time of creation of the object with reuse of CreateTime
            user_in_repo = user.copy(userId = userid, createTime = user_in_repo.createTime)

            return userRepository.save(user_in_repo)
        } else {
            TODO("If no such user exists, then a user object should not be returned. To fix")
            return User()
        }

    }

    override fun updatePassword(userid: Long, newPassword: String): Boolean {
        TODO()
    }

    override fun existsByUser_Id(userid: Long): Boolean {
        return userRepository.existsById(userid)
    }

    override fun getUserById(id: Long): User {

        var optionalUser : Optional <User> = userRepository.findById(id)
        var user : User = if (optionalUser.isPresent) optionalUser.get() else User ()
        return user
        //TODO("Should not return new User(), if the user is not available in the repository")
    }
}
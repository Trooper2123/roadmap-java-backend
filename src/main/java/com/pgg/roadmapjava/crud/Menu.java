package com.pgg.roadmapjava.crud;

import com.pgg.roadmapjava.user.User;
import com.pgg.roadmapjava.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class Menu {

    @Autowired
    private UserService userService;

    private char answer;
    private static Scanner input;
    private static final Logger logger = LoggerFactory.getLogger(Menu.class);
    public Menu() {
    }

    public void initCrud() {

        input = new Scanner(System.in);


        System.out.println(
                "Choose operation: \n"
                        + "C - Create new Client \n"
                        + "R - Look for a client \n"
                        + "U - Update Cliente \n"
                        + "D - Delet Client\n"
        );

        answer = input.next().toUpperCase().charAt(0);

        chooseOperation(answer);

        input.close();
    }

    public void chooseOperation(char answer) {
        switch (answer) {
            case 'C':
                this.createUser();
                break;
            case 'R':
                this.findUser();
                break;
            case 'U':
                this.updateUser();
                break;
            case 'D':
                this.deleteUser();
                break;
            default:
                logger.error("NOT A VALID OPTION");
        }
    }

    public void createUser() {
        User user = inputUser();
        userService.insertUser(user);
        loopMenu();
    }

    public void findUser() {
        logger.info("One client (o) or (a) for all clients: ");
        this.answer = input.next().toUpperCase().charAt(0);
        if (answer == 'A') {
            for (User user : userService.findAllUsers()) {
                System.out.println(user);
            }
        } else if (answer == 'O') {
            System.out.println("Insert CPF: ");
            String cpf = input.next();
            for (User user : userService.findUserByCPF(cpf)) {
                System.out.println(user);
            }
        } else {
            logger.error("Not a valid option");
        }

        loopMenu();
    }

    public void deleteUser() {
        logger.info("Insert CPF: ");
        String cpf = input.next();
        logger.info("Wish to delete permanently this user ? y/n ");
        this.answer = input.next().charAt(0);
        if (answer == 'y') {
            userService.deleteUserByCPF(cpf);
            logger.info("Delete operation succeed");
        } else {
            logger.info("Delete operation terminated");
        }

        loopMenu();
    }

    public void updateUser() {

        logger.info("Insert CPF: ");
        String cpf = input.next();
        List<User> userListRetunedByCPF = userService.findUserByCPF(cpf);

        if(userListRetunedByCPF.size() == 1) {
            User user = userListRetunedByCPF.get(0);
            System.out.println(user);
            logger.info("Wish to update this user ? y/n");
            answer = input.next().toUpperCase().charAt(0);
            input.nextLine();
            if (answer == 'Y') {
                inputUpdate(user);
               userService.updateUserByCPF(cpf,user);
            }
        }else if (userListRetunedByCPF.size() == 0){
           logger.error("User not found.");
        }else{
            System.out.println("More than one user for this CPF." + userListRetunedByCPF);
        }

        loopMenu();
    }

    public void inputUpdate(User user){

        String name,phone,anniversary,email,address;

        logger.info("Insert your name: ");
        name = input.nextLine();

        logger.info("Insert your phone: ");
        phone = input.nextLine();

        logger.info("Insert your anniversary: ");
        anniversary = input.nextLine();

        logger.info("Insert your best e-mail: ");
        email = input.nextLine();

        logger.info("Insert your address: ");
        address = input.nextLine();


        user.setName(name);
        user.setPhone(phone);
        user.setAnniversary(anniversary);
        user.setEmail(email);
        user.setAddress(address);

        System.out.println(user);
    }

    public User inputUser (){
        User user = new User();

        String name,phone,anniversary,cpf,email,address;

        logger.info("Insert your name: ");
        name = input.nextLine();

        logger.info("Insert your phone: ");
        phone = input.nextLine();

        logger.info("Insert your anniversary: ");
        anniversary = input.nextLine();

        logger.info("Insert your CPF");
        cpf = input.nextLine();

        logger.info("Insert your best e-mail: ");
        email = input.nextLine();

        logger.info("Insert your address: ");
        address = input.nextLine();


        user.setName(name);
        user.setPhone(phone);
        user.setAnniversary(anniversary);
        user.setCpf(cpf);
        user.setEmail(email);
        user.setAddress(address);

        System.out.println(user);
        return user;
    }

    public void loopMenu() {
        char menuAnswer;
        logger.info("Do you wish to do another action? y/n");
        menuAnswer = input.next().toUpperCase().charAt(0);

        switch (menuAnswer) {
            case 'Y':
                initCrud();
                break;
            case 'N':
                logger.info("Have a good day ! Bay :D ");
                break;
            default:
                logger.error("NOT A VALID OPTION!");
        }
    }
}
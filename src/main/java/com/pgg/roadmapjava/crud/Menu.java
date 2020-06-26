package com.pgg.roadmapjava.crud;

import com.pgg.roadmapjava.user.User;
import com.pgg.roadmapjava.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class Menu {

    @Autowired
    private UserService userService;

    private char ans;
    private static Scanner input;


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

        ans = input.next().toUpperCase().charAt(0);

        chooseOperation(ans);

        input.close();
    }

    public void chooseOperation(char ans) {
        switch (ans) {
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
                System.out.println("NOT A VALID OPTION");
        }
    }

    public void createUser() {
        User user = inputUser();
        userService.insertUser(user);
        loopMenu();
    }

    public void findUser() {
        System.out.println("One client (o) or (a) for all clients: ");
        this.ans = input.next().toUpperCase().charAt(0);
        if (ans == 'A') {
            for (User user : userService.findAllUsers()) {
                System.out.println(user);
            }
        } else if (ans == 'O') {
            System.out.println("Insert CPF: ");
            String cpf = input.next();
            for (User user : userService.findUserByCPF(cpf)) {
                System.out.println(user);
            }
        } else {
            System.out.println("Not a valid option");
        }

        loopMenu();
    }

    public void deleteUser() {
        System.out.println("Insert CPF: ");
        String cpf = input.next();
        System.out.println("Wish to delete permanently this user ? y/n ");
        this.ans = input.next().charAt(0);
        if (ans == 'y') {
            userService.deleteUserByCPF(cpf);
            System.out.println("Delete operation succeed");
        } else {
            System.out.println("Delete operation terminated");
        }

        loopMenu();
    }

    public void updateUser() {

        System.out.println("Insert CPF: ");
        String cpf = input.next();
        List<User> userListRetunedByCPF = userService.findUserByCPF(cpf);

        if(userListRetunedByCPF.size() == 1) {
            User user = userListRetunedByCPF.get(0);
            System.out.println(user);
            System.out.println("Wish to update this user ? y/n");
            ans = input.next().toUpperCase().charAt(0);
            input.nextLine();
            if (ans == 'Y') {
                inputUpdate(user);
               userService.updateUserByCPF(cpf,user);
            }
        }else if (userListRetunedByCPF.size() == 0){
            System.out.println("User not found.");
        }else{
            System.out.println("More than one user for this CPF." + userListRetunedByCPF);
        }

        loopMenu();
    }

    public void inputUpdate(User user){

        String name,phone,anniversary,email,address;

        System.out.println("Insert your name: ");
        name = input.nextLine();

        System.out.println("Insert your phone: ");
        phone = input.nextLine();

        System.out.println("Insert your anniversary: ");
        anniversary = input.nextLine();

        System.out.println("Insert your best e-mail: ");
        email = input.nextLine();

        System.out.println("Insert your address: ");
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

        System.out.println("Insert your name: ");
        name = input.nextLine();

        System.out.println("Insert your phone: ");
        phone = input.nextLine();

        System.out.println("Insert your anniversary: ");
        anniversary = input.nextLine();

        System.out.println("Insert your CPF");
        cpf = input.next();

        System.out.println("Insert your best e-mail: ");
        email = input.nextLine();

        System.out.println("Insert your address: ");
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
        System.out.println("Do you wish to do another action? y/n");
        menuAnswer = input.next().toUpperCase().charAt(0);

        switch (menuAnswer) {
            case 'Y':
                initCrud();
                break;
            case 'N':
                System.out.println("Have a good day ! Bay :D ");
                break;
            default:
                System.out.println("NOT A VALID OPTION!");
        }
    }

}

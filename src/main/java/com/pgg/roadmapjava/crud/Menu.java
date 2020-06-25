package com.pgg.roadmapjava.crud;

import com.pgg.roadmapjava.user.User;
import com.pgg.roadmapjava.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class Menu {

    
       private char ans;
       private Scanner input;
       
    @Autowired
       private UserService userService;


        public Menu() {}

        public void initCrud(){

            this.input = new Scanner(System.in);


            System.out.println("Choose operation: \n"
                    + "C - Create new Client \n"
                    + "R - Look for a client \n"
                    + "U - Update Cliente \n"
                    + "D - Delet Client\n"
            );

            this.ans = input.next().toUpperCase().charAt(0);

            this.chooseOperation(ans);
        }

        public void chooseOperation(char ans) {
            switch (ans) {
                case 'C':
                    this.create();
                    break;
                case 'R':
                    this.look();
                    break;
                case 'U':
                    this.update();
                    break;
                case 'D':
                    this.deleteCient();
                    break;
                default:
                    System.out.println(" NOT A VALID OPTION");
            }
        }

        public void create() {
            User user = new User();
            System.out.println("Insert your name: ");
            String name = input.next();
            user.setName(name);
            System.out.println("Insert your phone: ");
            String phone = input.next();
            user.setPhone(phone);
            System.out.println("Insert your anniversary: ");
            String anniversary = input.next();
            user.setAnniversary(anniversary);
            System.out.println("Insert your CPF");// implement mask
            String cpf = input.next();
            user.setCpf(cpf);
            System.out.println("Insert your best e-mail: ");
            String email = input.next();
            user.setEmail(email);
            System.out.println("Insert your address: ");
            String address = input.next();
            user.setAddress(address);

            userService.insertUser(user);
        }

        public void look() {
            System.out.println("One client (o) or (a) for all clients: ");
            this.ans = input.next().toUpperCase().charAt(0);
            if (ans == 'A') {
            // userService.findAllUsers();
             for(User user : userService.findAllUsers()){
                 System.out.println(user);
             }
            } else if (ans == 'O') {
                System.out.println("Insert CPF: ");
                String cpf = input.next();
            userService.findUserByCPF(cpf);
            } else {
                System.out.println("Not a valid option");
            }

            initCrud();
        }

        public void deleteCient() {
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

        }

        public void update() {
            System.out.println("Insert CPF: ");
            String cpf = input.next();
            userService.findUserByCPF(cpf);
            System.out.println("Wish to update this user ? y/n");
            this.ans = input.next().charAt(0);

            if (ans == 'Y'){

                User user = new User();
                userService.updateUserByCPF(cpf,user);

                System.out.println("Insert your name: ");
                String name = input.next();
                user.setName(name);
                System.out.println("Insert your phone: ");
                String phone = input.next();
                user.setPhone(phone);
                System.out.println("Insert your anniversary: ");
                String anniversary = input.next();
                user.setAnniversary(anniversary);
                System.out.println("Insert your best e-mail: ");
                String email = input.next();
                user.setEmail(email);
                System.out.println("Insert your address: ");
                String address = input.next();
                user.setAddress(address);

            }
        }

}

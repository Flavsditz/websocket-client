package com.example.websocketclient.controller;

import java.util.Scanner;

/**
 * This class is used for coordinating the LOGIN process
 * with the server.
 *
 * In the current version there is no login so the only thing necessary is the
 * user's name.
 */
public class LoginController {

    public String login(){
        Scanner scanner = new Scanner(System.in);

        String userName = null;
        while (userName == null) {
            System.out.print("Type in your username: ");
            String maybeUserName = scanner.nextLine();

            if (!maybeUserName.trim().equals("")) {
                userName = maybeUserName;
            }
        }

        return userName;
    }

}

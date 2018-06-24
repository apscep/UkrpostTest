Feature: Testing of personal account

In order to check validation to personal account
As a personal account user
I want to enter to personal account with valid and invalid password

@user
Scenario:  User  enters personal account

Given User go to the main page 
When user input correct login in the login frame, enter correct password in the password frame, click enter button
Then user enter personal account

Given User go to the main page 
When user input incorrect login  in the login frame, enter incorrect password in the password frame, click enter button
Then user can't enter personal account, got error message


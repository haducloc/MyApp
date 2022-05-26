# MyApp
Test Jakarta EE Security - Wildfly

# How to deploy
## Install Wildfly 26 for Jakarta EE
   - Need to activate Jakarta EE Security in the Wildfly 26

## Deploy MyApp-1.0.war into the Wildfly

## Steps to produce the logout issue:

### Step1: http://localhost:8080/MyApp/TestServlet
- You will see User is Null (Good)
### Step2: Click on the login link in the TestServlet page
- The app will process a login, then redirect to the TestServlet again
- You will see User is User1 & the created session id ( Good )
### Step3: Click on the logout link in the TestServlet page
- The app will process a request.logout(), then redirect to the TestServlet again
- You will see User is User1 & the same session id (WRONG) -> The user must be NULL because the request.logout().

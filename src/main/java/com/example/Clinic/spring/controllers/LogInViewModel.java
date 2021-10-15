package com.example.Clinic.spring.controllers; // best practices: packages should be all lowercase

import com.example.Clinic.spring.model.User;
import com.example.Clinic.spring.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver(DelegatingVariableResolver.class)
public class LogInViewModel {

    @WireVariable
    private AuthenticationService authenticationService;
    private String username;
    private String password;
    private String errorMessage;


    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geterrorMessage() {
        return errorMessage;
    }

    public void setMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    @Command("login")
    @NotifyChange({"errorMessage", "role"})
    public void doLogin() {


        if (!authenticationService.authenticate(username, password)) {
            errorMessage = "User ID  or password is/are not correct.";

        } else {


            User user = authenticationService.getUserData();
            // message="Welcome, " + user.getUsername();


            role = authenticationService.getUserRole(user.getId());
            if (role.equals("superuser")) {
                Executions.sendRedirect("~./zul/private/doctor/all-doctors.zul");
            } else if (role.equals("doctor")) {

                Executions.sendRedirect("~./zul/private/appointment/my-appointments.zul");
            } else {
                Executions.sendRedirect("~./zul/private/appointment/book-appointment.zul");
            }


        /*String requested = (String) Executions.getCurrent().getSession().getAttribute(TaskConstants.SessionKeys.SESSION_ATTR_REQUEST_URL);
        if (requested != null && !requested.isEmpty()) {
            System.out.println(requested);
            Executions.getCurrent().getSession().removeAttribute(TaskConstants.SessionKeys.SESSION_ATTR_REQUEST_URL);
            Executions.sendRedirect(requested);*/
        }
    }

}


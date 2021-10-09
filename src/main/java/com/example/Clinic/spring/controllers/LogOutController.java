package com.example.Clinic.spring.controllers;;

import com.example.Clinic.spring.services.AuthenticationService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class LogOutController  extends SelectorComposer<Component> {

    AuthenticationService authService = new AuthenticationService();

    @Listen("onClick=#logout")
    public void doLogout(){
        authService.logout();
        Executions.sendRedirect("/zkau/web/zul/login.zul");
    }
}

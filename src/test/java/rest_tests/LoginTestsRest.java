package rest_tests;

import api_rest.AuthentificationController;
import dto.RegistrationBodyDto;
import interfaces.BaseApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginTestsRest extends AuthentificationController {
    RegistrationBodyDto user;

    @BeforeMethod
    public  void  registrationUser(){
        int i = new Random().nextInt(1000);
         user = RegistrationBodyDto.builder()
                .username("sethy"+i +"@gmail.dto")
                .password("Parol123!")
                .firstName("Vasia")
                .lastName("Popov")
                .build();
        System.out.println("registration result-->"+registrationLogin(user, REGISTRATION_URL).getStatusCode());
        System.out.println(user);
    }

    @Test
    public void loginPositiveTest(){
        Assert.assertEquals(registrationLogin(user,LOGIN_URL).getStatusCode(),200);
    }

    @Test
    public void  loginNegativeTest_UnregUser(){
        user.setUsername("Fraza123@gmail.fto");
        Response response = registrationLogin(user,LOGIN_URL);
        System.out.println(response.getBody().print());
        Assert.assertEquals(response.getStatusCode(), 401);

    }
}

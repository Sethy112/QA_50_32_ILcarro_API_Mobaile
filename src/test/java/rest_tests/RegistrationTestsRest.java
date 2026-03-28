package rest_tests;

import api_rest.AuthentificationController;
import dto.ErrorMessageDto;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Random;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.mapper.ObjectMapperType.GSON;

public class RegistrationTestsRest extends AuthentificationController {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("sethy@gmail.dto")
                .password("Parol123!")
                .firstName("Vasia")
                .lastName("Popov")
                .build();
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(), 200);
    }

    @Test
    public void registrationNegativeTest_WrongEmail() {
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("sethy" + i + "gmail.dto")
                .password("Parol123!")
                .firstName("Vasia")
                .lastName("Popov")
                .build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode()
                , 400, "validate Statuse Code");
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        System.out.println(errorMessageDto);
        softAssert.assertEquals(errorMessageDto.getError(), "Bad Request", "validate error name");
        softAssert.assertTrue(errorMessageDto.getMessage().toString().contains("must be a well-formed email address")
                , "validate message");
        softAssert.assertAll();
    }


}

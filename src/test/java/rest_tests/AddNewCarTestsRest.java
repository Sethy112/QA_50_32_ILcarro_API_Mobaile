package rest_tests;

import api_rest.CarController;
import dto.CarDto;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class AddNewCarTestsRest extends CarController {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void addNewCarPositiveTest(){
        int i = new Random().nextInt(1000)+2000;
        CarDto car = CarDto.builder()
                .serialNumber("878-"+i)
                .manufacture("Suzuki")
                .model("Baleno")
                .year("2017")
                .fuel("Gas")
                .seats(5)
                .carClass("A")
                .pricePerDay(20.0)
                .about(" it is coolest car")
                .city("Haifa")
                .build();
        System.out.println(car);
        Response response = addNewCar(car);
        System.out.println(response.getBody().print());
        softAssert.assertEquals(response
                        .getStatusCode(),200,
                "validate status code");
        softAssert.assertTrue(response
                        .getBody()
                        .print()
                        .contains("Car added successfully"),
                "validate message");
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeTest_DuplicateSerialNumber(){
        CarDto car = CarDto.builder()
                .serialNumber("878-2198")
                .manufacture("Suzuki")
                .model("Baleno")
                .year("2017")
                .fuel("Gas")
                .seats(5)
                .carClass("A")
                .pricePerDay(20.0)
                .about(" it is coolest car")
                .city("Haifa")
                .build();
        System.out.println(car);
        Response response = addNewCar(car);
        System.out.println(response.getBody().print());
        softAssert.assertEquals(response.
                        getStatusCode()
                ,400
                ,"validate status code");
        softAssert.assertTrue(response
                        .getBody()
                        .print()
                        .contains("Car with serial number 878-2198 already exists")
                ,"validate massage");
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeTest_WrongToken(){
        int i = new Random().nextInt(1000)+2000;
        CarDto car = CarDto.builder()
                .serialNumber("878-"+i)
                .manufacture("Suzuki")
                .model("Baleno")
                .year("2017")
                .fuel("Gas")
                .seats(5)
                .carClass("A")
                .pricePerDay(20.0)
                .about(" it is coolest car")
                .city("Haifa")
                .build();
        System.out.println(car);
        Response response = addNewCarNegative_WrongToken(car
                ,"c3ViIjoic2V0aHlAZ21haWwuZHRvIiwiaXNzIjoiUmVndWxhaXQiLCJleH");
        System.out.println(response.getBody().print());
        softAssert.assertEquals(response
                        .getStatusCode()
                ,401
                , "validate status code");
        softAssert.assertTrue(response
                        .getBody()
                        .print()
                        .contains("Unauthorized")
                ,"validate error");
        softAssert.assertAll();
    }
}
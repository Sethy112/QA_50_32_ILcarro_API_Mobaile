package rest_tests;

import api_rest.CarController;
import dto.CarsDto;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetAllUserCarsTestsRest extends CarController {
    CarsDto cars;
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void  getAllUserCarsPositiveTest(){
        Response response= getAllUserCars();
        cars = response.getBody().as(CarsDto.class);
        System.out.println(cars);
        softAssert.assertEquals(response.getStatusCode(), 200, "validate statusCode");
        softAssert.assertTrue(cars.getCars()[0]
                        .getManufacture().contains("Suzuki")
                ,"validate manufacture");
        softAssert.assertAll();
    }
}

package com.jr.example.ut.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.jr.example.api.dto.Message;
import com.jr.example.api.service.GreetingService;

/**
 * Unit test cases for GreetingService
 */
@ActiveProfiles("unittest")
@ComponentScan
@SpringBootTest

public class GreetingServiceTest {   
    @Autowired
    private GreetingService gService;

    @MockBean
	private Supplier<Instant> serverTimeSupplier;

    /**
     * Test the service with null input
     */
    @Test
    public void testWithNullInput(){
        given(serverTimeSupplier.get()).willReturn(ZonedDateTime.of(2023,8,1,10,0,0,0, ZoneId.of("CET"))
                                                                .toInstant());
        Exception ex = assertThrows(IllegalArgumentException.class, ()-> {
                                                                                    gService.sayHello(null);
                                                                                });
        String sExpectedErrMsg = "Input param 'name' cannot be null for method sayHello(String name)";                                                                        
        assertTrue(ex.getMessage().contains(sExpectedErrMsg));                                                                        
    }

    /** Test for Scenario when time is between 03:00 and 12:00 */
    @Test
    public void testWithTime_3_To_12(){
        given(serverTimeSupplier.get()).willReturn(ZonedDateTime.of(2023,8,1,6,0,0,0, ZoneId.of("CET"))
                                                                .toInstant());
        String inUserName = "John Doe";
        assertEquals(getExpectedMessage("Good Morning", inUserName), gService.sayHello(inUserName).block());                                  
    }
   
    /** Test for Scenario when time is between 12:00 and 16:00 */
    @Test
    public void testWithTime_12_To_16(){
        given(serverTimeSupplier.get()).willReturn(ZonedDateTime.of(2023,8,1,14,0,0,0, ZoneId.of("CET"))
                                                                .toInstant());
        String inUserName = "John Doe";
        assertEquals(getExpectedMessage("Good Afternoon", inUserName), gService.sayHello(inUserName).block());                                  
    }
    
    /** Test for Scenario when time is between 16:00 and 20:00 */
    @Test
    public void testWithTime_16_To_20(){
        given(serverTimeSupplier.get()).willReturn(ZonedDateTime.of(2023,8,1,17,0,0,0, ZoneId.of("CET"))
                                                                .toInstant());
        String inUserName = "John Doe";
        assertEquals(getExpectedMessage("Good Evening", inUserName), gService.sayHello(inUserName).block());                                  
    }
    
    /** Test for Scenario when time is > 20:00  */
    @Test
    public void testWithTime_GreaterThan_20(){
        given(serverTimeSupplier.get()).willReturn(ZonedDateTime.of(2023,8,1,22,0,0,0, ZoneId.of("CET"))
                                                                .toInstant());
        String inUserName = "John Doe";
        assertEquals(getExpectedMessage("Good Night", inUserName), gService.sayHello(inUserName).block());                                  
    }
    
    /** Test for Scenario when time is  00:00  */
    @Test
    public void testWithTime_Equals_To_00(){
        given(serverTimeSupplier.get()).willReturn(ZonedDateTime.of(2023,8,1,0,0,0,0, ZoneId.of("CET"))
                                                                .toInstant());
        String inUserName = "John Doe";
        assertEquals(getExpectedMessage("Good Night", inUserName), gService.sayHello(inUserName).block());                                  
    }
    
    /** Test for Scenario when time is  > 00:00  & < 03:00 */
    @Test
    public void testWithTime_00_To_03(){
        given(serverTimeSupplier.get()).willReturn(ZonedDateTime.of(2023,8,1,1,45,00,0, ZoneId.of("CET"))
                                                                .toInstant());
        String inUserName = "John Doe";
        assertEquals(getExpectedMessage("Good Night", inUserName), gService.sayHello(inUserName).block());                                  
    }
    /** Utility to create expected test results */
    private Message getExpectedMessage(String sGreeting,String inUserName){
        return Message.builder()
                      .text(String.format("%s, %s!!",sGreeting,inUserName))
                      .serverTime(serverTimeSupplier.get().atZone(ZoneId.of("CET")))
                      .build();
    }


}

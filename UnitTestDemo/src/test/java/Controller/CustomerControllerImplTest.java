package Controller;

import Entity.Customer;
import Entity.Rooms;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

@PowerMockIgnore("javax.managment.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(CustomerControllerImpl.class)
public class CustomerControllerImplTest {

    Customer customer;
    Rooms rooms;
    CustomerControllerImpl customerController;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        rooms = new Rooms();
    }

    @Before
    public void setUp_jUnit4() {
        customer = new Customer();
        rooms = new Rooms();
    }

    @Test
    void addCustomer() {
        customer.setName("Busra");
        customer.setSurname("Sahin");
        customerController = new CustomerControllerImpl(customer, null);
        customerController.addCustomer();
    }

    @Test
    void removeCustomer() {
        customer.setName("Busra");
        customer.setSurname("Sahin");
        customerController = new CustomerControllerImpl(customer, null);
        customerController.removeCustomer();
    }

    @Test
    void updateCustomer() {
        customer.setName("Aleyna");
        customer.setSurname("Boncuk");
        customerController = new CustomerControllerImpl(customer, null);
        customerController.updateCustomer();
    }

    @Test
    void calculateBirthYear() {
        customer.setAge(20);
        customerController = new CustomerControllerImpl(customer, null);
        assertEquals(1999, customerController.calculateBirthYear());
    }


    @org.junit.Test
    public void bodyMassIndex() throws Exception {
        customer.setWeight(47);
        customer.setHeight(170);
        customerController = new CustomerControllerImpl(customer,null);

        Whitebox.invokeMethod(customerController,"bodyMassIndex",customer);


    }


   @org.junit.Test
    public void fatOrThin_ilkIfIcin() throws Exception {
        customerController = PowerMockito.spy(new CustomerControllerImpl(customer, null));
        PowerMockito.doReturn(16).when(customerController, "bodyMassIndex", customer);
        assertEquals("you need to get weight", customerController.fatOrThin());
        verifyPrivate(customerController).invoke("bodyMassIndex", customer);
    }

    @org.junit.Test
    public void fatOrThin_ikinciIfIcin() throws Exception {
        customerController = PowerMockito.spy(new CustomerControllerImpl(customer, null));
        PowerMockito.doReturn(19).when(customerController, "bodyMassIndex", customer);
        assertEquals("you are good", customerController.fatOrThin());
        verifyPrivate(customerController).invoke("bodyMassIndex", customer);

    }

    @org.junit.Test
    public void fatOrThin_ucuncuIfIcin() throws Exception {

        customerController = PowerMockito.spy(new CustomerControllerImpl(customer, null));
        PowerMockito.doReturn(26).when(customerController, "bodyMassIndex", customer);
        assertEquals("you need to lose weight", customerController.fatOrThin());
       verifyPrivate(customerController).invoke("bodyMassIndex", customer);
    }


    @Test
    void typeOfRooms() {
        Rooms roomid = Mockito.mock(Rooms.class);
        customerController = new CustomerControllerImpl(null, roomid);
        when(roomid.getRoomId()).thenReturn(1);
        assertEquals("Basic Room", customerController.typeOfRooms());
        verify(roomid).getRoomId();
    }
    @Test
    void typeOfRooms_ikincicase() {
        Rooms roomid2 = Mockito.mock(Rooms.class);
        customerController = new CustomerControllerImpl(null, roomid2);
        when(roomid2.getRoomId()).thenReturn(2);
        assertEquals("Intermadiate Room", customerController.typeOfRooms());
        verify(roomid2).getRoomId();
    }
    @Test
    void typeOfRooms_ucuncucase() {
        Rooms roomid3 = Mockito.mock(Rooms.class);
        customerController = new CustomerControllerImpl(null, roomid3);
        when(roomid3.getRoomId()).thenReturn(3);
        assertEquals("King Room", customerController.typeOfRooms());
        verify(roomid3).getRoomId();
    }
    @Test
    void typeOfRooms_default() {
        Rooms roomid4 = Mockito.mock(Rooms.class);
        customerController = new CustomerControllerImpl(null, roomid4);
        when(roomid4.getRoomId()).thenReturn(4);
        assertEquals("Wrong Room Id", customerController.typeOfRooms());
        verify(roomid4).getRoomId();
    }


    @org.junit.Test(expected = NullPointerException.class)
    public void isNullCustomer_returnNullPointerException() throws Exception {
        customer = null;
        customerController = new CustomerControllerImpl(customer, null);
        Whitebox.invokeMethod(customerController, "isNullCustomer");
    }

    @org.junit.Test
    public void isNullCustomer_returntrue() throws Exception {
        customer.setName("Busra");
        customerController = new CustomerControllerImpl(customer, null);

        boolean result = Whitebox.invokeMethod(customerController, "isNullCustomer");
        assertTrue(result);
    }

    @Test
    void isNullRooms_returnNullPointerException() {
        rooms = null;
        customerController = new CustomerControllerImpl(null, rooms);
        assertThrows(NullPointerException.class,
                () -> {
                    customerController.isNullRooms();
                });
    }


     @Test
    void isNullRooms_returnfalse() {
        customerController = new CustomerControllerImpl(null, rooms);
        assertFalse(customerController.isNullRooms());
    }


    @org.junit.Test
    public void returnRoomObject() throws Exception {
        customerController = new CustomerControllerImpl(null, null);
        Rooms rooms = Mockito.mock(Rooms.class);
        PowerMockito.whenNew(Rooms.class).withNoArguments().thenReturn(rooms);
        assertEquals(rooms, Whitebox.invokeMethod(customerController, "returnRoomObject"));
        PowerMockito.verifyNew(Rooms.class).withNoArguments();
    }


    @org.junit.Test
    public void substractBodyMassIndex_ificin() throws Exception {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        customerController = PowerMockito.spy(new CustomerControllerImpl(null, null));
        PowerMockito.doReturn(2,2).when(customerController,"bodyMassIndex", any());

        Whitebox.invokeMethod(customerController, "substractBodyMassIndex", customer1, customer2);

        verifyPrivate(customerController, times(2)).invoke("bodyMassIndex", any());
    }

    @org.junit.Test
    public void substractMassIndex_elseicin() throws Exception{

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
         customerController = PowerMockito.spy(new CustomerControllerImpl(null,null));
         PowerMockito.doReturn(3,1).when(customerController,"bodyMassIndex",any());

         Whitebox.invokeMethod(customerController,"substractBodyMassIndex",customer1,customer2);

         verifyPrivate(customerController,times(2)).invoke("bodyMassIndex",any());
    }
}





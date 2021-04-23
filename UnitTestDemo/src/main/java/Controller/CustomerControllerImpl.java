package Controller;

import Entity.Customer;
import Entity.Rooms;
import java.util.Objects;

public class CustomerControllerImpl implements CustomerController {

  private Customer customer;
  private Rooms rooms;

  public CustomerControllerImpl(Customer customer, Rooms rooms) {
    this.customer = customer;
    this.rooms = rooms;
  }

  @Override
  public void addCustomer() {
    System.out.println("Add Customer = [" + customer.getName() + " " + customer.getSurname() + "]");
  }

  @Override
  public void removeCustomer() {
    System.out.println("Remove Customer = [" + customer.getName() + " " + customer.getSurname() + "]");
  }

  @Override
  public void updateCustomer() {
    System.out.println("Update Customer = [" + customer.getName() + " " + customer.getSurname() + "]");
  }

  public int calculateBirthYear() {
    return (2019 - customer.getAge());
  }

  //Boy Kilo İndeksi Hesaplıyor
  private int bodyMassIndex(Customer customer) {
    int weight = customer.getWeight();
    float height = customer.getHeight()/100f;
    return (int) (weight / (height * height));
  }

  public String fatOrThin() {
    int index = bodyMassIndex(customer);

    if (index < 18) {
      return "you need to get weight";
    } else if (index >= 18 && index <= 25) {
      return "you are good";
    } else {
      return "you need to lose weight";
    }
  }

  public String typeOfRooms() {
    int roomId = rooms.getRoomId();
    switch (roomId) {
      case 1:
        return "Basic Room";
      case 2:
        return "Intermadiate Room";
      case 3:
        return "King Room";
      default:
        return "Wrong Room Id";
    }
  }

  private boolean isNullCustomer() {
    if (Objects.isNull(customer)) {
      throw new NullPointerException();
    } else {
      return true;
    }
  }

  public  boolean isNullRooms() {
    if (Objects.isNull(rooms)) {
      throw new NullPointerException();
    } else {
      return false;
    }
  }

  private Rooms returnRoomObject() {
    return new Rooms();
  }



  private void substractBodyMassIndex (Customer customer1, Customer customer2) {
    int person1 = bodyMassIndex(customer1);
    int person2 = bodyMassIndex(customer2);
    int substract = person1 - person2;

    if (substract == 0) {
      System.out.println("Warning! People Can Be The Same");
    } else {
      System.out.println(Math.abs(substract));
    }
  }
}

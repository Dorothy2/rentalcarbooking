# rentalcarbooking
Rental Car Booking Coding Assignment - no UI

Design and prototype a car rental system using object-oriented principles. Please focus on delivering the following core features: 

The system should let a customer reserve a car of a given type at a desired date and time for a given number of days
The number of cars of each type is limited, but customers should be able to reserve a single rental car for multiple, non-overlapping time frames
Provide a Junit test that illustrates the core reservation workflow and demonstrates its correctness
 

Please use Java as the implementation language.

 While the solution should allow for extension to be exposed as a service, please note that

 No UI needs to be provided
 
 To Test app:
 
 Run integration tests BookingServiceTest and InventoryServiceTest.
 
 TBD: (1) remove code which is not part of requirements, like the check that the driver is >= 25.
      (2) refactor dates in tests by basing from the current system date so that this can be run at any time and the tests will not fail due to old dates.

# Hotel Service - MVP

A hotel service - a spring boot application that handles the following functionalities: search, create, update, view, and cancel hotel bookings. 
Here’s a basic outline for this application:

### Directory Breakdown

- **HotelBookingApplication.java**: The main entry point of the application.
- **controller/**: Contains REST controllers for handling HTTP requests.
    - **BookingController.java**: Manages booking-related operations.
    - **HotelController.java**: Manages hotel-related operations.
- **model/**: Contains the data models for the application.
    - **Booking.java**: Represents a hotel booking.
    - **Hotel.java**: Represents a hotel.
    - **User.java**: Represents a user.
- **repository/**: Contains interfaces for database interactions.
    - **BookingRepository.java**: Repository for managing bookings.
    - **HotelRepository.java**: Repository for managing hotels.
- **service/**: Contains service classes for business logic.
    - **BookingService.java**: Business logic for bookings.
    - **HotelService.java**: Business logic for hotels.
- **resources/**: Contains configuration and SQL files.
    - **application.properties**: Application configuration properties.
    - **data.sql**: SQL script for initializing the database with sample data.


### Service Setup

1. Install git - https://git-scm.com/book/en/v2/Getting-Started-Installing-Git and set up git credentials
2. Install mysql server - https://www.geeksforgeeks.org/how-to-install-mysql-on-macos/ . If 'mysql -u root -p' command throws error, it may be because of not adding mysql to PATH. For MacOS, add 'export PATH=${PATH}:/usr/local/mysql/bin/' in zshrc file (varies for different macs). Run the server locally.
3. Clone this repository - 'git clone https://github.com/simran1901/hotel-service'
4. Download intellij and open the project.
5. Go to File -> Project Structure -> Download jdk 11 -> Apply
6. Open the HotelServiceApplication file and right click on it. Click on run (in debug mode).
7. Go to http://localhost:8080/swagger-ui.html on any browser to retrieve documentation.


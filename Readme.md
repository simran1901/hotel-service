# Hotel Service - MVP

A hotel service - a spring boot application that handles the following functionalities: search, create, update, view, and cancel hotel bookings. 
Here’s a basic outline for this application:

### Project Structure

src/
└── main/
├── java/
│   └── com/
│       └── example/
│           └── hotel-service/
│               ├── HotelServiceApplication.java
│               ├── controller/
│               │   ├── BookingController.java
│               │   └── HotelController.java
│               ├── entity/
│               │   ├── Booking.java
│               │   ├── Hotel.java
│               │   └── User.java
│               ├── repository/
│               │   ├── BookingRepository.java
│               │   └── HotelRepository.java
│               └── service/
│                   ├── BookingService.java
│                   └── HotelService.java
└── resources/
    ├── application.properties
    └── data.sql

1. **Controller:** Handles HTTP requests.
2. **Service:** Contains business logic.
3. **Repository:** Manages data persistence.
4. **Entity:** Defines database entities.
5. **Config:** Configures the application.
6. **HotelServiceApplication:** Starting point of the application

### Service Setup

1. Install git - https://git-scm.com/book/en/v2/Getting-Started-Installing-Git and set up git credentials
2. Install mysql server - https://www.geeksforgeeks.org/how-to-install-mysql-on-macos/ . If 'mysql -u root -p' command throws error, it may be because of not adding mysql to PATH. For MacOS, add 'export PATH=${PATH}:/usr/local/mysql/bin/' in zshrc file (varies for different macs). Run the server locally.
3. Clone this repository - 'git clone https://github.com/simran1901/hotel-service'
4. Download intellij and open the project.
5. Go to File -> Project Structure -> Download jdk 11 -> Apply
6. Open the HotelServiceApplication file and right click on it. Click on run (in debug mode).
7. Go to http://localhost:8080/swagger-ui.html on any browser to retrieve documentation.


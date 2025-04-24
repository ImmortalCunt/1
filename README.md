# Urban Ladder Automation Testing Framework

This project contains automated tests for the Urban Ladder e-commerce website using Selenium WebDriver, TestNG, and Java.

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome browser (latest version)
- Chrome WebDriver (managed automatically by WebDriverManager)

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── urbanladder/
│               ├── base/
│               │   ├── BaseTest.java
│               │   └── WebDriverManager.java
│               ├── pages/
│               │   ├── HomePage.java
│               │   └── ProductPage.java
│               └── utils/
│                   └── ExcelUtils.java
└── test/
    └── java/
        └── com/
            └── urbanladder/
                └── tests/
                    └── UrbanLadderTest.java
```

## Features Tested

1. User Registration
2. User Login
3. Add items to wishlist
4. Order items (till payment page)
5. Compare items
6. Compare incompatible items
7. Help desk functionality
8. User Logout

## Running the Tests

1. Clone the repository:
```bash
git clone <repository-url>
```

2. Navigate to the project directory:
```bash
cd urbanladder-automation
```

3. Run the tests using Maven:
```bash
mvn clean test
```

## Test Reports

- TestNG reports will be generated in the `test-output` directory
- Logs will be available in the `logs` directory
- Product comparison data will be saved in `product_comparison.xlsx`

## Configuration

- Browser configuration can be modified in `testng.xml`
- Logging configuration can be modified in `src/main/resources/log4j2.xml`

## Notes

- The framework uses Page Object Model (POM) design pattern
- Tests are configured to run in sequence using TestNG priorities
- WebDriverManager is used for automatic driver management
- Log4j2 is used for logging
- Apache POI is used for Excel operations 
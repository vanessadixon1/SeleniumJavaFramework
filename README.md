# Selenium Java Automation Framework

A Selenium WebDriver test automation framework built with Java, TestNG, and Maven. Supports local and Selenium Grid execution, data-driven testing via JSON, and automated HTML reporting with ExtentReports.

---

## Tech Stack

| Tool | Version |
|---|---|
| Java | 17 |
| Selenium WebDriver | 4.44.0 |
| TestNG | 7.12.0 |
| Maven | 3.x |
| ExtentReports | 5.1.2 |
| Gson | 2.11.0 |
| Logback | 1.5.33 |
| Lombok | 1.18.38 |

---

## Project Structure

```
src
├── main/java/com/amcsoftware
│   ├── driver
│   │   └── DriverFactory.java        # ThreadLocal WebDriver management, local & grid init
│   ├── models
│   │   ├── Homepage.java             # Root model mapping to HomePage.json
│   │   ├── HomePageItem.java         # Search test data model
│   │   ├── LoginItem.java            # Login test data model
│   │   ├── NavigationItem.java       # Navigation test data model
│   │   └── PageContentItem.java      # Page content test data model
│   ├── pages
│   │   ├── BasePage.java             # Abstract base for all page objects
│   │   └── HomePage.java             # HomePage page object
│   └── utils
│       ├── ExtentManager.java        # Singleton ExtentReports initialiser
│       └── JsonReader.java           # Generic JSON test data loader
│
├── main/resources
│   └── config.properties             # gridUrl and environment config
│
└── test/java/com/amcsoftwae
    ├── base
    │   └── BaseTest.java             # TestNG lifecycle: driver setup/teardown
    ├── dataProviders
    │   └── LoginPageData.java        # @DataProvider methods for all HomePage sections
    ├── listeners
    │   └── TestListener.java         # TestNG listener: ExtentReports logging + screenshots
    └── tescases
        └── HomePageTest.java         # HomePage test class

test/resources
└── testData
    └── HomePage.json                 # JSON test data (search, login, navigation, content)
```

---

## Setup

### Prerequisites

- Java 17+
- Maven 3.x
- Google Chrome (latest)
- ChromeDriver (managed automatically by Selenium 4)

### Clone and Install

```bash
git clone <repo-url>
cd SeleniumJavaAutomation
mvn clean install -DskipTests
```

---

## Running Tests

### Local (QA environment)

```bash
mvn test -Pqa
```

### Selenium Grid (Dev environment)

Start a Grid 4 standalone server first:

```bash
java -jar selenium-server-4.x.jar standalone
```

Then run:

```bash
mvn test -Pdev
```

### Maven Profiles

| Profile | Run Mode | Environment |
|---|---|---|
| `qa` | local | qa |
| `dev` | grid | dev |

---

## Configuration

**`src/main/resources/config.properties`**

```properties
gridUrl=http://localhost:4444
```

Update `gridUrl` to point to your Selenium Grid hub when running in grid mode.

**`testng.xml`** — controls which browser and test classes run:

```xml
<parameter name="browser" value="chrome"/>
```

Supported browsers: `chrome`, `firefox`, `edge`

---

## Data-Driven Testing

Test data lives in `src/test/resources/testData/HomePage.json` and is divided into sections:

| Section | Model | DataProvider name |
|---|---|---|
| `homePage` | `HomePageItem` | `searchData` |
| `login` | `LoginItem` | `loginData` |
| `navigation` | `NavigationItem` | `navigationData` |
| `pageContent` | `PageContentItem` | `pageContentData` |

Use a data provider in a test like this:

```java
@Test(dataProvider = "loginData", dataProviderClass = LoginPageData.class)
public void loginTest(LoginItem data) {
    // data.getUsername(), data.getPassword(), etc.
}
```

Adding a new data set requires only a new entry in `HomePage.json` — no code changes needed.

---

## Reports

HTML reports are generated automatically after each run under `reports/`:

```
reports/report_2026-06-01_21-26-35.html
```

Each run produces a uniquely timestamped file. Failed tests automatically capture a screenshot attached to the report and saved under `screenshots/`.

---

## Architecture Notes

- **`DriverFactory`** uses `ThreadLocal<WebDriver>` for thread-safe parallel execution
- **`BasePage`** calls `DriverFactory.getDriver()` lazily (at method call time, not construction) to avoid null driver issues
- **`ExtentManager`** is a singleton initialised once in `@BeforeSuite`
- **`TestListener`** hooks into TestNG lifecycle to log pass/fail/skip to the Extent report

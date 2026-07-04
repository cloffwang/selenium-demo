# Selenium Demo

A Java test automation framework demonstrating a BDD-style Selenium + Cucumber + TestNG setup, using [saucedemo.com](https://www.saucedemo.com/) as the target application. It includes multi-browser execution, environment-based configuration, and Allure reporting wired into GitHub Actions.

## Tech Stack

- **Java 17**, built with **Maven**
- **Selenium WebDriver 4.27** for browser automation (Chrome, Firefox)
- **Cucumber 7** (Gherkin feature files) for BDD-style tests
- **TestNG** as the test runner, with parallel data providers
- **WebDriverManager** for automatic driver binary management
- **Allure** for test reporting (with history publishing to GitHub Pages)
- **SnakeYAML** for environment/config management
- **SLF4J + Logback** for logging

## Project Structure

```
demo/
├── pom.xml
├── scripts/                          # Windows helper scripts for local Allure reports
│   ├── init-report.bat
│   └── copy-history-report.bat
└── src/
    ├── main/
    │   ├── java/com/cliff/managers/
    │   │   ├── ConfigManager.java    # Loads config.yaml, exposes typed getters
    │   │   ├── DriverManager.java    # Creates local/grid/BrowserStack WebDriver instances
    │   │   └── AllureReportManager.java
    │   ├── java/com/cliff/utils/ProjLog.java
    │   └── resources/config.yaml     # Per-environment target URL & browser matrix
    └── test/
        ├── java/com/cliff/
        │   ├── hooks/CucumberHooks.java     # Before/After scenario hooks, screenshots
        │   ├── pages/                       # Page Object classes (LoginPage, InventoryPage)
        │   ├── steps/                       # Cucumber step definitions
        │   ├── common/                      # Shared step/element helpers
        │   ├── runner/                      # TestNG/Cucumber runner classes (Smoke/Regression)
        │   └── tests/ConfigManagerTest.java
        └── resources/
            ├── features/                    # login.feature, inventory.feature
            └── testng/                      # testng.xml, smoke.xml, regression.xml
```

## Configuration

Environments are defined in [demo/src/main/resources/config.yaml](demo/src/main/resources/config.yaml): `local`, `ci`, `grid`, and `browserstack`. Each environment specifies its browser list, headless mode, and grid/BrowserStack settings. `ConfigManager` reads this file at runtime via a dotted-key lookup (e.g. `getConfig("env", "ci", "hubUrl")`).

`DriverManager` uses this configuration to spin up a `ChromeDriver`/`FirefoxDriver` locally, a `RemoteWebDriver` against a Selenium Grid hub, or a BrowserStack remote session (requires `BROWSERSTACK_USER`, `BROWSERSTACK_KEY`, `BROWSERSTACK_HUB` env vars).

## Running Tests

From the `demo/` directory:

```bash
# Run the default suite (testng.xml) against the "ci" environment
mvn verify -Denv=ci

# Run only the smoke suite
mvn test -DsuiteXmlFile=src/test/resources/testng/smoke.xml -Denv=local

# Run only the regression suite
mvn test -DsuiteXmlFile=src/test/resources/testng/regression.xml -Denv=local
```

Test scenarios are tagged (`@Smoke`, `@Regression`, `@Disabled`) in the feature files, and `RunSmokeTest`/`RunRegressionTest` filter by these tags. Each scenario runs once per browser configured for the active environment (e.g. both Chrome and Firefox), driven off `config.yaml`.

### Allure Reports

Live report (published from `gh-pages` by CI): https://cloffwang.github.io/selenium-demo/

```bash
allure generate demo/target/allure-results --clean -o demo/target/allure-report
allure open demo/target/allure-report
```

Helper `.bat` scripts in `demo/scripts/` wrap this for local Windows use, including preserving report history between runs.

## Continuous Integration

[.github/workflows/main.yml](.github/workflows/main.yml) runs on every push to `main`:
1. Checks out the code and sets up JDK 17 (Zulu).
2. Runs `mvn verify -Denv=ci`.
3. Builds an Allure report (merging in history from the `gh-pages` branch).
4. Publishes the report to the `gh-pages` branch.

It can also be triggered manually via `workflow_dispatch`, optionally enabling a `tmate` debug session.

## Test Coverage

- **Login** ([login.feature](demo/src/test/resources/features/login.feature)): valid login, empty username/password validation, invalid credential combinations.
- **Inventory** ([inventory.feature](demo/src/test/resources/features/inventory.feature)): sort-order verification, link validity check across the inventory page.

## License

Apache License 2.0 — see [LICENSE](LICENSE).

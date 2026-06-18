# SauceDemo Selenium Automation

## Tech Stack
- Java 21
- Selenium 4.18.1
- Cucumber BDD 7.15.0
- TestNG 7.9.0
- Maven 3.9.x

## How to Run
```bash
cd sauce-demo
mvn test
```

## Run with specific browser
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

## Run headless (for CI)
```bash
mvn test -Dheadless=true
```

## Reports
After running, open: `target/cucumber-reports/report.html`

# Requirements – CleanSweep

**Project Name:** CleanSweep
**Team:** Ronny Kpa - Provider, Millard Cil - Customer
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-09-18

---

## 1. Overview
**Vision.** CleanSweep is web platform that connects households and property owners with cleaners based on their constraints and preferences. Trying to find a reliable selection of trusted cleaners can be a struggle for those with specific living spaces, locations, and budgets. CleanSweep provides a simple way of searching and discovering cleaners, cleaning services, pricing, locations, and booking appointments on a user friendly interface.

**Glossary** Terms used in the project
- **Cleaner:** The cleaning professional, indepedent or company, who provides cleaning services to the customers.
- **Customer:** A person looking to book an appointment with a cleaner.
- **Appointment:** A scheduled time between a customer and a cleaner for a cleaning service.
- **Service:** The type of cleaning offered and provided by a cleaner
- **Profile:** Information about a user that includes personal details, cleaning preferences, and constraints.

**Primary Users / Roles.**
- **Property Owners/Household Owners** — Find and book cleaners that fit their preferences and constraints.
- **Cleaner** — Connect with customers, advertise and manage their services.


**Scope (this semester).**
- User Profiles (customers and cleaners)
- Search and browse cleaners by pricing and location
- Booking/Canceling cleaning appointments

**Out of scope (deferred).**
- Customer feedback and ratings
- Cleaner response

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)


### 2.1 Customer Stories
- **US‑1 — <Register and create profile>**  
  _Story:_ As a customer, I want to create a customer profile so that cleaners can understand my preferences and limitations  
  _Acceptance:_
  ```gherkin
  Scenario: Register with valid credentials
    Given I am not registered
    When  I provide valid registration details
    Then  I should be registered and logged in
    And I can view my profile
  ```

- **US‑2 — <Browse cleaners by pricing and location>**  
  _Story:_ As a customer, I want to browse cleaners by pricing and location so that I can find cleaners that best fit my needs
  _Acceptance:_
  ```gherkin
  Scenario: Browse cleaners by pricing and location
    Given I am logged in as a customer
    When  I select a price range and/or location
    Then  I should see a list of cleaners who fall under the selected specifications
  ```

  - **US‑3 — <Book a cleaning appointment>**  
  _Story:_ As a customer, I want to book a cleaning appointment with a cleaner so that I can receive the services I want
  _Acceptance:_
  ```gherkin
  Scenario: Browse cleaners by pricing and location
    Given I am logged in as a customer
    When  I select a cleaner, choose a service, and book an available time slot
    Then  I should receive a confirmation of the booked appointment
    AND I can view the appointment on my customer profile dashboard
  ```

    - **US‑4 — <Cancel a cleaning appointment>**  
  _Story:_ As a customer, I want to cancel a cleaning appointment with a cleaner so that I remove a booking I do not want anymore
  _Acceptance:_
  ```gherkin
  Scenario: Cancel a cleaning appointment
    Given I am logged in as a customer and booked an appointment
    When  I deselect or cancel an appointment
    Then  I should receive a confirmation of the cancelled appointment, removing the booking
    AND I can view the cancellation on my customer profile dashboard
  ```
  
### 2.2 Provider (Cleaner) Stories
- **US-20 — Create and update cleaner profile**  
  _Story:_ As a cleaner, I want to create and update my profile so that I can attract clients.  
  _Acceptance:_
  ```gherkin
  Scenario: Create and update cleaner profile
    Given I do not have a profile
    When  I provide my details and submit the form
    Then  my profile should be created 
    And   the profile should be visible to customers
  ```

- **US-21 — Define services and pricing**  
  _Story:_ As a cleaner, I want to define my services and pricing so that customers understand my offerings.
  _Acceptance:_
  ```gherkin
  Scenario: Define services and pricing 
    Given I am logged in as a cleaner
    When  I add my services and set pricing 
    Then  the services should be saved, visible and understood to customers
  ```

- **US‑30 — Cancel appointments**  
  _Story:_ As a cleaner, I want to cancel appointments so that I can focus on other appointments  
  _Acceptance:_
  ```gherkin
  Scenario: Cancel appointments
    Given I am logged in as a cleaner
    When  I am offered an appointment that is out of my scope or doesn't seem worth pursuing 
    Then  I should be able to cancel the appointment 
  ```

---

## 3. Non‑Functional Requirements (make them measurable)
- **Performance:** Website load and user actions shall receive a response within 2 seconds under typical load. 
- **Availability/Reliability:** The system should be available 99.5% of the time during the semester and not lose submitted booking information.
- **Security/Privacy:**  The system must implement secure authentication and authorization mechanisms. All sensitive data should be encrypted in transit and at rest.
- **Usability:** New users should be able to complete the registration process, view available cleaner information, and book a cleaning appointment without assistance in 10 minutes or less.
---

## 4. Assumptions, Constraints, and Policies
- Modern browsers and stable connectivity
- User responsilble for putting down and providing accurate information, service addresses, and booking details
- Real payments and transactions not part of demonstration and will not be included/facilitated

---

## 5. Milestones (course‑aligned)
- **M1 Requirements** — this file + stories opened as issues. 
- **M2 High‑fidelity prototype** — core customer/provider flows fully interactive. 
- **M3 Design** — architecture, schema, API outline. 
- **M4 Backend API** — key endpoints + tests. 
- **M5 Increment** — ≥2 use cases end‑to‑end. 
- **M6 Final** — complete system & documentation. 

---

## 6. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.
---
name: project-test-standards
description: >-
  How this project writes and names Quarkus tests. Use whenever adding or
  changing tests, or whenever a code change lacks a covering test in
  coolstore-inventory-service.
---

# Test Standards

Every behavior change ships with tests in the same change. A task is not
done because the code compiles; it is done when the tests below exist and
`./mvnw test` passes.

## Structure and naming

- Tests live in `src/test/java/com/redhat/coolstore/inventory/`, named
  `<ClassUnderTest>Test` (e.g. `InventoryResourceTest`).
- Test classes are package-private and annotated `@QuarkusTest`.
- Test methods are package-private `void`, named as lowerCamelCase behavior
  statements: `listInventoryItems`, `getInventoryItemByItemId`,
  `availabilityReportsOutOfStock` — the name reads as what the system does,
  not `test1` or `shouldXxx`.

## HTTP test style

- Use RestAssured's `given().when().<verb>(path).then()` flow with static
  imports, matching `InventoryResourceTest`.
- Assert with Hamcrest matchers on JSON bodies (`is`, `contains`,
  `hasSize`), not string comparison on raw payloads.
- Always assert the status code AND at least one body property.
- Use the canonical seed items (`329299`, `329199`, `165613`) for
  happy-path tests, and a clearly-fake identifier (`does-not-exist`) for
  404 paths.

## Coverage expectations per endpoint

Each endpoint change covers at minimum:

1. Happy path with representative body assertions.
2. Not-found (or validation-failure) path with the error status code.
3. For mutating endpoints: a follow-up read proving the state change, and
   one test for rejected invalid input.

## What NOT to do

- No Mockito mocking of the repository in `@QuarkusTest` HTTP tests — this
  project tests through the real wiring with seed data.
- No test ordering dependencies; each test must pass alone.
- No sleeping/polling in tests.
- Do not weaken or delete a failing assertion to make a task pass; report
  the failure and stop for review instead.
